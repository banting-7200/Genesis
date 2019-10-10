package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.LogitechJoystick;
import frc.robot.utils.Config;
import frc.robot.controllers.Controller;

public class DriveCommand extends Command {
  public DriveCommand() {
    requires(Robot.m_drivetrainsubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_drivetrainsubsystem.drive((Controller) Config.get("controls.main"), (double) Config.get("controls.base_speed"));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_drivetrainsubsystem.drive((Controller) Config.get("controls.main"), 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}