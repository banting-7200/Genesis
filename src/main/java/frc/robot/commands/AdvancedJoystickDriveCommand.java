package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import org.banting7200.utils.Numbers;

public class AdvancedJoystickDriveCommand extends Command {
  public AdvancedJoystickDriveCommand() {
    requires(Robot.m_drivetrainsubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double speed = Robot.m_oi.joystick_1.getRawAxis(3);
    speed = Numbers.range(-1.0, 1.0, 1, 0, speed);
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