package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;
import frc.robot.subsystems.base.DriveTrainSubsystem;

public class SparkSubsystem extends DriveTrainSubsystem {

    public SparkSubsystem() {
        super(Config.getIntArray("motors.left_motors"), Config.getIntArray("motors.right_motors"));
    }

    @Override
    public SpeedController createSpeedController(int id) {
        return new Spark(id);
    }

    @Override
    public void drive(Controller joystick) {
        drive_train.tankDrive(joystick.getY(), joystick.getY());

    }

    @Override
    public void drive(double movementSpeed, double turnSpeed, double speed) {
        drive_train.tankDrive(movementSpeed * speed, turnSpeed * speed);

    }
}

