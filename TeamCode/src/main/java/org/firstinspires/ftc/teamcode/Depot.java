package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Depot")
public class Depot extends LinearOpMode {

    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.hardware(hardwareMap, telemetry);

        waitForStart();

        //for rotating
        //negative=right
        //positive=left

        robot.latch(1,-70);
        robot.rotateRobot(-50);
        robot.latch(1,60);
        robot.rotateRobot(5);

        robot.driveDistance(.99,-38);
        robot.outake(.99);
        sleep(1500);

        robot.stopDriving();

        robot.rotateRobot(-120);
        robot.driveDistance(.99,-100);


       /* robot.latch(.99, -70);
        robot.turnRight(.99);
        sleep(6000);
        robot.latch(.99,60);
        robot.turnLeft(.99);
        sleep(6000);
        robot.driveDistance(.99,50);
        robot.rotateRobot(25);
        robot.driveDistance(.99,50);
*/
    }
}