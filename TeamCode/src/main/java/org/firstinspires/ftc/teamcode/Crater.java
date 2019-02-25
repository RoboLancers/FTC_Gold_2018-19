package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;

@Autonomous(name = "Crater")
public class Crater extends LinearOpMode {

    Robot robot = new Robot();
    TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {

        tensorflowWrapper = new TensorflowWrapper(hardwareMap);
        tensorflowWrapper.activateTfod();
        robot.hardware(hardwareMap, telemetry);

        waitForStart();

        //default for rotating is right

        //robot.latch(1,5);
        robot.latchNotEncoder(-.99);
        sleep(2000);
        robot.rotateRobot(50);
        //robot.latch(1,-5);
        robot.latchNotEncoder(.99);
        sleep(1800);
        robot.rotateRobot(-10);

        robot.driveDistance(.99,38);


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



        /* lander to depot =  49 inches
           depot to crater = 85 inches
           lander to crater = 38 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
         */

    }
}