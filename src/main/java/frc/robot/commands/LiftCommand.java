package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.LimitSubsystem;
import frc.robot.utils.Config;



public class LiftCommand extends Command {

 private LimitSubsystem limitSwitch;

    public LiftCommand() {
        requires(Robot.m_liftsubsystem);
        limitSwitch = new LimitSubsystem(11);
    }

    

    @Override
    protected void execute() {
  
      Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);

      Controller controller = Config.getController("controls.main");

      boolean liftUp = controller.getButton(Config.getInt("controls.liftup"));
      boolean liftDown = controller.getButton(Config.getInt("controls.liftdown"));
      int CanID = (Config.getInt("lift.id"));
      int Rotation = (Config.getInt("number.of.rotations"));

      //if (limitSwitch.getLimit()){
        //Robot.m_liftsubsystem.Lift.getEncoder().setPosition(0.0);
        //System.out.println(Robot.m_liftsubsystem.Lift.getEncoder().setPosition(0.0));
        //System.out.println(Robot.m_liftsubsystem.Lift.getEncoder());
      //}

      if (true) {
        if (liftUp){
            Robot.m_liftsubsystem.Lift.encoderup(CanID, Rotation);
            Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);//turns the lift lock off
          }else{
            Robot.m_liftsubsystem.Lift.stop();
            Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);//urns the lift lock on
          }
          if (liftDown){
            Robot.m_liftsubsystem.Lift.encoderdown(CanID);
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