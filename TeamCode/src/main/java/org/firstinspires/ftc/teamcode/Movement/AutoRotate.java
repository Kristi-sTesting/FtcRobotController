package org.firstinspires.ftc.teamcode.Movement;

import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoRotate {

    DcMotor frontleft;
    DcMotor frontright;
    DcMotor backleft;
    DcMotor backright;

    int direction;
    long time;
    double power = 0.7;

    //rotate the robot
    //direction = 1 for clockwise, direction = -1 for counterclockwise (intended, but doublecheck)
    public AutoRotate(int direction, long time){
        frontleft.setPower(1 * direction);
        frontright.setPower(-1 * direction);
        backleft.setPower(1 * direction);
        backright.setPower(-1 * direction);


        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);
    }
}
