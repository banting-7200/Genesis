// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commandgroups.DriveAndLiftGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.subsystems.JoyStickSubSystem;
import edu.wpi.first.wpilibj2.command.Command;

/*
 * This class contains instances of subsystems, and commands
 * Controls are declared in constants class using a HashMap
 */
public class RobotContainer {
  
  private static final JoyStickSubSystem m_joyStickSubSystem = new JoyStickSubSystem();

  // Declare the Commands that are responsible for driving and the lift
  private static final LiftCommand m_LiftCommand = new LiftCommand(m_joyStickSubSystem);
  private static final DriveCommand m_DriveCommand = new DriveCommand(m_joyStickSubSystem);
  public static final DriveAndLiftGroup m_DriveAndLiftGroup = new DriveAndLiftGroup(m_DriveCommand, m_LiftCommand);

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
