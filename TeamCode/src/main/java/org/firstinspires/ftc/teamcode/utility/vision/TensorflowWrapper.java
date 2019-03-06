package org.firstinspires.ftc.teamcode.utility.vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"WeakerAccess", "unused"})
public class TensorflowWrapper {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "Aavasa3/////AAABmRV65kreC0RthhGZVtQ/UH81ZwAlkkxxN7E+rTqb2qtUa/KIxeGM1QU+QyB6o+v18fU77aHYIOw6aQUgCdVWgHL20fBAIN/yOe/2nf6J1alaQxxedscMpSq6C6TbAZPgye63Flz8HqMrnApz3PjNSsR9aFnU7TBRdwh+JvUfCSUSCthmp5FlkIJOchnI5UMUVmrdOipRop9Gyd/hJWzkHpbGv9uu0FHYVdQx0J6Opbz8T9ilngrJhrpZwmvd2vkxzJ+gUjQOja4n8OqFeUtH3kIEevRGgei+B2OXMEiGyN0mIOr1cC3sjJ1k2WDiWbQb+CCitMYvIIwIxNT4AlgQ9WuxKOj4UIltt5SzlzXCcYHq";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private HardwareMap hardwareMap;

    private List<Recognition> updatedRecognitions;

    private List<MineralPoint> goldMineralPoints, silverMineralPoints;

    private FieldView fieldView;

    private ElapsedTime timer;

    public TensorflowWrapper(HardwareMap hardwareMap){
        this(hardwareMap, FieldView.FULL);
    }

    public TensorflowWrapper(HardwareMap hardwareMap, FieldView fieldView){
        this.hardwareMap = hardwareMap;

        initVuforia();
        initTfod();

        goldMineralPoints = new ArrayList<>();
        silverMineralPoints = new ArrayList<>();

        this.fieldView = fieldView;

        timer = new ElapsedTime();
    }

    /**
     * Initializes Vuforia
     */
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initializes Tensorflow Object Detection
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.4;

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    /**
     * Runs until stop is requested, detected sampling minerals, or has timed out
     * @param timeout Timeout of the while loop
     * @param opMode The opmode that this is running in
     */
    public void detectMinerals(double timeout, LinearOpMode opMode){
        activateTfod();

        timer.reset();

        while (!opMode.isStopRequested() && !detectedSamplingMinerals() && timer.time(TimeUnit.SECONDS) < timeout) {
            updateRecognition();
            updateMineralPosition();
        }

        shutdown();
    }

    /**
     * Activates Tensorflow Object Detection and turn on flash
     */
    public void activateTfod(){
        CameraDevice.getInstance().setFlashTorchMode(true);
        tfod.activate();
    }

    /**
     * Shutdown Tensorflow Object Detection and turn off flash
     */
    public void shutdown(){
        CameraDevice.getInstance().setFlashTorchMode(false);
        tfod.shutdown();
    }

    /**
     * Gets the updated recognition from Tensorflow
     */
    public void updateRecognition(){
        updatedRecognitions = tfod.getUpdatedRecognitions();
    }

    /**
     * Checks if we actually have updated recognition since
     * tfod.getUpdatedRecognitions() can return null if nothing has changed
     * @return Whether the recognition has updated
     */
    public boolean hasUpdatedRecognitions(){
        return updatedRecognitions != null;
    }

    /**
     * Returns the number of object detected
     */
    public int getNumberOfObjectsDetected(){
        return hasUpdatedRecognitions() ? updatedRecognitions.size() : 0;
    }

    /**
     * Checks if the recognition has updated, then loops through the recognitions
     * and add them to their specific lists
     */
    public void updateMineralPosition(){
        if(hasUpdatedRecognitions()) {
            goldMineralPoints.clear();
            silverMineralPoints.clear();

            for (Recognition recognition : updatedRecognitions) {
                switch(recognition.getLabel()){
                    case LABEL_GOLD_MINERAL:
                        goldMineralPoints.add(new MineralPoint(recognition.getLeft() + recognition.getWidth()/2, recognition.getBottom() + recognition.getHeight()/2, recognition.estimateAngleToObject(AngleUnit.DEGREES)));
                        break;
                    case LABEL_SILVER_MINERAL:
                        silverMineralPoints.add(new MineralPoint(recognition.getLeft() + recognition.getWidth()/2, recognition.getBottom() + recognition.getHeight()/2, recognition.estimateAngleToObject(AngleUnit.DEGREES)));
                        break;
                }
            }
        }
    }

    /**
     * Gets the X coordinate of the first gold mineral detected
     */
    public double getFirstGoldMineralX(){
        return goldMineralPoints.isEmpty() ? -1 : goldMineralPoints.get(0).getX();
    }

    /**
     * Check if we have detected exactly 1 gold mineral
     * Should be called after updateMineralPosition
     */
    public boolean detectedOneGoldMineral(){
        return goldMineralPoints.size() == 1;
    }

    /**
     * Check if we have detected exactly 1 silver mineral
     * Should be called after updateMineralPosition
     */
    public boolean detectedOneSilverMineral(){
        return silverMineralPoints.size() == 1;
    }

    /**
     * Check if we have detected two silver minerals.
     * Should be called after updateMineralPosition
     */
    public boolean detectedTwoSilverMinerals(){
        return silverMineralPoints.size() == 2;
    }

    /**
     * Returns whether we have detected the sampling minerals based on the FieldView
     * @return if we have detected the required sampling minerals
     */
    public boolean detectedSamplingMinerals(){
        return fieldView == FieldView.FULL ? (detectedOneGoldMineral() && detectedTwoSilverMinerals()) :
                ((detectedOneGoldMineral() && detectedOneSilverMineral()) || (detectedTwoSilverMinerals()));
    }

    /**
     * Returns the gold mineral position in relation to the two other silver mineral.
     * Could return UNKNOWN if we don't detect all three minerals.
     */
    public MineralPosition getGoldMineralPosition(){
        if(fieldView == FieldView.FULL) {
            if (detectedOneGoldMineral() && detectedTwoSilverMinerals()) {
                double goldMineralX = goldMineralPoints.get(0).getX();
                double firstSilverMineralX = silverMineralPoints.get(0).getX();
                double secondSilverMineralX = silverMineralPoints.get(1).getX();

                if (secondSilverMineralX < firstSilverMineralX && goldMineralX < secondSilverMineralX) {
                    return MineralPosition.LEFT;
                } else if (goldMineralX > firstSilverMineralX && goldMineralX > secondSilverMineralX) {
                    return MineralPosition.RIGHT;
                } else {
                    return MineralPosition.CENTER;
                }
            }
        }else if(fieldView == FieldView.LEFT){
            if(detectedTwoSilverMinerals()){
                //If we see two silver minerals then the gold is automatically on the right because our phone is on the left
                return MineralPosition.RIGHT;
            }else if(detectedOneGoldMineral() && detectedOneSilverMineral()){
                double goldMineralX = goldMineralPoints.get(0).getX();
                double silverMineralX = silverMineralPoints.get(0).getX();

                if(goldMineralX < silverMineralX){
                    return MineralPosition.LEFT;
                }else{
                    return MineralPosition.CENTER;
                }
            }
        }else if(fieldView == FieldView.RIGHT){
            //If we see two silver minerals then gold mineral is automatically on the left because our phone is on the right
            if(detectedTwoSilverMinerals()){
                return MineralPosition.LEFT;
            }else if(detectedOneGoldMineral() && detectedOneSilverMineral()){
                double goldMineralX = goldMineralPoints.get(0).getX();
                double silverMineralX = silverMineralPoints.get(0).getX();

                if(goldMineralX < silverMineralX){
                    return MineralPosition.CENTER;
                }else{
                    return MineralPosition.RIGHT;
                }
            }
        }

        return MineralPosition.UNKNOWN;
    }
}
