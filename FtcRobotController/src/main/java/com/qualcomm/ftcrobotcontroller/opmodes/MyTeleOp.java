/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.util.Timer;
import java.util.TimerTask;


/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */


public class MyTeleOp extends OpMode {


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

	float lbumperval = 0f;


	/*
	harvester motors for robotics
	 */

	DcMotor harvester_init;
	DcMotor motor_harvest;


	/**
	 * Constructor
	 */
	public MyTeleOp() {

	}

	/*
	 * Code to run when the op mode is initialized goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
	 */
	@Override
	public void init() {

		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */
		
		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot and reversed.
		 *   
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
        motor_1 = hardwareMap.dcMotor.get("motor_1");
		motor_2 = hardwareMap.dcMotor.get("motor_2");
		motor_3 = hardwareMap.dcMotor.get("motor_3");
		motor_4 = hardwareMap.dcMotor.get("motor_4");
		motor_harvest = hardwareMap.dcMotor.get("motor_harvest");
        servo_1 = hardwareMap.servo.get("servo_1");
        }

	/*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop() {

		/*
		 * Gamepad 1
		 * 
		 * Gamepad 1 controls the motors via the left stick, and it controls the
		 * wrist/claw via the a,b, x, y buttons
		 */

        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
        // 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left
        // and 1 is full right
        float lefty = gamepad1.left_stick_y;
        float righty = gamepad1.right_stick_y;
        float leftt = gamepad1.left_trigger;
        float rightt = gamepad1.right_trigger;
        boolean triggerdump = gamepad1.y;
		float triggerrail = gamepad1.right_trigger;
		boolean rbumper = gamepad1.right_bumper;
		boolean lbumper = gamepad1.left_bumper;
		float rbumpervalue = 0;

        float triggerdumpvalue = 0;

            if (triggerdump == true)
            {
                triggerdumpvalue = 0.5f;
            }
            else
            {
                triggerdumpvalue = 0;
            }
		if (rbumper == true)
		{
			rbumpervalue = 1;
		}
		else
		{
			rbumpervalue = 0;
		}


	/* if (triggerrail == 100 ) {
		ramp.schedule(new TimerTask() {
			@Override
			public void run() {
			harvester_ramp.setPower(1);

			}
		}, 2*60*1000);
		harvester_ramp.setPower(0);
		if (rbumpervalue == 1) {
			harvester_ramp.setPower(-1);
		}
		else {
			harvester_ramp.setPower(0);
		}
	}
	*/

		if (lbumper == false) {
			lbumperval = leftt;
		}
		else if (lbumper == true){
			lbumperval = -0.7f;
		}
		else {
			lbumperval = 0.0f;
		}
		motor_harvest.setPower(lbumperval);




        lefty = (float) ((lefty*32.0)/32.0);
        righty = (float) ((righty*32.0)/32.0);
		// write the values to the motors
		motor_1.setPower(-lefty);
		motor_2.setPower(-lefty);
        motor_3.setPower(-righty);
		motor_4.setPower(-righty);
        servo_1.setPosition(triggerdumpvalue);

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.3f", lefty));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.3f", righty));
        telemetry.addData("servo controller", "servo is" + String.format("%.3f", triggerdumpvalue));

	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

	}
	
	/*
	 * This method scales the joystick input so for low joystick values, the 
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */

	double scaleInput(double dVal)  {
		double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
		
		// get the corresponding index for the scaleInput array.
		int index = (int) (dVal * 16.0);
        //double value = (dVal * 16.0) / 64.0;

		if (index < 0) {
			index = -index;
		} else if (index > 16) {
			index = 16;
		}
		
		double dScale = 0.0;
		if (dVal < 0) {
			dScale = -scaleArray[index];
		} else {
			dScale = scaleArray[index];
		}
		
		return dScale;
	}

}