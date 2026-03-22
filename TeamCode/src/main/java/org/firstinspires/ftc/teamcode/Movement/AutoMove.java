package org.firstinspires.ftc.teamcode.Movement;

import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoMove {
    DcMotor frontleft;
    DcMotor frontright;
    DcMotor backleft;
    DcMotor backright;
    double x;
    double y;
    long time;
    double power = 0.7;

    //Move in a certain direction for a certain amount of time
    //Make sure x + y = 1
    // constructure method
    public AutoMove(double x, double y, long time, double power) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.power = power;

        time *= (1/power);

        double leftfrontPower = (y-x) * power;
        double rightfrontPower = (y+x) * power;
        double leftbackPower = (y+x) * power;
        double rightbackPower = (y-x) * power;
        frontleft.setPower(leftfrontPower);
        frontright.setPower(rightfrontPower);
        backleft.setPower(leftbackPower);
        backright.setPower(rightbackPower);
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

    }

}
