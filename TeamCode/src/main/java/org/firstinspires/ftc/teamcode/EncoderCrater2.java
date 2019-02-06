package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.firstinspires.ftc.teamcode.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.vision.TensorflowWrapper;

@Disabled
@Autonomous(name = "EncoderCrater2")

public class EncoderCrater2 extends LinearOpMode {

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

        robot.driveDistance(1, 11);
        robot.rotateRobotRight(90);
        robot.driveDistance(1,40);
        robot.rotateRobotRight(-65);
        robot.driveDistance(1,40);

        robot.driveDistance(-1,5);
        robot.driveDistance(1,5);
        robot.driveDistance(-1,105);
    }
}