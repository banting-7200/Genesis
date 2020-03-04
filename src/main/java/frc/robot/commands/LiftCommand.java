package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
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

      //Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);

      Controller Pilot = Config.getController("pilot.controll");
      Controller CoPilot = Config.getController("co.pilot.controll");

      boolean liftUp = CoPilot.getButton(Config.getInt("controls.liftup"));
      boolean liftDown = CoPilot.getButton(Config.getInt("controls.liftdown"));
      int liftShiftSparkL = CoPilot.getPOV(Config.getInt("controls.lift.shift.l"));
      int liftShiftSparkR = CoPilot.getPOV(Config.getInt("controls.lift.shift.r"));
      int CanID = (Config.getInt("lift.can.id"));
      int Rotation = (Config.getInt("number.of.rotations"));
      int FastSpeedUp = (Config.getInt("lift.up.fast.pos"));
      int SlowSpeedUp = (Config.getInt("lift.up.slow.pos"));
      int FastSpeedDown = (Config.getInt("lift.down.fast.pos"));
      int SlowSpeedDown = (Config.getInt("lift.down.slow.pos"));

       if (liftShiftSparkL == (Config.getInt("controls.lift.shift.l"))) {
          Robot.shiftSpark.start(1);
        } else if (liftShiftSparkR == (Config.getInt("controls.lift.shift.r"))) {
          Robot.shiftSpark.start(-1);
        } else {
          Robot.shiftSpark.stop();
        }   

        if (liftUp){
            Robot.m_liftsubsystem.Lift.encoderup(CanID, Rotation, SlowSpeedUp, FastSpeedUp);
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);//turns the lift lock off
          }else{
            Robot.m_liftsubsystem.Lift.stop();
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);//urns the lift lock on
          }
          if (liftDown){
            Robot.m_liftsubsystem.Lift.encoderdown(CanID, Rotation, SlowSpeedDown, FastSpeedDown);
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);
      
          }else{
            Robot.m_liftsubsystem.Lift.stop();
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
          }
      }
  


    @Override
    protected boolean isFinished() {
      Robot.m_liftsubsystem.Lift.stop();
      //Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
      return false;
    }
}