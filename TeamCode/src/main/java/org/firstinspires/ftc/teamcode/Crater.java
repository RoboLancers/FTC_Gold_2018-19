package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utility.vision.FieldView;
import org.firstinspires.ftc.teamcode.utility.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;

@Autonomous(name = "Crater")
public class Crater extends LinearOpMode {

    Robot robot = new Robot();
    TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        TensorflowWrapper tensorflowWrapper = new TensorflowWrapper(hardwareMap, FieldView.LEFT);
        tensorflowWrapper.activateTfod();

            robot.hardware(hardwareMap, telemetry);

            robot.latch(1, -70);
            robot.rotateRobot(-35);
            robot.latch(1, 60);
            robot.rotateRobot(10);

            robot.driveDistance(.99, -32);

            robot.stopDriving();


        //sampling
        //we dont know field view yet

      /*  tensorflowWrapper.detectMinerals(30, this);

        while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
              tensorflowWrapper.updateRecognition();
              tensorflowWrapper.updateMineralPosition();

            telemetry.addData("# Object Detected", tensorflowWrapper.getNumberOfObjectsDetected());
            telemetry.addData("Gold X", tensorflowWrapper.getFirstGoldMineralX());
            telemetry.update();
        }

        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT) {
            robot.turnRight(-0.5);
            sleep(250);
            //robot.rotateRobot(15);
            //robot.driveDistance(1, 15);
        } else if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.CENTER){
            robot.driveDistance(.99,-15);
        }else
            {
            robot.turnRight(0.5);
            sleep(250);
        }

        tensorflowWrapper.shutdown(); */

    }

    }
