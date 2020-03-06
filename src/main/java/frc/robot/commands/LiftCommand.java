package frc.robot.commands;

import java.sql.Time;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;

import frc.robot.utils.Config;



public class LiftCommand extends Command {
    public LiftCommand() {
        requires(Robot.m_liftsubsystem);
        Robot.m_liftLock.pneumaticsSetup(1, 2);

    }

    @Override
    protected void execute() {

      //Robot.m_liftLock.ToggleSolenoid(false);

      Controller Pilot = Config.getController("pilot.controll");
      Controller CoPilot = Config.getController("co.pilot.controll");

      boolean liftUp = CoPilot.getButton(Config.getInt("controls.liftup"));
      boolean liftDown = CoPilot.getButton(Config.getInt("controls.liftdown"));
      boolean liftShiftSparkL = CoPilot.getButton(Config.getInt("controls.lift.shift.l"));
      boolean liftShiftSparkR = CoPilot.getButton(Config.getInt("controls.lift.shift.r"));
      int CanID = (Config.getInt("lift.can.id"));
      int Rotation = (Config.getInt("number.of.rotations"));
      int FastSpeedUp = (Config.getInt("lift.up.fast.pos"));
      int SlowSpeedUp = (Config.getInt("lift.up.slow.pos"));
      int FastSpeedDown = (Config.getInt("lift.down.fast.pos"));
      int SlowSpeedDown = (Config.getInt("lift.down.slow.pos"));

       if (liftShiftSparkL) {
          Robot.shiftSpark.start(1);
        } else if (liftShiftSparkR) {
          Robot.shiftSpark.start(-1);
        } else {
          Robot.shiftSpark.stop();
        }   
        //if (Robot.m_timer.get()>10){
        if (liftUp){
            //Robot.m_liftLock.ToggleSolenoid(true);
            //Timer.delay(0.5);
            Robot.m_liftsubsystem.Lift.encoderup(CanID, Rotation, SlowSpeedUp, FastSpeedUp);
          }else if (liftDown){
            Robot.m_liftsubsystem.Lift.encoderdown(CanID, Rotation, SlowSpeedDown, FastSpeedDown);
          }else{
            //Robot.m_liftLock.ToggleSolenoid(false);
            //Timer.delay(0.5);
            Robot.m_liftsubsystem.Lift.stop();
          }
        }
     // }
  


    @Override
    protected boolean isFinished() {
      //Robot.m_liftsubsystem.Lift.stop();
      //Robot.m_liftLock.ToggleSolenoid(false);
      return false;
    }
}