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
        //default is left for rotating
//      default is backwards for driving w/ encoders

        robot.latchNotEncoder(1);
        sleep(2000);
        robot.rotateRobot(-50); /*right*/
        robot.latchNotEncoder(-1);
        sleep(1500);
        robot.rotateRobot(10);

        robot.driveDistance(1,-2);

        //robot.outake(-1);
        //sleep(2000);
        robot.rotateRobot(100);
        //robot.turnRight(1);
        //sleep(500);
      //  robot.driveDistance(1,105);

 /*        lander to depot =  53 inches
           depot to crater = 105 inches
           lander to crater = 41 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
           hook to ground =
         */

    }
}