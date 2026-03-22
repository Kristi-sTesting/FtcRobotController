package org.firstinspires.ftc.teamcode.Movement;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutoShoot {
    double flywheelVel = 0;
    double targetFlywheelVel = 1600;
    DcMotorEx flywheel;
    CRServo right_launch_servo;
    CRServo left_launch_servo;
    ElapsedTime shootTimer = new ElapsedTime();
    boolean shooting = false;


    //wait 13 seconds after calling the function
    public AutoShoot() {

        shooting = true;
        shootTimer.reset();

        while (shooting == true) {
            flywheelVel = flywheel.getVelocity();
            double motorSpeedTowardsTarget = 0;


            if (flywheelVel < targetFlywheelVel) {
                if (flywheelVel > (targetFlywheelVel / 2)) {
                    double normalVel = flywheelVel / targetFlywheelVel;
                    motorSpeedTowardsTarget = 1 - (2 * (normalVel - 0.5));
                    if (motorSpeedTowardsTarget < 0)
                        motorSpeedTowardsTarget = 0;
                } else {
                    motorSpeedTowardsTarget = 1;
                }
                flywheel.setPower(motorSpeedTowardsTarget);
            } else {
                flywheel.setPower(0);

            }

        }

    }
}

