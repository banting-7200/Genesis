package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SparkSubsystem extends Subsystem {

    public Spark NEWSPARK;

    public SparkSubsystem(int PWMPin) {
        this.NEWSPARK = new Spark(PWMPin);
        this.NEWSPARK.set(0);
    }

    public void start(int speed) {
        this.NEWSPARK.set(speed);
    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}

