package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "CraterToDepot")
public class CraterToDepot extends LinearOpMode {

    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.hardware(hardwareMap, telemetry);

        waitForStart();

        robot.latch(1,-70);
        robot.rotateRobot(-50);
        robot.latch(1,60);
        robot.rotateRobot(5);

        robot.driveDistance(.99, -20);

        robot.stopDriving();

        robot.rotateRobot(85);
      /*  robot.driveDistance(1,-40);

        robot.stopDriving();

        robot.rotateRobot(60);
        robot.driveDistance(1,-60);

        robot.stopDriving();

        robot.outake(.99);
        sleep(2000);
        robot.driveDistance(1,100);
        */
    }
        /* lander to 'depot =  49 inches
           depot to crater = 85 inches
           lander to crater = 38 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
         */

}