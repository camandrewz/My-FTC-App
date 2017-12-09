package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Polam", group="Linear Opmode")
//@Disabled
public class Extender extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //private Servo Grab = null;
   // private DcMotor Lift = null;
    private DcMotor extend = null;
    private DcMotor pull = null;



    @Override
    public void runOpMode()  {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


      //  Grab = hardwareMap.get(Servo.class, "grab");
        //Lift = hardwareMap.get(DcMotor.class, "lift");
        extend = hardwareMap.get(DcMotor.class, "extend");
        pull = hardwareMap.get(DcMotor.class, "pull");


        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            if (gamepad1.a) {
          //      Grab.setPosition(0);
            }
            else {
            //    Grab.setPosition(1);
            }

            if (gamepad1.dpad_up) {
              //  Lift.setPower(.3);
            }
            else {
                //Lift.setPower(0);
            }

            if (gamepad1.dpad_down) {
               // Lift.setPower(-.3);
            }
            else {
               // Lift.setPower(0);
            }

            if (gamepad1.right_bumper) {
                extend.setPower(-1);
                pull.setPower(-.1);
            }
            else {
                extend.setPower(0);
                pull.setPower(0);
            }

            if (gamepad1.left_bumper) {
                extend.setPower(.1);
                pull.setPower(1);
            }
            else {
                extend.setPower(0);
                pull.setPower(0);
            }



        }
    }
}
