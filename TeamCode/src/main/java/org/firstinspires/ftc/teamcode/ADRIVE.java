package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "adrive")

public class ADRIVE extends OpMode {

    Robot robot = new Robot();


    @Override
    public void init() {
        robot.hardware(hardwareMap, telemetry);
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);


    }

    @Override
    public void loop() {
        double leftsticky = -(gamepad1.left_stick_y);
        double rightstickx = -(gamepad1.right_stick_x);

        boolean intake = gamepad1.left_bumper;
        boolean outake = gamepad1.right_bumper;
        //gamepad 2-manipultor
        double flipper = -(gamepad2.left_stick_y);
        boolean buttonA = gamepad2.a;
        boolean buttonB = gamepad2.b;

        robot.topLeft.setPower(leftsticky);
        robot.topRight.setPower(leftsticky);
        robot.bottomRight.setPower(leftsticky);
        robot.bottomLeft.setPower(leftsticky);

        robot.bottomLeft.setPower(-rightstickx);
        robot.bottomRight.setPower(rightstickx);
        robot.topRight.setPower(rightstickx);
        robot.topLeft.setPower(-rightstickx);

        robot.flipper.setPower(flipper);

        if (buttonA) {
            robot.latch.setPower(1.00);
        } else {
            robot.latch.setPower(0.0);
        }
        if (buttonB) {
            robot.latch.setPower(-1.00);
        } else {
            robot.latch.setPower(0.0);
        }
        if (intake) {
            robot.Intake.setPower(1.00);
        } else {
            robot.Intake.setPower(0.0);
        }
        if (outake) {
            robot.Intake.setPower(-1.00);
        } else {
            robot.Intake.setPower(0.0);
        }

    }

}