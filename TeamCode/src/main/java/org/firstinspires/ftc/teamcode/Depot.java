package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Depot")
public class Depot extends LinearOpMode {

    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

            robot.hardware(hardwareMap, telemetry);

            //for rotating
            //negative=right
            //positive=left

            robot.latch(1, -65);
            robot.rotateRobot(-35);
            robot.latch(1, 60);
            robot.rotateRobot(10);

            robot.driveDistance(.99, -44);
            robot.outake(.99);
            sleep(1500);

            robot.stopDriving();

            //robot.turnRight(.99);
            //sleep(3500);
            //robot.rotateRobot(-100);
            // robot.driveDistance(.99,-100);

        }
    }
