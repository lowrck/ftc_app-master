package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;
import java.util.Timer;
import java.util.TimerTask;

public class MyAutoOpRed1 extends OpMode {

    //Timer Task for ramp motor

    Timer ramp = new Timer();
    // tread motors
    DcMotor motor_1;
    DcMotor motor_2;
    DcMotor motor_3;
    DcMotor motor_4;

	/*
	Slide motors and servos for robotics
	 */

    DcMotor motor_harvest2; // Both of these motors control the basket slide
    DcMotor motor_harvest;  // ^
    Servo servo_1;   // controls the basket;
    Servo servo_2;   //^ Both turn same direction

    @Override
    public void init() {

		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */


        motor_1 = hardwareMap.dcMotor.get("motor_1"); // right side
        motor_2 = hardwareMap.dcMotor.get("motor_2"); // right side
        motor_3 = hardwareMap.dcMotor.get("motor_3"); // left side
        motor_4 = hardwareMap.dcMotor.get("motor_4"); // left side
        motor_harvest = hardwareMap.dcMotor.get("motor_harvest");
        motor_harvest2 = hardwareMap.dcMotor.get("motor_harvest2");
        servo_1 = hardwareMap.servo.get("servo_1");
        servo_2 = hardwareMap.servo.get("servo_2");

        motor_1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_3.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_4.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_harvest.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_harvest2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    @Override
    public void loop()
    {
        //Straight 3 tiles in order to line up with the climber box
        motor_1.setPower(1.0);
        motor_2.setPower(1.0);
        motor_3.setPower(-1.0);
        motor_4.setPower(-1.0);
        while(motor_1.getCurrentPosition() < 5040&&motor_3.getCurrentPosition() > -5040){}
        motor_1.setPower(0.0);
        motor_2.setPower(0.0);
        motor_3.setPower(0.0);
        motor_4.setPower(0.0);
        resetEncoders();

        //turn right to face away from basket
        motor_1.setPower(-1.0);
        motor_2.setPower(-1.0);
        motor_3.setPower(-1.0);
        motor_4.setPower(-1.0);
        while(motor_1.getCurrentPosition() < 840&&motor_3.getCurrentPosition() < 840){}
        motor_1.setPower(0.0);
        motor_2.setPower(0.0);
        motor_3.setPower(0.0);
        motor_4.setPower(0.0);
        resetEncoders();

        //Straight 2 tiles in order to go to climber box
        motor_1.setPower(-1.0);
        motor_2.setPower(-1.0);
        motor_3.setPower(1.0);
        motor_4.setPower(1.0);
        while(motor_1.getCurrentPosition() < 3360&&motor_3.getCurrentPosition() > -3360){}
        motor_1.setPower(0.0);
        motor_2.setPower(0.0);
        motor_3.setPower(0.0);
        motor_4.setPower(0.0);
        resetEncoders();

        //lift basket towards box
        motor_harvest.setPower(0.7);
        motor_harvest2.setPower(0.7);
        while(motor_harvest.getCurrentPosition() < 2800 && motor_harvest2.getCurrentPosition() < 2800){}
        motor_harvest.setPower(0.0);
        motor_harvest2.setPower(0.0);

        //turn basket to score
        servo_1.setPosition(90);
        servo_2.setPosition(90);
        while(servo_1.getPosition() != 90 && servo_2.getPosition() != 90){}
        try{wait(3000L);}catch (InterruptedException e){}
        servo_1.setPosition(0);
        servo_2.setPosition(0);
        while(servo_1.getPosition() != 0 && servo_2.getPosition() != 0){}

        //lower slides
        motor_harvest.setPower(-0.5);
        motor_harvest2.setPower(-0.5);
        while(motor_harvest.getCurrentPosition() > 0 && motor_harvest2.getCurrentPosition() > 0){}
        motor_harvest.setPower(0.0);
        motor_harvest2.setPower(0.0);

        //head straight 3 to the opposite ramp
        motor_1.setPower(1.0);
        motor_2.setPower(1.0);
        motor_3.setPower(-1.0);
        motor_4.setPower(-1.0);
        while(motor_1.getCurrentPosition() < 2940&&motor_3.getCurrentPosition() > -2940){}
        motor_1.setPower(0.0);
        motor_2.setPower(0.0);
        motor_3.setPower(0.0);
        motor_4.setPower(0.0);
        resetEncoders();

        //turn right to face away from ramp
        motor_1.setPower(-1.0);
        motor_2.setPower(-1.0);
        motor_3.setPower(-1.0);
        motor_4.setPower(-1.0);
        while(motor_1.getCurrentPosition() < 1260&&motor_3.getCurrentPosition() < 1260){}
        motor_1.setPower(0.0);
        motor_2.setPower(0.0);
        motor_3.setPower(0.0);
        motor_4.setPower(0.0);
        resetEncoders();

        //GO ONTO THE RAMP
        motor_1.setPower(-1.0);
        motor_2.setPower(-1.0);
        motor_3.setPower(1.0);
        motor_4.setPower(1.0);
        while(motor_1.getCurrentPosition() > -2940&&motor_3.getCurrentPosition() < 2940){}
        motor_1.setPower(0.0);
        motor_2.setPower(0.0);
        motor_3.setPower(0.0);
        motor_4.setPower(0.0);
        resetEncoders();

        try {wait(30000L);} catch (InterruptedException e) {}

    }
    public void resetEncoders()
    {
        motor_1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor_2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor_3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor_4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor_1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_3.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor_4.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

    @Override
    public void stop(){

    }
}
