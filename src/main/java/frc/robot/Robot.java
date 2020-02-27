package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorSensorCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeLiftCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.controllers.Controller;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.SparkSubsystem;
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
  public static SparkSubsystem shiftSpark = new SparkSubsystem(7);
  public static LimitSubsystem limitSwitch = new LimitSubsystem(1);

  I2CCOM arduinoI2C;

  public PingController pingController;
  public PneumaticsSubsystem colorwheelpiston;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command liftCommand = new LiftCommand();
  Command colorCommand = new ColorSensorCommand();
  Command intakeCommand = new IntakeCommand();
  Command intakeLiftCommand = new IntakeLiftCommand();
  Command m_autonomousCommand;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    this.colorwheelpiston = new PneumaticsSubsystem(1, 1);// setting the can Adress of the PCM and the port on PCM

  }

  @Override
  public void robotPeriodic() {
    // this.findColor.ColorSencorSubsystem();
    // System.out.println(findColor);
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
    // CameraServer.getInstance().startAutomaticCapture();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Controller controller = Config.getController("controls.main");
    Scheduler.getInstance().run();
    driveCommand.start();
    // liftCommand.start();
    colorCommand.start();
    intakeCommand.start();
    intakeLiftCommand.start();

    boolean shootColorWheel = controller.getButton(6);
    boolean retractColorWheel = controller.getButton(7);
    // *****************Pneumatics*******************/
    if (shootColorWheel) {
      this.colorwheelpiston.ToggleSolenoid(true);
      m_drivetrainsubsystem.setSpeed(0.5);
    }
    if (retractColorWheel) {
      this.colorwheelpiston.ToggleSolenoid(false);
      m_drivetrainsubsystem.setSpeed(1);
    }
    // ***************Pneumatics end*****************/
    // **************Limit Switch Code****************/
    // if (!limitSwitch.getLimit()){
    // System.out.println("Bruh switch bruh switch");
    // driveCommand.cancel();
    // }else{
    // }
    // ************Limit Switch Code END***************/

    /*
     * if (controller.getButton(1)) { arduinoI2C.sendData(1, 1); } if
     * (controller.getButton(2)) { arduinoI2C.sendData(1, 0);
     * 
     * }
     */

    if (controller.getButton(10)) {
      System.out.println("Button 10 be like");
      this.shiftSpark.start(1);
    } else if (controller.getButton(9)) {

      System.out.println("Button 9 be like");
      this.shiftSpark.start(-1);
    } else {
      this.shiftSpark.stop();
    }
  }

  @Override
  public void testPeriodic() {
  }
}