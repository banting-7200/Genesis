package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.JoyStickSubSystem;

/*
 * This class is responsible for using joystick input to activate/deactivate motors
 * This class depends on:
 *  - JoyStickSubSystem
 */
public class DriveCommand extends CommandBase{

    private JoyStickSubSystem joyStickSubSystem;
    private boolean isRunning;

    public DriveCommand(JoyStickSubSystem joyStickSubSystem) {
        this.joyStickSubSystem = joyStickSubSystem;
        addRequirements(joyStickSubSystem);
    }
    
    @Override
    public void initialize() {
        isRunning = true;
    }

    @Override
    public void execute() {

        //Get the axis IDs for the joystick from Constants.java
        int xAxis = (int)Constants.getValue("robot.joystick.x");
        int yAxis = (int)Constants.getValue("robot.joystick.y");

        //Get the default x and y axis from the joystick
        double xInput = joyStickSubSystem.getJoyStickAxis(xAxis);
        double yInput = joyStickSubSystem.getJoyStickAxis(yAxis);

        //Apply the invert settings from Constants.java (line 42-44)
        boolean invertX = (boolean)Constants.getValue("robot.joystick.invert_stick_x");
        boolean invertY = (boolean)Constants.getValue("robot.joystick.invert_stick_y");
        xInput *= invertX ? -1 : 1;
        yInput *= invertY ? -1 : 1;

        System.out.println("Drive Axis: (" + xInput + ", " + yInput + ")");

        //TODO: have xInput and yInput drive robot. (y forward/back) (x left/right rotate)
        isRunning = false;
    }

    @Override
    public boolean isFinished() {
        return isRunning == false;
    }
}
