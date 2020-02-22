package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSubsystem extends Subsystem {

    public DigitalInput lSwitch;

    public LimitSubsystem(int port){
        lSwitch = new DigitalInput(port);
    }

    public boolean getLimit(){
        return lSwitch.get();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
} // hello kaden