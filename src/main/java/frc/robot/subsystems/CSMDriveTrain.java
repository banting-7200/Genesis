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
        CANSparkMax motor = new CANSparkMax(id, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setSmartCurrentLimit(60);
        //motor.setInverted(true);
        //motor.setOpenLoopRampRate(0.3);
        return motor;

    }

    @Override
    public void drive(Controller joystick) {

        //System.out.println(joystick.getX()+", "+joystick.getY());
        int reverse = 1;
        if ((boolean) Config.get("drive.reverse")) {
            reverse = -1;
        } else {
            reverse = 1;
        }

        drive_train.arcadeDrive(reverse*(joystick.getY()*Config.getDouble("defaults.controller_base_speed"))*-1, reverse*(joystick.getX()*Config.getDouble("defaults.controller_base_speed")));        //drive_train.tankDrive(joystick.getY(), joystick.getY());

    }

    public void drive(double x, double y) {
        drive_train.arcadeDrive(x,y);
    }

}
