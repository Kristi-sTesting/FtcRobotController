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
import org.firstinspires.ftc.teamcode.Movement.AutoRotate;
import org.firstinspires.ftc.teamcode.Movement.AutoShoot;

@Autonomous
public class bluebottom extends LinearOpMode {
    AutoMove autoMove = new AutoMove(0, 0, 0, 0);
    AutoRotate autoRotate = new AutoRotate(0,0);
    AutoShoot autoShoot = new AutoShoot();
    OldRobotHard oldRobotHard = new OldRobotHard();
    double flywheelVel = 0;
    double targetFlywheelVel = 1600;

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

        new AutoMove(0, 1, 900, .5);
        sleep(900);
        new AutoRotate(-1, 70);
        sleep(140);
        new AutoMove(0,1,550, 0.2);
        sleep(550);
        new AutoShoot();
        sleep(300);
        new AutoMove(0, -1, 150, 0.75);
        sleep(150);
        new AutoMove(-1, 0, 150, 0.75);
        sleep(150);
    }
}