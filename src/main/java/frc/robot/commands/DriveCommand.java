package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
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
    double speed = (Config.getRawAxis(3) * Config.baseSpeed) + 1;
    Robot.m_drivetrainsubsystem.drive(Robot.m_oi.joystick_1, speed, true); // third argument = reverse (true = forward)
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_drivetrainsubsystem.drive(Robot.m_oi.joystick_1, 0);
  }

  @Override
  protected void interrupted() {
  }
}