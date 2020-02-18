package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSubsystem extends Subsystem{

    public DigitalInput NEWINPUT;

    public LimitSubsystem(int port){
        this.NEWINPUT = new DigitalInput(port);
    }

    public void getlimit(){
        this.NEWINPUT.get();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
}