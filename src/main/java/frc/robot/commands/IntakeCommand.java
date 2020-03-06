package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.SparkSubsystem;

import frc.robot.utils.Config;

public class IntakeCommand extends Command {

    public IntakeCommand() {
    }

    @Override
    protected void execute() {
      Controller Pilot = Config.getController("pilot.controll");
      
        boolean in = Pilot.getButton(Config.getInt("controls.intake.in"));
        boolean out = Pilot.getButton(Config.getInt("controls.intake.out"));
        if (in) {
            Robot.m_ballSparkSubsystem.start(0.6);
          } else if (out) {
            Robot.m_ballSparkSubsystem.start(-0.6);
          } else {
            Robot.m_ballSparkSubsystem.stop();
          }
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}