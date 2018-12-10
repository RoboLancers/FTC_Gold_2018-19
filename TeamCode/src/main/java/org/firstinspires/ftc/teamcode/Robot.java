package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


public class Robot {

    DcMotor topLeft, topRight, bottomLeft, bottomRight;
    DcMotor latch;
    DcMotor Intake;
    DcMotor flipper;

    BNO055IMU imu;
    Telemetry telemetry;

    public void hardware(HardwareMap hardwareMap, Telemetry telemetry) {
        topLeft = hardwareMap.dcMotor.get("Top Left");
        topRight = hardwareMap.dcMotor.get("Top Right");
        bottomLeft = hardwareMap.dcMotor.get("Bottom Left");
        bottomRight = hardwareMap.dcMotor.get("Bottom Right");
        latch = hardwareMap.dcMotor.get("Latch");

        Intake = hardwareMap.dcMotor.get("Intake");
        flipper = hardwareMap.dcMotor.get("Flipper");

        topLeft.setDirection(DcMotor.Direction.REVERSE);
        bottomLeft.setDirection(DcMotor.Direction.REVERSE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        this.telemetry = telemetry;
    }

    public void outake(double power) {
        Intake.setPower(power);
    }

    public void driveForward(double power) {
        topLeft.setPower(power);
        bottomLeft.setPower(power);
        topRight.setPower(power);
        bottomRight.setPower(power);
    }

    public void driveBackward(double power) {
        topLeft.setPower(-power);
        bottomLeft.setPower(-power);
        topRight.setPower(-power);
        bottomRight.setPower(-power);
    }


    public void StopDriving() {
        topRight.setPower(0);
        bottomRight.setPower(0);
        bottomLeft.setPower(0);
        topLeft.setPower(0);
    }

    public void driveDistance(double power, double inches) {

        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        topLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottomLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottomRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        topLeft.setTargetPosition((int) ((inchesToTick(inches))));
        bottomLeft.setTargetPosition((int) ((inchesToTick(inches))));
        topRight.setTargetPosition((int) ((inchesToTick(inches))));
        bottomRight.setTargetPosition((int) (inchesToTick(inches)));

        driveForward(power);

        while (topLeft.isBusy() && bottomLeft.isBusy() && topRight.isBusy() && bottomRight.isBusy()) {
        }

        StopDriving();
    }

   public void latch(double power, double inches) {
        latch.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        latch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        latch.setTargetPosition((int) ((inchesToTick(inches))));
        latch.setPower(power);
        while (latch.isBusy()) {

        }
        latch.setPower(0);
    }




    public double inchesToTick(double inches) {
        //converts the amount of inches into ticks
        double circumference = Math.PI * 4;
        double fullRotation = 1440;
        return (inches / (circumference)) * fullRotation;
    }
    /* one full rotation = 12.56 in.
    1 tick = 0.00872 in.
    1 in = 114 ticks
     */


    public double getAngle() {
        return AngleUnit.DEGREES.normalize(imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
    }

    public void rotateRobotLeft(double Degrees) {
        double currentAngle = getAngle();
        double error = Degrees - getAngle();

        while (Math.abs(error) > 5) {

            error = Degrees - getAngle();
            double output = error * 0.01;

            topRight.setPower(output);
            bottomRight.setPower(output);
            bottomLeft.setPower(-output);
            topLeft.setPower(-output);

            telemetry.addData("angle is ", getAngle());
            telemetry.update();

        }

    }

    public void rotateRobotRight(double Degrees) {
        double currentAngle = getAngle();
        double error = Degrees - getAngle();

        while (Math.abs(error) > 5) {

            error = Degrees - getAngle();
            double output = error * 0.01;

            topRight.setPower(-output);
            bottomRight.setPower(-output);
            bottomLeft.setPower(output);
            topLeft.setPower(output);

            telemetry.addData("angle is ", getAngle());
            telemetry.update();

        }

    }
}
