package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Color Sensor", group = "Sensor")
//@Disabled
public class Color extends LinearOpMode {

  ColorSensor colorSensor;    // Hardware Device Object
  private DcMotor motor = null;
  private Servo arm = null;


  @Override
  public void runOpMode() throws InterruptedException {

    float hsvValues[] = {0F,0F,0F};

    final float values[] = hsvValues;

    int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());

    final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

    boolean bPrevState = false;
    boolean bCurrState = false;

    boolean bLedOn = true;
///////////////////////////////////////////////////////////////////////////////////////////////////////
    colorSensor = hardwareMap.get(ColorSensor.class, "color");
    motor = hardwareMap.get(DcMotor.class, "motor");
    arm = hardwareMap.get(Servo.class, "arm");
///////////////////////////////////////////////////////////////////////////////////////////////////////
    colorSensor.enableLed(bLedOn);

    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    waitForStart();

    while (opModeIsActive()) {

      bCurrState = gamepad1.x;

      if (bCurrState && (bCurrState != bPrevState))  {

        bLedOn = !bLedOn;
        colorSensor.enableLed(bLedOn);
      }

      bPrevState = bCurrState;

      android.graphics.Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

      telemetry.addData("LED", bLedOn ? "On" : "Off");
      telemetry.addData("Red  ", colorSensor.red());
      telemetry.addData("Blue ", colorSensor.blue());
      telemetry.addData("Hue", hsvValues[0]);

////////////////////////////////////////////////////////

      sleep(1000);

      arm.setPosition(1);

////////////////////////////////////////////////////////


      if (1 < colorSensor.blue()) {
          motor.setTargetPosition(-1120);
          motor.setPower(1);
        }

      if (1 < colorSensor.red()) {
        motor.setTargetPosition(1120);
        motor.setPower(1);
      }

////////////////////////////////////////////////////////
      relativeLayout.post(new Runnable() {
        public void run() {
          relativeLayout.setBackgroundColor(android.graphics.Color.HSVToColor(0xff, values));
        }
      });

      telemetry.update();
    }

    relativeLayout.post(new Runnable() {
      public void run() {
        relativeLayout.setBackgroundColor(android.graphics.Color.WHITE);
      }
    });
  }
}
