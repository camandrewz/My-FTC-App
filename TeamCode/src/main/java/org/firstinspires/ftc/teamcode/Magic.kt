package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo

/**
 * Created by towerlab1 on 12/7/17.
 */
@TeleOp(name = "magicTest", group = "test")
class Magic: OpMode() {

    lateinit var servo: Servo

    override fun init() {
        servo = hardwareMap.servo.get("servo1")
        val servo2 = hardwareMap.servo.get("servo2")
        servo.position = 0.0
        servo2.position = 1.0
    }

    override fun loop() {
    }
}