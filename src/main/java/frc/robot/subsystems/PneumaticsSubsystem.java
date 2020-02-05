package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsSubsystem extends Subsystem {

    public HashMap<Integer, Solenoid> solenoids;

    public PneumaticsSubsystem() {
        solenoids = new HashMap<>();

    }

    public void createSolenoid(int id, int address) {
        solenoids.put(id, new Solenoid(id, address));
    }

    public Solenoid get(int id) {
        return solenoids.get(id);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Reset pneumatics position on robot startup

    }

}