package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utility.vision.FieldView;
import org.firstinspires.ftc.teamcode.utility.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;

@Autonomous(name = "Depot")
public class Depot extends LinearOpMode {

    Robot robot = new Robot();
    TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

            robot.hardware(hardwareMap, telemetry);

            robot.latch(1, -70);
            robot.turnRight(0.5);
            sleep(500);
            robot.stopDriving();
            robot.latch(1, 60);
            robot.turnRight(-0.5);
            sleep(600);
            robot.stopDriving();
;
            robot.driveDistance(.99,-44);
            //robot.outake(.99);
           // sleep(1500);

            robot.stopDriving();
           // robot.turnRight(.5);
           // sleep(1000);
            robot.driveDistance(1,83);




        /*TensorflowWrapper tensorflowWrapper = new TensorflowWrapper(hardwareMap, FieldView.LEFT);
        tensorflowWrapper.activateTfod();

            tensorflowWrapper.detectMinerals(30, this);

        while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
            tensorflowWrapper.updateRecognition();
            tensorflowWrapper.updateMineralPosition();

            telemetry.addData("# Object Detected", tensorflowWrapper.getNumberOfObjectsDetected());
            telemetry.addData("Gold X", tensorflowWrapper.getFirstGoldMineralX());
            telemetry.update();
        }

        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT) {
            robot.turnRight(-0.5);
            sleep(250);
            //robot.driveDistance(1, 15);
        } else if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.CENTER){
            robot.driveDistance(.99,-44);
            robot.outake(.99);
            sleep(1500);
        }else
        {
            robot.turnRight(0.5);
            sleep(250);
        }

        tensorflowWrapper.shutdown();
*/
    }

        }

