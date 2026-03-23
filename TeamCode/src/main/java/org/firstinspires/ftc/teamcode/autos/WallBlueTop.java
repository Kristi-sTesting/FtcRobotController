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

//Not Tested
@Autonomous
public class WallBlueTop extends LinearOpMode {
    OldRobotHard oldRobotHard = new OldRobotHard();
    AutoMove autoMove = new AutoMove(0,0,0,0);
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

        //new AutoMove(.5, .5, 200, .75);
        //sleep(200);
        //new AutoRotate(1, 50);
        //sleep(50);
        new AutoShoot();
        sleep(500);
        new AutoMove(0,-1,315, .75);
        sleep(315);

    }
}
