package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;

public class SparkDriveTrain extends DriveTrainSubsystem {
    /* Motors */

    public SparkDriveTrain() {
        super(Config.getIntArray("left_motors"), Config.getIntArray("right_motors"));
    }

    @Override
    public SpeedController createSpeedController(int id) {
        return new Spark(id);
    }

    @Override
    public void drive(Controller joystick, double speed) {
        System.out.println(joystick.getY());
        drive_train.tankDrive(joystick.getY() * speed * joystick.speed, joystick.getX() * speed * joystick.speed);
    }

    @Override
    public void drive(double movementSpeed, double turnSpeed, double speed) {
        drive_train.arcadeDrive(movementSpeed * speed, turnSpeed * speed);
    }

}
