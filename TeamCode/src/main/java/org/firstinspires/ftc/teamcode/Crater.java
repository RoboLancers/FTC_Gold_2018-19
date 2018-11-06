package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Crater")
public class Crater extends LinearOpMode {
    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.hardware(hardwareMap);

        waitForStart();

     /*   robot.latchOpen(1.0);
        sleep(2500);
        robot.latchClose(1.0);
        sleep(2500);
*/
        robot.driveForward(0.75);
        sleep(3000);


        /*
        driveForward(0.5);
        sleep(2000);
        turnLeft(0.5);
        sleep(500);
        driveForward(0.5);
        sleep(2000);
        turnRight(0.5);
        sleep(500);
        driveForward(0.5);
        sleep(2000);
        */
    }


}
