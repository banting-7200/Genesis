package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.base.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousCommand extends Command{

    public AutonomousCommand(){
      //  requires(Robot.DriveTrainSubsystem);
    }
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}