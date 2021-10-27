package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.SparkSubsystem;

import frc.robot.utils.Config;

public class IntakeLiftCommand extends Command{

    public SparkSubsystem liftSpark;

    public IntakeLiftCommand(){

    }

    @Override
    protected void execute() {
      Controller Pilot = Config.getController("pilot.controll");
      Controller CoPilot = Config.getController("co.pilot.controll");

        boolean in = CoPilot.getButton(Config.getInt("controls.intake.lift.in"));
        boolean out = CoPilot.getButton(Config.getInt("controls.intake.lift.out"));
        if (in) {
            Robot.m_intakelift.start(0.5);
          } else if (out) {
            Robot.m_intakelift.start(-0.5);
          } else {
            Robot.m_intakelift.stop();
          }

      
    }

    @Override
    protected boolean isFinished() {

      return false;
    }

}