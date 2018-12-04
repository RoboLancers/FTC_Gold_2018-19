package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

@Autonomous (name = "tryEncoders")
public class tryEncoders extends LinearOpMode {
    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {

        robot.hardware(hardwareMap, telemetry);
        waitForStart();

        robot.rotateRobotLeft(-90);
//    //drive forward
//    telemetry.addLine("driving backwards");
//    robot.driveDistance(0.5, -10);
//
//sleep(500);
//    robot.rotateRobot(90);

    }
}
