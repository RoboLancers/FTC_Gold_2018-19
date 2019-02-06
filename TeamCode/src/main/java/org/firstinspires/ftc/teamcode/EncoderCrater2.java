package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

@Disabled
@Autonomous(name = "EncoderCrater2")

public class EncoderCrater2 extends LinearOpMode {

    Robot robot = new Robot();

    // TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {
        //  tensorflowWrapper = new TensorflowWrapper(hardwareMap);
        //  tensorflowWrapper.activateTfod();
        robot.hardware2(hardwareMap);

        waitForStart();

        robot.latch(1.0, 5);

        robot.rotateRobot(-30);
        robot.latch(1.0, -5);
        robot.rotateRobot(30);

        robot.driveDistance(1, 11);
        robot.rotateRobot(90);
        robot.driveDistance(1,40);
        robot.rotateRobot(-65);
        robot.driveDistance(1,40);

        robot.driveDistance(-1,5);
        robot.driveDistance(1,5);
        robot.driveDistance(-1,105);
    }
}