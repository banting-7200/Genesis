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
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new CSMDriveTrain(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  public static PneumaticsSubsystem m_colourWheelShoot = new PneumaticsSubsystem();
  public static PneumaticsSubsystem m_colourWheelsRetract = new PneumaticsSubsystem();
  public static PneumaticsSubsystem m_liftLock = new PneumaticsSubsystem();
  public static SparkSubsystem m_intakelift = new SparkSubsystem(2);
  public static SparkSubsystem m_ballSparkSubsystem = new SparkSubsystem(3);

  public static CSMSubsystem m_CsmSubsystem = new CSMSubsystem(7);
  public static DigitalInput m_liftLimit = new DigitalInput(8);
  public static SparkSubsystem shiftSpark = new SparkSubsystem(0);
  public static LimitSubsystem limitSwitch = new LimitSubsystem(1);
  public static Timer m_timer = new Timer();	
  public static Timer a_timer = new Timer();

  I2CCOM arduinoI2C;
  public Gyro gyro;		

  public PingController pingController;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command liftCommand = new LiftCommand();
  Command colorCommand = new ColorSensorCommand();
  Command intakeCommand = new IntakeCommand();
  Command intakeLiftCommand = new IntakeLiftCommand();
  Command colorWheelCommand = new ColorWheelCommand();
  //Command pixyCommand = new PixyCommand();
  Command autonomouousCommand = new AutonomousCommand();
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
    gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
    gyro.reset();	



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
    a_timer.start();
    a_timer.reset();


  }

  @Override
  public void autonomousPeriodic() {
    gyro.reset();	
    double adjust = 0.15;
    double angle = gyro.getAngle();
    System.out.println("gyro angle:  ");
    System.out.println(angle);
    if (a_timer.get()<0.5 ){
    Robot.m_intakelift.start(-0.5);
    Robot.m_drivetrainsubsystem.drive(-0.4, -angle*adjust);
    }else if(a_timer.get()<2 ){
      Robot.m_intakelift.start(0.5);
      Robot.m_drivetrainsubsystem.drive(-0.4, -angle*adjust);
    }else if (a_timer.get()<4.1){ //time driving fowards 
      Robot.m_intakelift.stop();
      Robot.m_drivetrainsubsystem.drive(-0.4, -angle*adjust);
    }else if (a_timer.get()>4.1 && a_timer.get()<4.5){
      Robot.m_drivetrainsubsystem.drive(0,0);
      Robot.m_ballSparkSubsystem.start(1); //stops and shoots ball 
    }else if (a_timer.get()>4.5 && a_timer.get()<6){
      Robot.m_ballSparkSubsystem.stop(); // back up a little
      Robot.m_drivetrainsubsystem.drive(0.4, angle); 
    }else if(a_timer.get()>6 && a_timer.get() <6.4){
      Robot.m_drivetrainsubsystem.drive(0.2,90);// first turn
    }else if(a_timer.get()>6.4 && a_timer.get()<9){
      Robot.m_drivetrainsubsystem.drive(-0.4, -angle*adjust); //drive
    }else if (a_timer.get()>9 && a_timer.get()<9.4){
      Robot.m_drivetrainsubsystem.drive(0.2,90); // second turn
    }else if (a_timer.get()>9.4 && a_timer.get()<12){
      Robot.m_drivetrainsubsystem.drive(-0.4, -angle*adjust); // drive
    }
    else{
      Robot.m_drivetrainsubsystem.drive(0,0);
      Robot.m_intakelift.stop();
    }
  }

  @Override
  public void teleopInit() { 
    m_timer.start();
    m_timer.reset();

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
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
    double angle = gyro.getAngle();
    System.out.println("gyro angle:  ");
    System.out.println(angle*100);
  }
}