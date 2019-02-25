package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "bot")

public class GoldTeleOp extends OpMode {

    Robot robot = new Robot();


    @Override
    public void init(){
        robot.hardware2(hardwareMap);
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop(){

        //gamepad 1-driver
        double leftsticky = (gamepad1.left_stick_y);
        double rightsticky = (gamepad1.right_stick_y);
        boolean intake = gamepad1.left_bumper;
        boolean outake = gamepad1.right_bumper;
        //gamepad 2-manipultor
        double flipper = -(gamepad2.left_stick_y);
        boolean buttonA = gamepad2.a;
        boolean buttonB = gamepad2.b;


        robot.topLeft.setPower(leftsticky);
        robot.bottomLeft.setPower(leftsticky);
        robot.topRight.setPower(rightsticky);
        robot.bottomRight.setPower(rightsticky);
        
        robot.flipper.setPower(flipper * 0.5);

        if (buttonA) /* button 4 or y */{
            robot.latch.setPower(1.00);
        }else {
            robot.latch.setPower(0.0);
        }
        if (buttonB)  /* button 1 or x*/ {
            robot.latch.setPower(-1.00);

        }else {
            robot.latch.setPower(0.0);
        }
        if (intake) {
            robot.Intake.setPower(1.00);
        }else {
            robot.Intake.setPower(0.0);
        }
        if (outake) {
            robot.Intake.setPower(-1.00);

        }else {
            robot.Intake.setPower(0.0);
        }


    }
}
