package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorSensorCommand;
import frc.robot.commands.ColorWheelCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeLiftCommand;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.controllers.Controller;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.subsystems.LimitSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.CSMDriveTrain;
import frc.robot.subsystems.CSMSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.base.DriveTrainSubsystem;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new CSMDriveTrain(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  public static CSMDriveTrain m_CsmDriveTrain = new CSMDriveTrain();
  //public static PneumaticsSubsystem m_colourWheelShoot = new PneumaticsSubsystem();
  //public static PneumaticsSubsystem m_colourWheelsRetract = new PneumaticsSubsystem();

  public static LiftSubsystem m_liftsubsystem = new LiftSubsystem();
  public static DigitalInput m_liftLimit = new DigitalInput(8);
  public static SparkSubsystem shiftSpark = new SparkSubsystem(7);
  public static LimitSubsystem limitSwitch = new LimitSubsystem(1);

  I2CCOM arduinoI2C;

  public PingController pingController;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command liftCommand = new LiftCommand();
  Command colorCommand = new ColorSensorCommand();
  Command intakeCommand = new IntakeCommand();
  Command intakeLiftCommand = new IntakeLiftCommand();
  Command colorWheelCommand = new ColorWheelCommand();
  Command autonomouousCommand = new AutonomousCommand();
  Command m_autonomousCommand;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    CameraServer.getInstance().startAutomaticCapture();


  }

  @Override
  public void robotPeriodic() {
    //this.findColor.ColorSencorSubsystem();
    //System.out.println(findColor);

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
    
  }

  @Override
  public void autonomousPeriodic() {
    
    
  }

  @Override
  public void teleopInit() {
    // CameraServer.getInstance().startAutomaticCapture();
    
  }

  @Override
  public void teleopPeriodic() {
    Controller controller = Config.getController("controls.main");
    Scheduler.getInstance().run();
    driveCommand.start();
    colorWheelCommand.start();
    // liftCommand.start();
    colorCommand.start();
    intakeCommand.start();
    intakeLiftCommand.start();
  }

  @Override
  public void testPeriodic() {

  }
}