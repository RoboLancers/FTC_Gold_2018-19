package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.utility.vision.FieldView;
import org.firstinspires.ftc.teamcode.utility.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;

@Autonomous (name ="TurnSampling")
public class
Sampling extends LinearOpMode {

    DcMotor topLeft;
    DcMotor bottomLeft;
    DcMotor topRight;
    DcMotor bottomRight;

    private BNO055IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get (BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        topLeft = hardwareMap.get(DcMotor.class, "top_left");
        bottomLeft = hardwareMap.get(DcMotor.class, "bottom_left");
        topRight = hardwareMap.get(DcMotor.class, "top_right");
        bottomRight = hardwareMap.get(DcMotor.class, "bottom_right");

        topLeft.setDirection(DcMotor.Direction.REVERSE);
        bottomLeft.setDirection(DcMotor.Direction.REVERSE);

        TensorflowWrapper tensorflowWrapper = new TensorflowWrapper(hardwareMap, FieldView.LEFT);
        tensorflowWrapper.activateTfod();

        waitForStart();

        tensorflowWrapper.detectMinerals(10, this);

        double angle = 0;

        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT) {
            angle = 15;
        } else if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.RIGHT) {
            angle = -15;
        }

        while (opModeIsActive()) {
            double error = angle - imu.getAngularOrientation().toAxesOrder(AxesOrder.ZYX).firstAngle;
            error *= 0.001;

            topLeft.setPower(-error);
            bottomLeft.setPower(-error);
            topRight.setPower(error);
            bottomRight.setPower(error);

        }

        tensorflowWrapper.shutdown();
    }
}