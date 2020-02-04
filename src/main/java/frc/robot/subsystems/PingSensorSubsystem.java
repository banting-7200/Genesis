package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PingSensorSubsystem extends Subsystem {

    public Ultrasonic pingSensor;

    public boolean printDistance;

    public PingSensorSubsystem (int trig, int echo) {
        this.pingSensor = new Ultrasonic(trig, echo);
        this.pingSensor.setAutomaticMode(true);
        this.printDistance = false;
    }

    public double getDistance() {
        double dist = this.pingSensor.getRangeMM();

        if (printDistance) {
            System.out.println(dist/100.0+" cm");
        }

        return dist;
    }

    @Override
    protected void initDefaultCommand() {

    }
    
}