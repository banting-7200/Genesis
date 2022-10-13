package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
    private CANSparkMax[] motors;
    private boolean isRunning;

    private double xInput;
    private double yInput;

    public DriveCommand(JoyStickSubSystem joyStickSubSystem) {
        // Get motor device ID's from Constants.java
        int[] leftMotors = (int[])Constants.getValue("robot.motors.left_motors");
        int[] rightMotors = (int[])Constants.getValue("robot.motors.right_motors");

        /*
         * Index 0 and 1 are the left motors
         * Index 2 and 3 are the right motors
        */
        motors = new CANSparkMax[] {
            new CANSparkMax(leftMotors[0], MotorType.kBrushless),
            new CANSparkMax(leftMotors[1], MotorType.kBrushless),
            new CANSparkMax(rightMotors[0], MotorType.kBrushless),
            new CANSparkMax(rightMotors[1], MotorType.kBrushless)
        };

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
        xInput = joyStickSubSystem.getJoyStickAxis(xAxis);
        yInput = joyStickSubSystem.getJoyStickAxis(yAxis);

        //Apply the invert settings from Constants.java (line 42-44)
        boolean invertX = (boolean)Constants.getValue("robot.joystick.invert_stick_x");
        boolean invertY = (boolean)Constants.getValue("robot.joystick.invert_stick_y");
        xInput *= invertX ? -1 : 1;
        yInput *= invertY ? -1 : 1;

        //Get the deadzone value for the xInput and the base speed of the robot.
        double xInputDeadZone = (double)Constants.getValue("robot.joystick.deadzone");
        double basePower = (double) Constants.getValue("robot.speed");

        //Calculate the overall length of the joystick vector to scale speed accordingly
        double inputMagnitude = Math.sqrt(xInput * xInput + yInput * yInput);

        /*
         * The xInput will represent the % of power that will be taken away from the motor corresponding to the side.
         * if xInput = -1 (full left), 100% of the power will be taken away on the left motors
         * if xInput = 1 (full right), 100% of the power will be taken away on the right motors.
        */
        double currentHorizontalPower = (basePower - Math.abs(basePower * xInput)) * inputMagnitude;
        double currentPower = basePower * inputMagnitude;
        
        if(xInput < -xInputDeadZone) { // Does the driver want to go left?
            //Set right motors to default joystick speed
            motors[2].set(currentPower);
            motors[3].set(currentPower);

            //Set left motors to inhibited speed
            motors[0].set(currentHorizontalPower);
            motors[1].set(currentHorizontalPower);
        } else if(xInput > xInputDeadZone) { // Does the driver want to go right?
            //Set left motors to default joystick speed
            motors[0].set(currentPower);
            motors[1].set(currentPower);

            //Set right motors to inhibited speed
            motors[2].set(currentHorizontalPower);
            motors[3].set(currentHorizontalPower);
        } else { // Does the driver want to go straight?
            //Set all motors to same power
            motors[0].set(currentPower);
            motors[1].set(currentPower);
            motors[2].set(currentPower);
            motors[3].set(currentPower);
        }

        isRunning = false;
    }

    @Override
    public boolean isFinished() {
        return isRunning == false;
    }
}
