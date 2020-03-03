package frc.robot.subsystems;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsSubsystem extends Subsystem {

    public Solenoid NEWSOLENOID;

    public PneumaticsSubsystem (){
    }
    
    public void pneumaticsSetup(int CANPCM, int PCMPORT){
        try {
            this.NEWSOLENOID = new Solenoid(CANPCM, PCMPORT);
            } catch (UncleanStatusException ex) {
                System.out.println("ERROR! ERROR! "+PCMPORT);
            }
    }

    public void ToggleSolenoid (Boolean state){
        this.NEWSOLENOID.set(state);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

    

}