package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveCommand;
import frc.robot.controllers.Controller;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.utils.I2CCOM;
import frc.robot.utils.Config;
import edu.wpi.first.wpilibj.CameraServer;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new DriveTrainSubsystem();
  public static PneumaticsSubsystem m_pneumaticsubsystem = new PneumaticsSubsystem();
  public static OI m_oi;

  public PingController pingController;


  Command driveCommand = new DriveCommand();
  Command m_autonomousCommand;

  I2CCOM arduinoI2C;
  I2CCOM rPiI2C;
  I2CCOM ColorSensor;

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public static I2C.Port i2cPort = I2C.Port.kOnboard;
  public static ColorSensorV3 m_colorSensor = new ColorSensorV3 (i2cPort);
  
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    arduinoI2C = new I2CCOM(1);
    rPiI2C = new I2CCOM(2);

    
    
    
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
    CameraServer.getInstance().startAutomaticCapture();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    driveCommand.start();
    Controller controller = (Controller) Config.get("controls.main");
    if (controller.getButton(1)){
      arduinoI2C.sendData(1, 1);
    } if (controller.getButton(2)){
      arduinoI2C.sendData(1, 0);

    Color detectedColor = m_colorSensor.getColor();
    double IR = m_colorSensor.getIR();
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);

    int proximity = m_colorSensor.getProximity();

    SmartDashboard.putNumber("Proximity", proximity);
    }
    }

    
  

  @Override
  public void testPeriodic() {
  }
}
