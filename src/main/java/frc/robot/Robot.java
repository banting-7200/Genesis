// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.Constants;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final Joystick joystickInstance = new Joystick(0);

  private final RobotDrive driveInstance = new RobotDrive(
    Constants.LEFT_MOTORS, 
    Constants.RIGHT_MOTORS, 
    joystickInstance, 
    3.0f
  );

  private final RobotLift liftInstance  = new RobotLift(
    joystickInstance, 
    Constants.LIFT_MOTOR, 
    0.3,
    Constants.LIFT_BUTTONUP, 
    Constants.LIFT_BUTTONDOWN
  );;

  //#region robot_functions
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}
  //#endregion

  //#region autonomous_functions
  @Override
  public void autonomousInit() {

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }
  //#endregion

  //#region teleop_functions
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    driveInstance.pollDrive();
    liftInstance.pollLift();
  }
  //#endregion
  
  //#region disabled_functions
  /* This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}
  //#endregion
  
  //#region test_functions
  /* This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
  //#endregion
}
