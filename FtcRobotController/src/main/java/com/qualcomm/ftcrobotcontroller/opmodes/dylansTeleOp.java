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
    Servo door;
    int highzone = 1400;
    int midzone = 1000;
    int lowzone = 800;

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
        door = hardwareMap.servo.get("door");
        lift1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        lift2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void loop()
    {
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;
        float right2 = gamepad2.left_stick_y;
        float left2 = gamepad2.right_stick_x;
        boolean x = gamepad1.x;
        boolean rb2 = gamepad2.right_bumper;

        //harvester toggle
        if(x)
           pickup = !pickup;
        if(pickup)
            if(harvester.getPower() == 0.0)
                harvester.setPower(1.0);
        else if(!pickup)
            if(harvester.getPower() == 1.0)
                harvester.setPower(0.0);

        //basket rotation
        if(rb2)
        {
            if(basket.getPosition() == 0.0){
                basket.setPosition(0.5);
                door.setPosition(0.0);
            }
            else if(basket.getPosition() == 0.5){
                basket.setPosition(0.0);
                door.setPosition(0.5);
            }
        }

        //drive train
        rb.setPower(right);
        rf.setPower(right);
        lb.setPower(left);
        lf.setPower(left);

        //lift NO MACROS
        lift1.setPower(-right2);
        lift2.setPower(-right2);

        //automatically open/close basket door
        if(lift1.getCurrentPosition() > 420 && lift1.getPower() > 0)
                basket.setPosition(0.8);
        else if(lift1.getCurrentPosition() < 1300 && lift1.getPower() < 0)
                basket.setPosition(0.0);

        //lift macros
        if(gamepad2.x) //high
        {
            macrox();
        }
        else if(gamepad2.y) //mid
        {
            macroy();
        }
        else if(gamepad2.b) //low
        {
            macrob();
        }

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Lift1", "Lift 1: " + lift1.getCurrentPosition());
        telemetry.addData("Lift2", "Lift 2: " + lift2.getCurrentPosition());
        telemetry.addData("Drive Train", "Drivetrain:\nLeft Stick: " + left + " Right Stick: " + right);
        telemetry.addData("Basket", "Basket position: " + basket.getPosition());
        telemetry.addData("Door", "Basket door: " + door.getPosition());

    }

    //high
    public void macrox()
    {
         lift1.setPower(0.8);
         lift2.setPower(0.8);
         while(lift1.getCurrentPosition() < 420){}
         door.setPosition(.8); // starts to retract door after it passes gear
         while(lift1.getCurrentPosition() < highzone){}
         lift1.setPower(0.0);
         lift2.setPower(0.0);
         basket.setPosition(0.5);
         door.setPosition(0.0);
         while(basket.getPosition() != 0.5){}
        try {wait(3000L);}catch(InterruptedException e) {}
        basket.setPosition(0.0);
         while(basket.getPosition() != 0.0){}
         lift1.setPower(-0.7);
         lift2.setPower(-0.7);
         door.setPosition(0.8);
         while(lift1.getCurrentPosition()> 1200){} //wait to reopen door
         door.setPosition(0.0);
         lift1.setPower(-0.5);
         lift2.setPower(-0.5);
         while(lift1.getCurrentPosition() > 0){}
         lift1.setPower(0.0);
         lift2.setPower(0.0);

    }

    //mid
    public void macroy()
    {
        lift1.setPower(0.8);
        lift2.setPower(0.8);
        while(lift1.getCurrentPosition() < 420){}
        door.setPosition(.8); // starts to retract door after it passes gear
        while(lift1.getCurrentPosition() < midzone){}
        lift1.setPower(0.0);
        lift2.setPower(0.0);
        basket.setPosition(0.5);
        door.setPosition(0.0);
        while(basket.getPosition() != 0.5){}
        try {wait(3000L);}catch(InterruptedException e) {}
        basket.setPosition(0.0);
        while(basket.getPosition() != 0.0){}
        lift1.setPower(-0.7);
        lift2.setPower(-0.7);
        door.setPosition(0.8);
        while(lift1.getCurrentPosition()> 1200){} //wait to reopen door
        door.setPosition(0.0);
        lift1.setPower(-0.5);
        lift2.setPower(-0.5);
        while(lift1.getCurrentPosition() > 0){}
        lift1.setPower(0.0);
        lift2.setPower(0.0);
    }

    //low
    public void macrob()
    {
        lift1.setPower(0.8);
        lift2.setPower(0.8);
        while(lift1.getCurrentPosition() < 420){}
        door.setPosition(.8); // starts to retract door after it passes gear
        while(lift1.getCurrentPosition() < lowzone){}
        lift1.setPower(0.0);
        lift2.setPower(0.0);
        basket.setPosition(0.5);
        door.setPosition(0.0);
        while(basket.getPosition() != 0.5){}
        try {wait(3000L);}catch(InterruptedException e) {}
        basket.setPosition(0.0);
        while(basket.getPosition() != 0.0){}
        lift1.setPower(-0.7);
        lift2.setPower(-0.7);
        door.setPosition(0.8);
        while(lift1.getCurrentPosition()> 1200){} //wait to reopen door
        door.setPosition(0.0);
        lift1.setPower(-0.5);
        lift2.setPower(-0.5);
        while(lift1.getCurrentPosition() > 0){}
        lift1.setPower(0.0);
        lift2.setPower(0.0);
    }
}
