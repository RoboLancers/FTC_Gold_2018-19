package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utility.vision.FieldView;
import org.firstinspires.ftc.teamcode.utility.vision.MineralPosition;
import org.firstinspires.ftc.teamcode.utility.vision.TensorflowWrapper;
import org.firstinspires.ftc.teamcode.utility.vision.MineralPosition;

@Autonomous (name = "EncoderDepot")
public class EncodersDepot extends LinearOpMode {
    Robot robot = new Robot();
    TensorflowWrapper tensorflowWrapper;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.hardware(hardwareMap, telemetry);
        tensorflowWrapper = new TensorflowWrapper(hardwareMap, FieldView.LEFT);
        tensorflowWrapper.activateTfod();

        waitForStart();

       /* robot.latchOpen(1.0);
        sleep(1600);
        robot.rotateRobotLeft(-30);
        robot.latchClose(1.0);
        sleep(1600);
        robot.rotateRobotRight(-30);

        robot.driveDistance(1.0,53);
        robot.driveDistance(1.0, -5);
        robot.driveDistance(1.0, 5);

        robot.rotateRobotLeft(-140);
        robot.driveDistance(1.0, 105);
        robot.driveDistance(1,86);
*/

        while (tensorflowWrapper.getFirstGoldMineralX() == -1) {
            tensorflowWrapper.updateRecognition();
            tensorflowWrapper.updateMineralPosition();

            telemetry.addData("# Object Detected", tensorflowWrapper.getNumberOfObjectsDetected());
            telemetry.addData("Gold X", tensorflowWrapper.getFirstGoldMineralX());
            telemetry.update();
        }

        tensorflowWrapper.shutdown();

        if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.LEFT) {
            robot.rotateRobotLeft(15);
            robot.driveDistance(1, 11);
            robot.rotateRobotRight(30);
            robot.driveDistance(1, 15);
            robot.rotateRobotRight(90);
            robot.driveDistance(1,96);
        } else if (tensorflowWrapper.getGoldMineralPosition() == MineralPosition.RIGHT) {
            robot.rotateRobotRight(15);
            robot.driveDistance(1, 11);
            robot.rotateRobotLeft(30);
            robot.driveDistance(1, 15);
            robot.rotateRobotRight(180);
            robot.driveDistance(1,96);
        } else {
            robot.driveDistance(1, 11);
            robot.rotateRobotRight(90);
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
