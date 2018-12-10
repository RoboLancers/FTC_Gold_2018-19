package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.vision.TensorflowWrapper;

@Autonomous (name = "EncoderDepot")

public class EncodersDepot extends LinearOpMode {
    Robot robot = new Robot();
    TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.hardware(hardwareMap, telemetry);
        tensorflowWrapper = new TensorflowWrapper(hardwareMap);


        waitForStart();


        robot.latch(1.0, 5);
        robot.rotateRobotLeft(-30);
        robot.latch(1.0,-5);
        robot.rotateRobotRight(-30);

        tensorflowWrapper.activateTfod();

        robot.driveDistance(1.0,53);
        robot.driveDistance(1.0, -5);
        robot.driveDistance(1.0, 5);

        robot.rotateRobotLeft(-140);
        robot.driveDistance(1.0, 105);
        //robot.driveDistance(1,86);

     /*   //should sample here
        while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
            tensorflowWrapper.updateRecognition();
            tensorflowWrapper.updateMineralPosition();

            telemetry.addData("# Object Detected", tensorflowWrapper.getNumberOfObjectsDetected());
            telemetry.addData("Gold X", tensorflowWrapper.getFirstGoldMineralX());
            telemetry.update();
        }

        tensorflowWrapper.shutdown();

        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT) {
            robot.rotateRobot(15);
            robot.driveDistance(1, 17);
            robot.rotateRobot(-30);
            robot.driveDistance(1, 15);
            robot.rotateRobot(-90);
            robot.driveDistance(1,96);
        } else if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.RIGHT) {
            robot.rotateRobot(-15);
            robot.driveDistance(1, 17);
            robot.rotateRobot(30);
            robot.driveDistance(1, 15);
            robot.rotateRobot(180);
            robot.driveDistance(1,96);
        } else {
            robot.driveDistance(1, 17);
            robot.rotateRobot(-90);
            robot.driveDistance(1,96);

        }
        /* lander to depot =  53 inches
           depot to crater = 105 inches
           lander to crater = 41 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
           hook to ground =
         */

    }
}
