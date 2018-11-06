package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "bot")

public class GoldTeleOp extends OpMode {

    Robot robot = new Robot();


    @Override
    public void init(){
        robot.hardware(hardwareMap);
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);


    }

    @Override
    public void loop(){
        double leftsticky = -(gamepad1.left_stick_y);
        double rightsticky = -(gamepad1.right_stick_y);
        boolean buttonA = gamepad1.a;
        boolean buttonB = gamepad1.b;


        robot.topLeft.setPower(leftsticky);
        robot.bottomLeft.setPower(leftsticky);
        robot.topRight.setPower(rightsticky);
        robot.bottomRight.setPower(rightsticky);

        /*if (buttonA) {
            rightIntake.setPower(0.75);
            leftIntake.setPower(0.75);
        }else {
            rightIntake.setPower(0.0);
            leftIntake.setPower(0.0);
        }
        if (buttonB) {
            rightIntake.setPower(-0.75);
            leftIntake.setPower(-0.75);
        }else {
            rightIntake.setPower(0.0);
            leftIntake.setPower(0.0);
        }

    */
    }
}
