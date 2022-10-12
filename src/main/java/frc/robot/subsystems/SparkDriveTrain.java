package frc.robot.subsystems;

import frc.robot.subsystems.base.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;

public class SparkDriveTrain extends DriveTrainSubsystem {
    /* Motors */

    public SparkDriveTrain() {
        super(Config.getIntArray("motors.left_motors"), Config.getIntArray("motors.right_motors"));
    }

    @Override
    public SpeedController createSpeedController(int id) {
        return new Spark(id);
    }

    @Override
    public void drive(Controller joystick) {
        //System.out.println(joystick.getY()+", "+joystick.getX());
        drive_train.arcadeDrive(joystick.getY() * 1, joystick.getX() * 1);
    }

    @Override
    public void drive(double x, double y) {
        drive_train.arcadeDrive(x,y);
    }

}
