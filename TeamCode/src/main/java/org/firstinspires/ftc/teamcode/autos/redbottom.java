package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.teamcode.Hardware.OldRobotHard;
import org.firstinspires.ftc.teamcode.Movement.AutoMove;
import org.firstinspires.ftc.teamcode.Movement.AutoRotate;
import org.firstinspires.ftc.teamcode.Movement.AutoShoot;

@Autonomous
public class redbottom extends LinearOpMode {
    OldRobotHard oldRobotHard = new OldRobotHard();
    AutoMove autoMove = new AutoMove(0,0,0,0);
    AutoRotate autoRotate = new AutoRotate(0,0);
    AutoShoot autoShoot = new AutoShoot();

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


        new AutoMove(0,1,800, 0.5);
        sleep(800);
        new AutoRotate(1, 100);
        sleep(100);
        new AutoMove(0,1,500, 0.25);
        sleep(500);
        new AutoShoot();
        sleep(300);
        new AutoMove(0, -1, 175, 0.75);
        sleep(175);
        new AutoMove(-1, 0, 175, 0.75);
        sleep(175);
    }
}