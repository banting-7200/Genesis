package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;

public class DriveCommand extends Command {
  public DriveCommand() {
    requires(Robot.m_drivetrainsubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_drivetrainsubsystem.drive(Config.getController("controls.main"));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_drivetrainsubsystem.setSpeed(0);
    Robot.m_drivetrainsubsystem.drive(Config.getController("controls.main"));
  }

  @Override
  protected void interrupted() {
    end();
  }
}