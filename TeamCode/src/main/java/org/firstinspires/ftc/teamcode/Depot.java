package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous(name = "Depot")
public class Depot extends LinearOpMode {
    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {

        robot.hardware(hardwareMap, telemetry);

        waitForStart();

        /*.latchOpen(1.0);
        sleep(1000);
        robot.turnLeft(1.0);
        sleep(1500);
        robot.driveForward(1);
        sleep(500);
        robot.turnRight(1.0);
        sleep(1500);
        robot.driveBackward(1);
        sleep(500);
        robot.latchClose(1.0);
        sleep(1000);
        */

        robot.driveForward(0.8);
        sleep(2750);
        robot.driveBackward(0.5);
        sleep(1000);
        robot.turnRight(0.75);
        sleep (1650);
        robot.driveForward(0.99);
        sleep (5500);

    }
































































































































































































































































}
