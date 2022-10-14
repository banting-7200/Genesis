package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
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

    private RelativeEncoder liftMotorEncoder;
    private Solenoid liftLockSolenoid;
    private CANSparkMax liftMotor;

    private double liftAxis;

    public LiftCommand(JoyStickSubSystem joyStickSystem) {

        //Initialise the liftMotor and get the motor encoder for later
        int liftMotorID = (int)Constants.getValue("robot.motors.lift_motor");
        liftMotor = new CANSparkMax(liftMotorID, MotorType.kBrushless);
        liftMotorEncoder = liftMotor.getEncoder();

        //Initialise the solenoid that will be used for later
        int liftLockSolenoidChannel = (int)Constants.getValue("robot.solenoids.lift_lock_channel");
        liftLockSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, liftLockSolenoidChannel);

        joyStickSubSystem = joyStickSystem;
        addRequirements(joyStickSubSystem);
    }

    @Override
    public void initialize() {
        isRunning = true;
    }

    @Override
    public void execute() {

        //Get buttons associated with lift and have them represent sides of a 1D axis (-) to (+)
        int liftUpButton = (int)Constants.getValue("robot.joystick.button_lift_up");
        int liftDownButton = (int)Constants.getValue("robot.joystick.button_lift_down");

        //Get the lift position limits from config.java
        double lowerLiftLimit = (double)Constants.getValue("robot.lift.lower_position_limit");
        double upperLiftLimit = (double)Constants.getValue("robot.lift.upper_position_limit");

        // Get set speed of lift motor from Constants.java
        double liftSpeed = (double)Constants.getValue("robot.liftspeed");

        // Check if the driver is pressing the lift buttons and set the solenoid activity state accordingly.
        liftAxis = joyStickSubSystem.getAxisButtons(liftUpButton, liftDownButton);
        if(liftAxis != 0) {
            liftLockSolenoid.set(true);
        } else {
            liftMotor.set(0);
            liftLockSolenoid.set(false);
        }

        /*
         * For either direction the driver wants to move the lift, check if the motor delta
         * will be within the position limit within Constants.java
         * 
         * If the position is within the limit set the motor to be the 
         */
        double liftPosition = liftMotorEncoder.getPosition();
        double liftMotorSpeed = liftAxis * liftSpeed;
        if(liftAxis > 0 && liftPosition < upperLiftLimit) {
            liftMotor.set(liftMotorSpeed);
        }

        if(liftAxis < 0 && liftPosition > lowerLiftLimit) {
            liftMotor.set(liftMotorSpeed);
        }

        isRunning = false;
    }

    @Override
    public boolean isFinished() {
        return isRunning == false;
    }
}
