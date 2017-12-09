package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "DriveTest", group = "Holonomic")
//@Disabled
public class Drive extends OpMode {

    // Declare OpMode members.
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontRight = hardwareMap.get(DcMotor.class, "fr");
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        backLeft = hardwareMap.get(DcMotor.class, "bl");
        backRight = hardwareMap.get(DcMotor.class, "br");

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {

    double ForBack;

        double forback = -gamepad1.left_stick_y;

        ForBack = Range.clip(forback , -1.0, 1.0) ;

        frontRight.setPower(ForBack);
        frontLeft.setPower(ForBack);
        backLeft.setPower(ForBack);
        backRight.setPower(ForBack);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        double Horizontal;

        double horizontal = -gamepad1.right_stick_x;

        Horizontal = Range.clip(horizontal , -1.0, 1.0) ;

        if (Horizontal != 0){

            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            frontLeft.setPower(Horizontal);
            frontRight.setPower(Horizontal);
            backRight.setPower(Horizontal);
            backLeft.setPower(Horizontal);
        }

        else {
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.FORWARD);
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (gamepad1.x) {
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.FORWARD);
            frontLeft.setPower(1);
            frontRight.setPower(1);
            backRight.setPower(1);
            backLeft.setPower(1);
        }

        if (gamepad1.b) {
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.FORWARD);
            frontLeft.setPower(-1);
            frontRight.setPower(-1);
            backRight.setPower(-1);
            backLeft.setPower(-1);
        }

    }
}
