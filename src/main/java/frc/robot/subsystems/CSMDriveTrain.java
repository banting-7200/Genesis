package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.utils.Config;

import com.revrobotics.CANSparkMax;

public class NeoDriveTrainSubsystem extends DriveTrainSubsystem {
    /* Motors */

    public NeoDriveTrainSubsystem() {
        super(Config.getIntArray("left_motors"), Config.getIntArray("right_motors"));
    }

    @Override
    public SpeedController createSpeedController(int id) {
        return new CANSparkMax(id, MotorType.kBrushless);
    }

}
