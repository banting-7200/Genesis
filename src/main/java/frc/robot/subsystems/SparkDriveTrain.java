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
    public void drive(Controller joystick) {
        System.out.println(joystick.getY());
        drive_train.arcadeDrive(joystick.getY() * getSpeed() * joystick.speed, joystick.getX() * getSpeed() * joystick.speed);
    }

    @Override
    public void drive(double movementSpeed, double turnSpeed, double speed) {
        drive_train.arcadeDrive(movementSpeed * speed, turnSpeed * speed);
    }

}
