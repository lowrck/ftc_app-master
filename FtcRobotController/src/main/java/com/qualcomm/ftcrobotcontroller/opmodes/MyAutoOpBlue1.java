package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.Timer;

public class MyAutoOpBlue1 extends LinearOpMode {


    DcMotor rf;
    DcMotor rb;
    DcMotor lf;
    DcMotor lb;
    DcMotor lift2;
    DcMotor lift1;
    DcMotor harvester;
    DcMotor retractor;
    Servo basket;
    Servo door;

    @Override
    public void runOpMode() {

		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */


        rf = hardwareMap.dcMotor.get("rf"); //right
            rb = hardwareMap.dcMotor.get("rb");
            lf = hardwareMap.dcMotor.get("lf"); //left
            lb = hardwareMap.dcMotor.get("lb");
            lift1 = hardwareMap.dcMotor.get("lift"); //RIGHT lift
            lift2 = hardwareMap.dcMotor.get("lift2"); //LEFT lift
            harvester = hardwareMap.dcMotor.get("harvester");
            retractor = hardwareMap.dcMotor.get("retractor");
            harvester.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            lift2.setDirection(DcMotor.Direction.REVERSE);
            basket = hardwareMap.servo.get("basket"); //basket servo
            door = hardwareMap.servo.get("door");


            //waitForStart();

        //Straight 4 tiles in order to line up with the climber box
        rb.setPower(1.0);
        rf.setPower(1.0);
        lf.setPower(-1.0);
        lb.setPower(-1.0);
        while(rf.getCurrentPosition() < 5040&&lf.getCurrentPosition() > -5040){}
        rf.setPower(0.0);
        rb.setPower(0.0);
        lf.setPower(0.0);
        lb.setPower(0.0);
        resetEncoders();

        //turn left to face away from basket
        rb.setPower(1.0);
        rf.setPower(1.0);
        lf.setPower(1.0);
        lb.setPower(1.0);
        while(rf.getCurrentPosition() < 840&&lf.getCurrentPosition() < 840){}
        rf.setPower(0.0);
        rb.setPower(0.0);
        lf.setPower(0.0);
        lb.setPower(0.0);
        resetEncoders();

        //Straight 3 tiles in order to go to climber box
        rf.setPower(-1.0);
        rb.setPower(-1.0);
        lf.setPower(1.0);
        lb.setPower(1.0);
        while(rf.getCurrentPosition() < 3360&&lf.getCurrentPosition() > -3360){}
        rf.setPower(0.0);
        rb.setPower(0.0);
        lf.setPower(0.0);
        lb.setPower(0.0);
        resetEncoders();

        //lift basket towards box
        lift1.setPower(0.7);
        lift2.setPower(0.7);
        retractor.setPower(0.7);
        door.setPosition(0.8);
        while(lift1.getCurrentPosition() < 2800 && lift2.getCurrentPosition() < 2800){}
        lift1.setPower(0.0);
        lift2.setPower(0.0);
        retractor.setPower(0.0);
        //turn basket to score
        basket.setPosition(0.8);
        door.setPosition(0.1);
        while(basket.getPosition() != 90){}
        try{wait(3000L);}catch (InterruptedException e){}
        basket.setPosition(0);
        while(basket.getPosition() != 0){}

        //lower slides
        lift1.setPower(-0.5);
        lift2.setPower(-0.5);
        retractor.setPower(-0.5);
        door.setPosition(0.3);
        while(lift1.getCurrentPosition() > 0 && lift2.getCurrentPosition() > 0){}
        lift1.setPower(0.0);
        lift2.setPower(0.0);
        retractor.setPower(0.0);

        //head straight 3 to the opposite ramp
        rf.setPower(1.0);
        rb.setPower(1.0);
        lf.setPower(-1.0);
        lb.setPower(-1.0);
        while(rf.getCurrentPosition() < 2940&&lf.getCurrentPosition() > -2940){}
        rf.setPower(0.0);
        rb.setPower(0.0);
        lf.setPower(0.0);
        lb.setPower(0.0);
        resetEncoders();

        //turn left to face away from ramp
        rf.setPower(1.0);
        rb.setPower(1.0);
        lf.setPower(1.0);
        lb.setPower(1.0);
        while(rf.getCurrentPosition() < 1260&&lf.getCurrentPosition() < 1260){}
        rf.setPower(0.0);
        rb.setPower(0.0);
        lf.setPower(0.0);
        lb.setPower(0.0);
        resetEncoders();

        //GO ONTO THE RAMP
        rf.setPower(-1.0);
        rb.setPower(-1.0);
        lf.setPower(1.0);
        lb.setPower(1.0);
        while(rf.getCurrentPosition() > -2940&&lb.getCurrentPosition() < 2940){}
        rf.setPower(0.0);
        rb.setPower(0.0);
        lf.setPower(0.0);
        lb.setPower(0.0);
        resetEncoders();

        try {wait(30000L);} catch (InterruptedException e) {}

    }
    public void resetEncoders()
    {
        rf.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rb.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        lf.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        lb.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
}
