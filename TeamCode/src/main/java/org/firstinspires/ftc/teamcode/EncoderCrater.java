package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.firstinspires.ftc.teamcode.utility.vision.FieldView;
import org.firstinspires.ftc.teamcode.utility.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;

@Autonomous(name = "EncoderCrater")
public class EncoderCrater extends LinearOpMode {
    Robot robot = new Robot();
    TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {
        tensorflowWrapper = new TensorflowWrapper(hardwareMap,FieldView.LEFT);
        tensorflowWrapper.activateTfod();
        robot.hardware(hardwareMap, telemetry);

        waitForStart();

        /* robot.latchOpen(1.0);
        sleep(2000);
        robot.rotateRobotLeft(-30);
        robot.latchClose(1.0);
        sleep(1600);

        robot.rotateRobotRight(-30);
        robot.driveDistance(1.0, 11);

        robot.rotateRobotLeft(-90);
        robot.driveDistance(1.0, 40);
        robot.rotateRobotLeft(-50);

        robot.driveDistance(1.0, 40);

        robot.driveDistance(1.0, -105);
        */

        while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
            tensorflowWrapper.updateRecognition();
            tensorflowWrapper.updateMineralPosition();

            telemetry.addData("# Object Detected", tensorflowWrapper.getNumberOfObjectsDetected());
            telemetry.addData("Gold X", tensorflowWrapper.getFirstGoldMineralX());
            telemetry.update();
        }
            tensorflowWrapper.shutdown();
        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT){
            robot.rotateRobotLeft(15);
            robot.driveDistance(1,11);
            robot.rotateRobotLeft(100);
            robot.driveDistance(1,100);



        }


    }

}


        /* lander to depot =  53 inches
           depot to crater = 105 inches
           lander to crater = 41 inches
           lander to minerals = 11 inches
           minerals to side wall = 40 inches
           side wall to depot = 40 inches
         */
