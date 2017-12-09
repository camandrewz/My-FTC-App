package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@TeleOp(name="Vuforia", group ="Concept")
public class Vuforia extends LinearOpMode {

    OpenGLMatrix lastLocation = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    VuforiaLocalizer vuforia;
    @Override public void runOpMode() {

        frontLeft = hardwareMap.dcMotor.get("fl");
        frontRight = hardwareMap.dcMotor.get("fr");
        backLeft = hardwareMap.dcMotor.get("bl");
        backRight = hardwareMap.dcMotor.get("br");

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AfniZh7/////AAAAGS2G2gvvB0HXgLu9CSBfq0gF3Gokw3P+U5cc1TQecCQFkKCYpNHlCdNPElvW1UPmmv/xU+RHmnxirkNCZf+r23EWW3iqULuUYpl/FASP903miM/wjF1/SO7aWqjXQ1nZ+Xw2Dhi0b2CrWwCOct4CrKcSsxB0XbE/udAHkLcMhIO57zsO0yot19s2NDUJqefCtPSI+9U7RJJkxr6YynMRPdEhHbfDmb1GCKum6qN8lDbyWfG/nH3PvQx5GTOjXwOy7ox+yRBhddmrCJsa+6dLFgKAkJ4FUyl3ZYOwBCTcwIc40zem+QVY09FPoMpRL4YVTJH5hHqIGNVq5IBfaGMTQnfXmJqVMtdGKJXpAmkkDCB2";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
            }

            frontLeft.setTargetPosition(1120);
            frontRight.setTargetPosition(1120);
            backLeft.setTargetPosition(1120);
            backRight.setTargetPosition(1120);




//////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*            if (vuMark == RelicRecoveryVuMark.CENTER) {
                frontLeft.setTargetPosition(1120);
                frontRight.setTargetPosition(1120);
                backLeft.setTargetPosition(1120);
                backRight.setTargetPosition(1120);
            }

            else {
                frontLeft.setTargetPosition(0);
                frontRight.setTargetPosition(0);
                backLeft.setTargetPosition(0);
                backRight.setTargetPosition(0);
            }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

            if (vuMark == RelicRecoveryVuMark.LEFT) {
                frontLeft.setTargetPosition(1120);
                frontRight.setTargetPosition(1120);
                backLeft.setTargetPosition(1120);
                backRight.setTargetPosition(1120);
            }

            else {
                frontLeft.setTargetPosition(0);
                frontRight.setTargetPosition(0);
                backLeft.setTargetPosition(0);
                backRight.setTargetPosition(0);
            }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

            if (vuMark == RelicRecoveryVuMark.RIGHT) {
                frontLeft.setTargetPosition(1120);
                frontRight.setTargetPosition(1120);
                backLeft.setTargetPosition(1120);
                backRight.setTargetPosition(1120);
            }

            else {
                frontLeft.setTargetPosition(0);
                frontRight.setTargetPosition(0);
                backLeft.setTargetPosition(0);
                backRight.setTargetPosition(0);
            }
*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

            {
                telemetry.update();
            }
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
