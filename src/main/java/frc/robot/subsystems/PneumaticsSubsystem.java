package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsSubsystem extends Subsystem {

    public Solenoid NEWSOLENOID;

    public PneumaticsSubsystem (int CANPCM, int PCMPORT){
        this.NEWSOLENOID = new Solenoid(CANPCM, PCMPORT);
    }

    public void ToggleSolenoid (Boolean state){
        this.NEWSOLENOID.set(state);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

    

}