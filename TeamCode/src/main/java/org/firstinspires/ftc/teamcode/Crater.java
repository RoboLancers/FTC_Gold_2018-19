package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;

@Autonomous(name = "Crater")
public class Crater extends LinearOpMode {

    Robot robot = new Robot();
    //TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

            // tensorflowWrapper = new TensorflowWrapper(hardwareMap);
            // tensorflowWrapper.activateTfod();
            robot.hardware(hardwareMap, telemetry);

            robot.latch(1, -70);
            robot.rotateRobot(-35);
            robot.latch(1, 60);
            robot.rotateRobot(10);

            robot.driveDistance(.99, -32);

            robot.stopDriving();


            //sampling
            //we dont know field view yet

       /* while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
            tensorflowWrapper.updateRecognition();
            tensorflowWrapper.updateMineralPosition();

            telemetry.addData("# Object Detected", tensorflowWrapper.getNumberOfObjectsDetected());
            telemetry.addData("Gold X", tensorflowWrapper.getFirstGoldMineralX());
            telemetry.update();
        }

        tensorflowWrapper.shutdown();

        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT) {
            robot.rotateRobot(15);
            robot.driveDistance(1, 15);
        } else if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.RIGHT) {
            robot.rotateRobot(-15);
            robot.driveDistance(1, 15);
        }else {
            robot.driveDistance(1, 15);
        }

*/

        }
    }
