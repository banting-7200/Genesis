package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorSensorCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.controllers.Controller;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.LimitSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.CSMDriveTrain;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.base.DriveTrainSubsystem;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new CSMDriveTrain(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  public static LiftSubsystem m_liftsubsystem = new LiftSubsystem();
  public static LimitSubsystem limitSwitch =  new LimitSubsystem(1);

  I2CCOM arduinoI2C;

  public PingController pingController;
  public PneumaticsSubsystem colorwheelpiston;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command liftCommand = new LiftCommand();
  Command colorCommand = new ColorSensorCommand();
  Command m_autonomousCommand;
   

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    this.colorwheelpiston = new PneumaticsSubsystem(1, 1);//setting the can Adress of the PCM and the port on PCM
    
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
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    //CameraServer.getInstance().startAutomaticCapture();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  
    Scheduler.getInstance().run();
    //driveCommand.start();
    liftCommand.start();
    Controller controller = Config.getController("controls.main");
    //CSMSubsystem motor1 = new CSMSubsystem(7);
    //CSMSubsystem motor2 = new CSMSubsystem(4);
    //CSMSubsystem motor3 = new CSMSubsystem(5);
    //CSMSubsystem motor4 = new CSMSubsystem(6);
    //if (controller.getButton(9)) {
    //  motor1.start(100);
    //} 
    //if (controller.getButton(10)) {
    //  motor2.start(100);
    //} 
    //if (controller.getButton(11)) {
    //  motor3.start(100);
    //} 
    //if (controller.getButton(12)) {
    //  motor4.start(100);
    //}
  
    boolean shootColorWheel = controller.getButton(6);
    boolean retractColorWheel = controller.getButton(7);
//*****************Pneumatics*******************/
    if(shootColorWheel){
      this.colorwheelpiston.ToggleSolenoid(true);
      m_drivetrainsubsystem.setSpeed(0.5);
    } 
    if (retractColorWheel){
      this.colorwheelpiston.ToggleSolenoid(false);
      m_drivetrainsubsystem.setSpeed(1);
    }
//***************Pneumatics end*****************/
//**************Limit Switch Code****************/
    //if (!limitSwitch.getLimit()){
    //  System.out.println("Bruh switch bruh switch");
    //  driveCommand.cancel();
    //}else{
      driveCommand.start();
    //}
//************Limit Switch Code END***************/


    /*if (controller.getButton(1)) {
      arduinoI2C.sendData(1, 1);
    }
    if (controller.getButton(2)) {
      arduinoI2C.sendData(1, 0);

    }*/
    colorCommand.start();
  }

  @Override
  public void testPeriodic() {
  }
}