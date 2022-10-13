package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LiftCommand;

/*
 * This class keeps the DriveCommand and LiftCommand in a sequential group.
 * Schedule this CommandGroup instead of individual driving commands.
 * This class depends on:
 *  - DriveCommand
 *  - LiftCommand
 */
public class DriveAndLiftGroup extends SequentialCommandGroup{
    public DriveAndLiftGroup(DriveCommand drive, LiftCommand lift) {
        addCommands(drive, lift);
    }
}
