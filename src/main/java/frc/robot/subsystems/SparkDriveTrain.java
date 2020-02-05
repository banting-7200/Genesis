package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
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

}
