package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.teamcode.Hardware.OldRobotHard;
import org.firstinspires.ftc.teamcode.Movement.AutoMove;
import org.firstinspires.ftc.teamcode.Movement.AutoShoot;

@Autonomous
public class redtop extends LinearOpMode {
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
        new AutoMove(1, 0, 150, .75);
        sleep(500);
        new AutoMove(0.5, -0.5, 600, .75);
        sleep(500);
    }
}