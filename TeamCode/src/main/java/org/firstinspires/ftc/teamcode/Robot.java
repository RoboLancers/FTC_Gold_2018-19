package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Robot {

    DcMotor topLeft, topRight, bottomLeft, bottomRight;
    // DcMotor latch;

    /* DcMotor rightIntake;
     DcMotor leftIntake;*/

    public void hardware(HardwareMap hardwareMap) {


        topLeft = hardwareMap.dcMotor.get("Top Left");
        topRight = hardwareMap.dcMotor.get("Top Right");
        bottomLeft = hardwareMap.dcMotor.get("Bottom Left");
        bottomRight = hardwareMap.dcMotor.get("Bottom Right");
//        latch = hardwareMap.dcMotor.get("Latch");

//        rightIntake = hardwareMap.dcMotor.get("Right Intake");
//        leftIntake = hardwareMap.dcMotor.get("Left Intake");

        topLeft.setDirection(DcMotor.Direction.REVERSE);
        bottomLeft.setDirection(DcMotor.Direction.REVERSE);
//        leftIntake.setDirection(DcMotor.Direction.REVERSE);

    }

    public void driveForward(double power) {
        topLeft.setPower(power);
        bottomLeft.setPower(power);
        topRight.setPower(power);
        bottomRight.setPower(power);
    }

    public void driveBackwards(double power) {
        topLeft.setPower(-power);
        bottomLeft.setPower(-power);
        topRight.setPower(-power);
        bottomRight.setPower(-power);
    }

    public void turnRight(double power) {
        topRight.setPower(power);
        bottomRight.setPower(power);
        bottomLeft.setPower(-power);
        topLeft.setPower(-power);
    }

    public void turnLeft(double power) {
        topRight.setPower(-power);
        bottomRight.setPower(-power);
        bottomLeft.setPower(power);
        topLeft.setPower(power);
   /* }public void latchOpen(double power){
        latch.setPower(power);
    }public void latchClose(double power){
        latch.setPower(-power);
    }
    */

    }
}