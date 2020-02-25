package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;
import frc.robot.subsystems.base.DriveTrainSubsystem;

import com.revrobotics.CANSparkMax;

public class CSMDriveTrain extends DriveTrainSubsystem {

    public CSMDriveTrain() {
        super(Config.getIntArray("motors.left_motors"), Config.getIntArray("motors.right_motors"));
    }

    @Override
    public SpeedController createSpeedController(int id) {
        return new CANSparkMax(id, MotorType.kBrushless);
    }

    @Override
    public void drive(Controller joystick) {

        System.out.println(joystick.getX()+", "+joystick.getY());

        drive_train.arcadeDrive(joystick.getX(), joystick.getY());        //drive_train.tankDrive(joystick.getY(), joystick.getY());

    }

    @Override
    public void drive(double movementSpeed, double turnSpeed, double speed) {
        drive_train.tankDrive(movementSpeed * speed, turnSpeed * speed);
    }

}
