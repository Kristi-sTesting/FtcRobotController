package org.firstinspires.ftc.teamcode.Movement;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;


public class FlyWheelTeleOp {
    CRServo right_launch_servo;
    CRServo left_launch_servo;
    DcMotorEx flywheel;
    int counter = 0;
    double flywheelVel = 0;
    double targetFlywheelVel = 1700;
    ElapsedTime shootTimer = new ElapsedTime();
    boolean shooting = false;
    int sortAmt = 0;
    int sortCounter = 0;
    double defaultFlywheelVel = 0;


    public FlyWheelTeleOp() {
        telemetry.addData("Left Servo", left_launch_servo.getPower());
        telemetry.addData("Right Servo", right_launch_servo.getPower());
        telemetry.addData("Sort Que:", sortAmt);
        telemetry.addData("Flywheel Velocity", flywheelVel);
        telemetry.addData("Target Flywheel Velocity", targetFlywheelVel);

        if (!shooting) {
            shootTimer.reset();
            shooting = true;

        }

        //Press after loading
        if (gamepad1.dpad_right || gamepad1.left_bumper || gamepad2.a) {
            //stopped waiting, too much work :P
            defaultFlywheelVel = 500;
        }

        if (gamepad1.xWasPressed()) {
            sortAmt += 1;
        }

        if (gamepad1.b) {
            flywheel.setPower(-0.4);
            right_launch_servo.setPower(1);
            left_launch_servo.setPower(-1);
            sortAmt = 0;
        } else if (gamepad1.right_bumper) {
            double oldFlywheelVel = defaultFlywheelVel;
            defaultFlywheelVel = 0;
            sortAmt = 0;
            //regular shot speed
            if (targetFlywheelVel < 1000)
                targetFlywheelVel = 1490;


            counter += 1;

            if(oldFlywheelVel > 0){
                if (counter == 10) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                }
                if (counter == 20) {
                    right_launch_servo.setPower(0);
                    left_launch_servo.setPower(0);
                    if (targetFlywheelVel > 1470) {
                        targetFlywheelVel -= 40;
                    } else{
                        targetFlywheelVel += 40;
                    }
                }
                if (counter == 24) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                    counter = 11;
                }
            } else {
                if (counter == 75) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                }

                if (counter == 85) {

                    right_launch_servo.setPower(0);
                    left_launch_servo.setPower(0);
                    if (targetFlywheelVel > 1470) {
                        targetFlywheelVel -= 40;

                    }else{
                        targetFlywheelVel += 40;
                    }
                }
                if (counter == 95) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                    counter = 76;
                }
            }



        } else if(gamepad1.right_trigger > 0.5f){
            double oldFlywheelVel = defaultFlywheelVel;
            sortAmt = 0;
            defaultFlywheelVel = 0;
            //regular shot speed
            if (targetFlywheelVel < 1000)
                targetFlywheelVel = 2350;


            counter += 1;

            if(oldFlywheelVel > 0) {
                if (counter == 50) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                }
                if (counter == 65) {
                    right_launch_servo.setPower(0);
                    left_launch_servo.setPower(0);
                    if (targetFlywheelVel > 1470)
                        targetFlywheelVel -= 40;
                }
                if (counter == 77) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                    counter = 51;
                }
            } else {
                if (counter == 100) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                }
                if (counter == 115) {
                    right_launch_servo.setPower(0);
                    left_launch_servo.setPower(0);
                    if (targetFlywheelVel > 1470)
                        targetFlywheelVel -= 40;
                }
                if (counter == 127) {
                    right_launch_servo.setPower(-1);
                    left_launch_servo.setPower(1);
                    counter = 101;
                }
            }
        }else if (sortAmt > 0) { //sorting system
            defaultFlywheelVel = 0;
            targetFlywheelVel = 760;

            sortCounter += 1;

            if (sortCounter == 100 && sortAmt < 50) {
                right_launch_servo.setPower(-1);
                left_launch_servo.setPower(1);
            }
            if (sortCounter == 130 && sortAmt < 50) {
                right_launch_servo.setPower(1);
                left_launch_servo.setPower(-1);
                if(targetFlywheelVel > 700)
                    targetFlywheelVel -=65;
            }
            if (sortCounter == 150) {
                right_launch_servo.setPower(-1);
                left_launch_servo.setPower(1);
                sortCounter = 101;
                sortAmt -= 1;
                if(sortAmt == 0){
                    sortAmt = 1000;
                    sortCounter = 0;
                    flywheel.setPower(-0.4);

                    right_launch_servo.setPower(1);
                    left_launch_servo.setPower(-1);
                }
            }

            if(sortAmt > 50 && sortCounter == 130){
                sortAmt = 0;
            }

        } else {
            counter = 0;
            sortCounter = 0;
            targetFlywheelVel = defaultFlywheelVel;
            right_launch_servo.setPower(0);
            left_launch_servo.setPower(0);
        }


        if (!gamepad1.b && sortAmt < 50) {
            double slowThreshold = 2;
            double motorSpeedTowardsTarget;
            if (flywheelVel < targetFlywheelVel) {
                if (flywheelVel > (targetFlywheelVel / slowThreshold) * (slowThreshold - 1)) {
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