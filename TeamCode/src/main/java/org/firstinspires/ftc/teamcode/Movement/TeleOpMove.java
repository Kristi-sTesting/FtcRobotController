package org.firstinspires.ftc.teamcode.Movement;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TeleOpMove {
    DcMotor frontleft;
    DcMotor frontright;
    DcMotor backleft;
    DcMotor backright;
    double moveSpeed = 1;
    IMU imu = hardwareMap.get(IMU.class, "imu");
    ImuOrientationOnRobot orientation = new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
            RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

    IMU.Parameters parameters = new IMU.Parameters(orientation);

    double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    double x = gamepad1.left_stick_x;
    double y = gamepad1.left_stick_y;
    double rot = gamepad1.right_stick_x;
    double moveAmnt = (Math.abs(x) + Math.abs(y)) * moveSpeed;
    double weight = 0;
    int xPolarity = 0;
    int yPolarityX = 0;
    int yPolarity = 0;
    int xPolarityY = 0;
    double rotOffset = 0;
    double curDirection = heading + rotOffset;

    public TeleOpMove() {

        telemetry.addData("Heading (Z)", heading);

        if (gamepad1.aWasPressed() || gamepad1.dpadUpWasPressed()) {
            imu.resetYaw();
            rotOffset = 0;
        }
        imu.initialize(parameters);
        if (curDirection > 180) {
            curDirection -= 360;
        } else if (curDirection < -180) {
            curDirection += 360;
        }

        //use direction to "rotate" movement inputs
        if (0 <= curDirection && curDirection <= 90) {
            xPolarity = 1;
            yPolarity = 1;
            xPolarityY = 1;
            yPolarityX = -1;

            weight = 1 - (curDirection / 90);
        } else if (90 <= curDirection && curDirection <= 180) {
            xPolarity = -1;
            yPolarity = -1;
            xPolarityY = 1;
            yPolarityX = -1;

            weight = ((curDirection - 90) / 90);
        } else if (-90 <= curDirection && curDirection <= 0) {
            xPolarity = 1;
            yPolarity = 1;
            xPolarityY = -1;
            yPolarityX = 1;

            weight = 1 - ((curDirection * -1) / 90);
        } else if (-180 <= curDirection && curDirection <= -90) {
            xPolarity = -1;
            yPolarity = -1;
            xPolarityY = -1;
            yPolarityX = 1;

            weight = (((curDirection * -1) - 90) / 90);
        }

        double oldX = x;

        x = (x * weight * xPolarity) + (y * (1 - weight) * yPolarityX);
        y = (y * weight * yPolarity) + (oldX * (1 - weight) * xPolarityY);

        double normalizeVector = 1 / (Math.abs(x) + Math.abs(y));

        x *= normalizeVector;
        y *= normalizeVector;


        if (Double.isNaN(x)) {
            x = 0;
        }

        if (Double.isNaN(y)) {
            y = 0;
        }

        x *= moveAmnt;
        y *= moveAmnt;
        rot *= moveSpeed;

        //assign power to motors

        double leftfrontPower = y - x - rot;
        double rightfrontPower = y + x + rot;
        double leftbackPower = y + x - rot;
        double rightbackPower = y - x + rot;

        frontleft.setPower(leftfrontPower);
        frontright.setPower(rightfrontPower);
        backleft.setPower(leftbackPower);
        backright.setPower(rightbackPower);
    }


}
