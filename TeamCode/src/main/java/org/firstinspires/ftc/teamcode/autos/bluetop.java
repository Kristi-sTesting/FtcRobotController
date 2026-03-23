package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.OldRobotHard;
import org.firstinspires.ftc.teamcode.Movement.AutoMove;
import org.firstinspires.ftc.teamcode.Movement.AutoShoot;

@Autonomous
public class bluetop extends LinearOpMode {
    OldRobotHard oldRobotHard = new OldRobotHard();
    AutoShoot autoShoot = new AutoShoot();
    AutoMove autoMove = new AutoMove(0,0,0,0);

    @Override
    public void runOpMode() throws InterruptedException {
        new OldRobotHard();
        IMU imu = hardwareMap.get(IMU.class, "imu");

        ImuOrientationOnRobot orientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

        IMU.Parameters parameters = new IMU.Parameters(orientation);
        imu.initialize(parameters);

        telemetry.addData("Status", "IMU Calibrating...");
        telemetry.update();

        imu.resetYaw();
        waitForStart();

        new AutoShoot();
        sleep(300);
        new AutoMove(1, 0, 100, .75);
        sleep(100);
        new AutoMove(0.5, -0.5, 150, .75);
        sleep(150);


    }
}







