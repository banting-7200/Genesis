package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.utils.Config;

import com.revrobotics.CANSparkMax;

public class CSMDriveTrain extends DriveTrainSubsystem {
    /* Motors */

    public CSMDriveTrain() {
        super(Config.getIntArray("motors.left_motors"), Config.getIntArray("motors.right_motors"));
    }

    @Override
    public SpeedController createSpeedController(int id) {
        return new CANSparkMax(id, MotorType.kBrushless);
    }

}
