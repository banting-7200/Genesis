package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
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
    private DifferentialDrive robotDrive;
    private boolean isRunning;
    
    private double zAngle;
    private double xInput;
    private double yInput;

    public DriveCommand(JoyStickSubSystem joyStickSubSystem) {
        // Get motor device ID's from Constants.java
        int[] leftMotors = (int[])Constants.getValue("robot.motors.left_motors");
        int[] rightMotors = (int[])Constants.getValue("robot.motors.right_motors");

        MotorControllerGroup leftGroup = new MotorControllerGroup(
            new CANSparkMax(leftMotors[0], MotorType.kBrushless), 
            new CANSparkMax(leftMotors[1], MotorType.kBrushless)
        );

        MotorControllerGroup rightGroup = new MotorControllerGroup(
            new CANSparkMax(rightMotors[0], MotorType.kBrushless), 
            new CANSparkMax(rightMotors[1], MotorType.kBrushless)
        );

        robotDrive = new DifferentialDrive(leftGroup, rightGroup);

        this.joyStickSubSystem = joyStickSubSystem;
        addRequirements(joyStickSubSystem);
    }
    
    @Override
    public void initialize() {
        isRunning = true;
    }

    // The motor code below for Genesis is not guaranteed to work
    @Override
    public void execute() {
        //Get the axis IDs for the joystick from Constants.java
        int xAxis = (int)Constants.getValue("robot.joystick.x");
        int yAxis = (int)Constants.getValue("robot.joystick.y");

        //Get the power for the motors from Constants (line 68)
        double basePower = (double) Constants.getValue("robot.speed");

        //Get the invert settings from Constants.java (line 42-44)
        boolean invertX = (boolean)Constants.getValue("robot.joystick.invert_stick_x");
        boolean invertY = (boolean)Constants.getValue("robot.joystick.invert_stick_y");

        //Get the default x and y axis from the joystick
        xInput = joyStickSubSystem.getJoyStickAxis(xAxis);
        yInput = joyStickSubSystem.getJoyStickAxis(yAxis);

        //Apply the invert settings
        xInput *= invertX ? -1 : 1;
        yInput *= invertY ? -1 : 1;

        double currentPower = yInput * basePower;
        robotDrive.arcadeDrive(currentPower, xInput);
        isRunning = false;
    }

    @Override
    public boolean isFinished() {
        return isRunning == false;
    }
}