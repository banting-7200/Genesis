package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSubsystem extends Subsystem {

    public DigitalInput Switch;

    public LimitSubsystem(int port){
        Switch = new DigitalInput(port);
    }

    public boolean getLimit(){
        return Switch.get();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
} // hello kaden