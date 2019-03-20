package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "CraterToDepot")
public class CraterToDepot extends LinearOpMode {

    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        //while(opModeIsActive()) {

            robot.hardware(hardwareMap, telemetry);

            robot.latch(1, -70);
            robot.rotateRobot(-25);
            robot.latch(1, 60);
            robot.rotateRobot(1);

            robot.driveDistance(.99, -20);

            robot.stopDriving();

           // robot.turnLeft(.99);
            sleep(2000);
            //robot.rotateRobot(85);
            //robot.driveDistance(.99,-40);
            //robot.turnLeft(.99);
            //sleep(1500);
            //robot.driveDistance(.99,-40);
            //robot.outake(.99);
            //sleep(1500);
            //robot.driveDistance(.99,100);
        }
    }

//}