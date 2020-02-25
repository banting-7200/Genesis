package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;

import frc.robot.utils.Config;



public class LiftCommand extends Command {
    public LiftCommand() {
        requires(Robot.m_liftsubsystem);
    }

    

    @Override
    protected void execute() {
  
      Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);

      Controller controller = Config.getController("controls.main");

      boolean liftUp = controller.getButton(Config.getInt("controls.liftup"));
      boolean liftDown = controller.getButton(Config.getInt("controls.liftdown"));
      if (!Robot.limitSwitch.getLimit()) {
        if (liftUp){
            Robot.m_liftsubsystem.Lift.encoderup(4, 1);
            Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);//turns the lift lock off
          }else{
            Robot.m_liftsubsystem.Lift.stop();
            Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);//urns the lift lock on
          }
          if (liftDown){
            Robot.m_liftsubsystem.Lift.encoderdown(4);
            Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);
      
          }else{
            Robot.m_liftsubsystem.Lift.stop();
            Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
          }
      } else {
        Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
      }
    }

    @Override
    protected boolean isFinished() {
      Robot.m_liftsubsystem.Lift.stop();
      Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
      return false;
    }
}