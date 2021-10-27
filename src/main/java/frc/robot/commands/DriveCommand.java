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
    Robot.m_drivetrainsubsystem.setSpeed(Config.getDouble("controls.base_speed"));
    if (Config.getController("pilot.controll").getButton(8)) {
      Config.set("drive.reverse", true);
    } else if (Config.getController("pilot.controll").getButton(7)) {
      Config.set("drive.reverse", false);
    }
    if (!Config.getController("pilot.controll").getButton(11)) {
      Robot.m_drivetrainsubsystem.drive(Config.getController("pilot.controll"));      
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_drivetrainsubsystem.setSpeed(0);
    Robot.m_drivetrainsubsystem.drive(Config.getController("pilot.controll"));
  }

  @Override
  protected void interrupted() {
    end();
  }
}