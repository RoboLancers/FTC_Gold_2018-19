package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Depot")
public class Depot extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.hardware2(hardwareMap);

        waitForStart();

        robot.latchNotEncoder(0.5);
        sleep(3000);
        robot.turnRight(1.0);
        sleep(500);
        robot.latchNotEncoder(-0.5);
        sleep(2500);
        robot.turnRight(-1.0);
        sleep(500);


        robot.driveDistance(1,53);
        robot.driveDistance(1,-10);
        robot.driveDistance(1,10);

       /* robot.turnRight(1);
        sleep(2000);
        robot.StopDriving();
        sleep(500);
        robot.driveDistance(1,105);

*/




    }
}