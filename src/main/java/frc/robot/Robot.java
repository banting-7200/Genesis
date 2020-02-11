package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.DriveCommand;
import frc.robot.controllers.Controller;
import frc.robot.controllers.LogitechJoystick;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.CSMDriveTrain;
import frc.robot.subsystems.CSMSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new CSMDriveTrain(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  //public static LogitechJoystick m_joystick = Config.getController("controls.main");

  I2CCOM arduinoI2C;

  public PingController pingController;
  public CSMSubsystem Lift;//creats a vareable for a CSM (CSMSubsystem)

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command m_autonomousCommand;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    this.Lift = new CSMSubsystem(7); //create a CSM using CSMSubsystem
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
    CameraServer.getInstance().startAutomaticCapture();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    System.out.println("INhael");
    Scheduler.getInstance().run();
    driveCommand.start();
    Controller controller = Config.getController("controls.main");
    boolean theLift = controller.getButton(6);

    if (theLift){
      

    }
    

    if (controller.getButton(1)) {
      arduinoI2C.sendData(1, 1);
    }
    if (controller.getButton(2)) {
      arduinoI2C.sendData(1, 0);

    }
    if (controller.getButton(5)){
      this.Lift.start(10);//Starts CSM (CSMSubsystem)
    }else{
      this.Lift.start(0);
    }

    
    
  }

  @Override
  public void testPeriodic() {
  }
}