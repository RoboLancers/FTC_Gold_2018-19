package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.firstinspires.ftc.teamcode.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.vision.TensorflowWrapper;

@Autonomous(name = "EncoderCrater")

public class EncoderCrater extends LinearOpMode {

    Robot robot = new Robot();
    // TensorflowWrapper tensorflowWrapper;
    //
    //
    @Override
    public void runOpMode() throws InterruptedException {
      //  tensorflowWrapper = new TensorflowWrapper(hardwareMap);
      //  tensorflowWrapper.activateTfod();
        robot.hardware2(hardwareMap);

        waitForStart();

        robot.latch(1.0, 5);

        robot.rotateRobotRight(-30);
        robot.latch(1.0, -5);
        robot.rotateRobotRight(30);
                                               robot.driveDistance(1.0,41);



        /*
        //sampling
        while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
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
         /*robot.latchOpen(1.0);
        sleep(1000);
        robot.turnLeft(0.5);
        sleep(500);
        robot.latchClose(1.0);
        sleep(900);
        robot.turnLeft(0.5);
        sleep(500);
        robot.turnLeft(0.5);
        sleep(2000);
        robot.turnLeft(0.5);
        sleep(3000);
        */