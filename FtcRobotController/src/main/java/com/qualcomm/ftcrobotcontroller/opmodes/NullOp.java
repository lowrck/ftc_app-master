package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by lewis on 11/14/15.
 */

public class NullOp extends OpMode {

  DcMotor motor_1;
  //DcMotor motor_2;
  //DcMotor motor_3;
  //DcMotor motor_4;



  public void init () {



    int DoOnce = 0;
    motor_1 = hardwareMap.dcMotor.get("motor_1");
    //motor_2 = hardwareMap.dcMotor.get("motor_2");
    //motor_3 = hardwareMap.dcMotor.get("motor_3");
    //motor_4 = hardwareMap.dcMotor.get("motor_4");


  }

  public void loop () {


    for(int i=1; i<2; i++){
      motor_1.setTargetPosition(280);
      int position_1 = motor_1.getCurrentPosition();
      telemetry.addData("Position 1", position_1);
    }

  }
  public void stop(){}
}
