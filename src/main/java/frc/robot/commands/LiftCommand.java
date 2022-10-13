package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.JoyStickSubSystem;

/*
 * This class is responsible for moving the lift ontop of the robot.
 * This class depends on:
 *  - JoyStickSubSystem
 * 
 * Refer to https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html for button mappings.
 */
public class LiftCommand extends CommandBase{
    private final JoyStickSubSystem joyStickSubSystem;
    private boolean isRunning; //Stores whether or not the command is running

    public LiftCommand(JoyStickSubSystem joyStickSystem) {
        joyStickSubSystem = joyStickSystem;
        addRequirements(joyStickSubSystem);
    }

    @Override
    public void initialize() {
        isRunning = true;
    }

    @Override
    public void execute() {
        int liftUpButton = (int)Constants.getValue("robot.joystick.button_lift_up");
        int liftDownButton = (int)Constants.getValue("robot.joystick.button_lift_down");
        double liftAxis = joyStickSubSystem.getAxisButtons(liftUpButton, liftDownButton);
        System.out.println("liftAxis: " + liftAxis);

        //TODO: have liftAxis influence motors
        isRunning = false;
    }

    @Override
    public boolean isFinished() {
        return isRunning == false;
    }
}
