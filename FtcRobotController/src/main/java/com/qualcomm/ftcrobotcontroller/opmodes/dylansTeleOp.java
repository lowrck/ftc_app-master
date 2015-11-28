package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Dylan on 11/21/15.
 */
public class dylansTeleOp extends OpMode {

    DcMotor rf;
    DcMotor rb;
    DcMotor lf;
    DcMotor lb;
    DcMotor lift2;
    DcMotor lift1;
    DcMotor harvester;
    boolean pickup = false;
    Servo hook1;
    Servo hook2;
    Servo basket;

    @Override
    public void init() {
        rf = hardwareMap.dcMotor.get("rf"); //right
        rb = hardwareMap.dcMotor.get("rb");
        lf = hardwareMap.dcMotor.get("lf"); //left
        lb = hardwareMap.dcMotor.get("lb");
        lift1 = hardwareMap.dcMotor.get("lift"); //lift
        lift2 = hardwareMap.dcMotor.get("lift2"); //lift
        harvester = hardwareMap.dcMotor.get("harvester");
        lift2.setDirection(DcMotor.Direction.REVERSE);
        //hook1 = hardwareMap.servo.get("hook1"); //servo hooks
        //hook2 = hardwareMap.servo.get("hook2");
        basket = hardwareMap.servo.get("basket"); //basket servo

    }

    public void loop()
    {
        float left = gamepad1.left_stick_y;


    }
}
