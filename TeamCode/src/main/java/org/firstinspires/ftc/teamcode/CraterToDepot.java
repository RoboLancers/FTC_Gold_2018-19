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

        robot.latchNotEncoder(-.99);
        sleep(2000);
        robot.rotateRobot(50);
        robot.latchNotEncoder(.99);
        sleep(1800);
        robot.rotateRobot(-10);

        robot.driveDistance(.99, 20);

        robot.rotateRobot(-90);
        robot.driveDistance(.99,40);
        robot.rotateRobot(50);
        robot.driveDistance(.99,40);
        //robot.outake(.99);
        //sleep(2000);
        robot.driveDistance(.99,-100);


    }
        /* lander to 'depot =  49 inches
           depot to crater = 85 inches
           lander to crater = 38 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
         */

}