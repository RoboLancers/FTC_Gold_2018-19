package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

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

    public TensorflowWrapper(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        initVuforia();
        initTfod();

        goldMineralPoints = new ArrayList<>();
        silverMineralPoints = new ArrayList<>();
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
        tfod.shutdown();
        CameraDevice.getInstance().setFlashTorchMode(false);
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
        if(!goldMineralPoints.isEmpty()) {
            return goldMineralPoints.get(0).getX();
        }else{
            return -1;
        }
    }

    /**
     * Check if we have detected any gold mineral.
     * Should be called after updateMineralPosition
     */
    public boolean detectedGoldMineral(){
        return goldMineralPoints.size() > 0;
    }

    /**
     * Check if we have detected two silver minerals.
     * Should be called after updateMineralPosition
     */
    public boolean detectedTwoSilverMinerals(){
        return silverMineralPoints.size() > 1;
    }

    public boolean detectedSilverMinerals(){
        return silverMineralPoints.size() > 0;
    }


    /**
     * Helper method to check if we detected one gold and two silver mineral.
     * Should be called after updateMineralPosition
     */
    public boolean detectedThreeMinerals(){
        return detectedGoldMineral() && detectedTwoSilverMinerals();
    }
    public boolean detectedTwoMinerals(){
        return (detectedTwoSilverMinerals()) || (detectedGoldMineral() && detectedSilverMinerals());
    }

    /**
     * Returns the gold mineral position in relation to the two other silver mineral.
     * Could return UNKNOWN if we don't detect all three minerals.
     */
    public MineralPosition getGoldMineralPosition() {

        if (detectedTwoMinerals()) {
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
        return MineralPosition.UNKNOWN;
    }

}