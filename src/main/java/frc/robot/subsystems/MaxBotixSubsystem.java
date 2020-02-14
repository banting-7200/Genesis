package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.utils.Config;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MaxBotixSubsystem extends Subsystem{

    public AnalogInput Data;
    private static final double ValueToInches = 0.125;
    

    public MaxBotixSubsystem(int MaxPort){
        this.Data = new AnalogInput(MaxPort);
    }

    public void Ping(){
        this.Data.getValue();
        //System.out.println(currentDistance);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
}