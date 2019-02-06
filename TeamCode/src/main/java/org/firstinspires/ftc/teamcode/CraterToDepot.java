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

        robot.latchNotEncoder(1);
        sleep(2000);
        robot.rotateRobot(-50);
        robot.latchNotEncoder(-1);
        sleep(2500);
        robot.rotateRobot(10);

        robot.driveDistance(1, -37);
        robot.rotateRobot(90);
        robot.driveDistance(1,-37);
        robot.rotateRobot(50);
        robot.driveDistance(1, -65);
        robot.outake(-1);
        sleep(2000);
        robot.driveDistance(1, 105);


        /*robot.driveDistance(1, -11);
        robot.turnRight(1);
        sleep(2000);
        robot.driveDistance(1, -40);
        robot.turnRight(1);
        sleep(2000);
        robot.driveDistance(1,-40);

        robot.driveDistance(1,10);
        robot.driveDistance(1,-10);

        robot.driveDistance(1,105);
        */

    }
 /*        lander to depot =  53 inches
           depot to crater = 105 inches
           lander to crater = 41 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
           hook to ground =
         */

}