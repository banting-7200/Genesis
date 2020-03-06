package frc.robot;

import edu.wpi.cscore.UsbCamera;
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
import frc.robot.commands.PixyCommand;
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
import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new CSMDriveTrain(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  public static PneumaticsSubsystem m_colourWheelShoot = new PneumaticsSubsystem();
  public static PneumaticsSubsystem m_colourWheelsRetract = new PneumaticsSubsystem();
  public static PneumaticsSubsystem m_liftLock = new PneumaticsSubsystem();

  public static CSMSubsystem m_CsmSubsystem = new CSMSubsystem(7);
  public static DigitalInput m_liftLimit = new DigitalInput(8);
  public static SparkSubsystem shiftSpark = new SparkSubsystem(0);
  public static LimitSubsystem limitSwitch = new LimitSubsystem(1);
  public static Timer m_timer = new Timer();	

  I2CCOM arduinoI2C;
 

  public PingController pingController;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command liftCommand = new LiftCommand();
  Command colorCommand = new ColorSensorCommand();
  Command intakeCommand = new IntakeCommand();
  Command intakeLiftCommand = new IntakeLiftCommand();
  Command colorWheelCommand = new ColorWheelCommand();
  //Command pixyCommand = new PixyCommand();
  //Command autonomouousCommand = new AutonomousCommand();
  Command m_autonomousCommand;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    CameraServer.getInstance().startAutomaticCapture();
    UsbCamera camera1;
    UsbCamera camera2;
    camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    camera2 = CameraServer.getInstance().startAutomaticCapture(1);



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
    
  }

  @Override
  public void autonomousPeriodic() {
    
    
  }

  @Override
  public void teleopInit() {    
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    m_timer.start();
    m_timer.reset();
    driveCommand.start();
    colorWheelCommand.start();
    liftCommand.start();
    colorCommand.start();
    intakeCommand.start();
    intakeLiftCommand.start();
  }

  @Override
  public void testPeriodic() {
    //pixyCommand.start();
  }
}