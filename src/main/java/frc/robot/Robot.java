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
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.subsystems.LimitSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new SparkSubsystem(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  public static LiftSubsystem m_liftsubsystem = new LiftSubsystem();
  //public static LogitechJoystick m_joystick = Config.getController("controls.main");

  I2CCOM arduinoI2C;

  public PingController pingController;
  public CSMSubsystem Lift;//creats a vareable for a CSM (CSMSubsystem)
  public LimitSubsystem colorwheelspinner;
  public PneumaticsSubsystem colorwheelpiston;
  public PneumaticsSubsystem LiftlockPiston;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command m_autonomousCommand;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    this.Lift = new CSMSubsystem(7); //create a CSM using CSMSubsystem
    this.colorwheelspinner = new LimitSubsystem(1);//limit switch for the color spinner
    this.colorwheelpiston = new PneumaticsSubsystem(1, 1);//setting the can Adress of the PCM and the port on PCM
    this.LiftlockPiston = new PneumaticsSubsystem(1, 2);
    
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
    Scheduler.getInstance().run();
    driveCommand.start();
    this.LiftlockPiston.ToggleSolenoid(false);
    Controller controller = Config.getController("controls.main");
    boolean theLift = controller.getButton(5);
    boolean theLift1 = controller.getButton(3);
    boolean shootColorWheel = controller.getButton(6);
    boolean retractColorWheel = controller.getButton(7);
//****************lift CODE******************/
    if (theLift){
      this.Lift.encoderup(7,250);
      this.LiftlockPiston.ToggleSolenoid(true);//turns the lift lock off
    }else{
      this.Lift.stop();
      this.LiftlockPiston.ToggleSolenoid(false);//urns the lift lock on
    }
    if (theLift1){
      this.Lift.encoderdown(7);
      this.LiftlockPiston.ToggleSolenoid(true);

    }else{
      this.Lift.stop();
      this.LiftlockPiston.ToggleSolenoid(false);
    }
//**************lift CODE END****************/
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
    if (this.colorwheelspinner.getlimit()){
      driveCommand.cancel();
    }else{
      driveCommand.start();
    }
//************Limit Switch Code END***************/


    if (controller.getButton(1)) {
      arduinoI2C.sendData(1, 1);
    }
    if (controller.getButton(2)) {
      arduinoI2C.sendData(1, 0);

    }    
  }

  @Override
  public void testPeriodic() {
  }
}