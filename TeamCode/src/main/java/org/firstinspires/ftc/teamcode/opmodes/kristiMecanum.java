package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Hardware.OldRobotHard;
import org.firstinspires.ftc.teamcode.Movement.FlyWheelTeleOp;
import org.firstinspires.ftc.teamcode.Movement.TeleOpMove;

@TeleOp(name = "kristiMecanum")
public class kristiMecanum extends LinearOpMode {
    OldRobotHard oldRobotHard = new OldRobotHard();
    TeleOpMove teleOpMove = new TeleOpMove();
    FlyWheelTeleOp flyWheelTeleOp = new FlyWheelTeleOp();

    @Override
    public void runOpMode() throws InterruptedException {
        new OldRobotHard();
        IMU imu = hardwareMap.get(IMU.class, "imu");

        telemetry.addData("Status", "IMU Calibrating...");
        telemetry.update();

        imu.resetYaw();
        waitForStart();


        while (opModeIsActive()) {

            new TeleOpMove();

            new FlyWheelTeleOp();

            telemetry.update();
        }
    }
}