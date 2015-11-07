package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by lewis on 11/7/15.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.util.Timer;
import java.util.TimerTask;

public class MyAutoOp extends OpMode {

    //Timer Task for ramp motor

    Timer ramp = new Timer();

    /*
     * Note: the configuration of the servos is such that
     * as the arm servo approaches 0, the arm position moves up (away from the floor).
     * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
     */
    // tread motors
    DcMotor motor_1;
    DcMotor motor_2;
    DcMotor motor_3;
    DcMotor motor_4;

    Servo servo_1;

	/*
	harvester motors for robotics
	 */

    DcMotor harvester_init;
    DcMotor harvester_ramp;


    /**
     * Constructor
     */
}
