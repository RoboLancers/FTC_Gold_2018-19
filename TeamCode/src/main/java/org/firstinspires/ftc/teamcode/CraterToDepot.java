package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "Crater2")
public class Crater2 extends LinearOpMode {

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

        robot.driveDistance(1, 11);
        robot.latch(1,5);
        /*robot.turnRight(-1.0);
        sleep(1000);
        robot.driveDistance(1,40);
        robot.turnRight(-1.0);
        sleep(1000);
        robot.driveDistance(1,40);
*/
        //robot.driveDistance(1,-5);
        //robot.driveDistance(1,5);
        //robot.driveDistance(1,-105);

    }


}