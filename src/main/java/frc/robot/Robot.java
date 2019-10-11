package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveCommand;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new DriveTrainSubsystem();
  public static OI m_oi;

  public PingController pingController;

  Command driveCommand = new DriveCommand();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

    this.pingController = new PingController(6, 5); // implement logic in PingController getX(), getY(), getZ();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    
    Robot.m_drivetrainsubsystem.drive(this.pingController.getY(), this.pingController.getX(), 1);
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    driveCommand.start();
  }

  @Override
  public void testPeriodic() {
  }
}
