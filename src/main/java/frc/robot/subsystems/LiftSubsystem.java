package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.LiftCommand;
import frc.robot.subsystems.PneumaticsSubsystem;

public class LiftSubsystem extends Subsystem {

    public CSMSubsystem Lift;//creats a vareable for a CSM (CSMSubsystem)
    public PneumaticsSubsystem LiftlockPiston;
    public static int SRotation;
    

    public LiftSubsystem() {
        //this.Lift = new CSMSubsystem(7);
    }

    @Override
    protected void initDefaultCommand() {
      //setDefaultCommand(new LiftCommand());

    }
}