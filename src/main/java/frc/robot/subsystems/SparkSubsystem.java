package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SparkSubsystem extends Subsystem {

    public Spark spark;

    public SparkSubsystem(int pin) {
        spark = new Spark(pin);
        spark.set(0);
    }

    public void start(int speed) {
        spark.set(speed);
    }

    public void stop() {
        spark.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
