package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class JoystickDriveCommand extends Command {
  public JoystickDriveCommand() {
    requires(Robot.m_drivetrainsubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double speed = Robot.m_oi.joystick_1.getRawAxis(3);
    speed = range(-1.0, 1.0, 1, 0, speed);
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

  double range(double min, double max, double newmin, double newmax, double number) {
    /* This function just maps one integer to another */
    return (number-min)/(max-min) * (newmax-newmin) + newmin;
  }
}