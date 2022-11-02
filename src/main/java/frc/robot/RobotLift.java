package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;

/* About
 * This class is responsible for moving the robot lift up/down.
 * In order for the limiters 
 */

/* Reference1
 * If motor is at lowermost bound (0),
 * check if the driver wants to move the lift down
 *  if the driver wants to move the lift down, set delta to 0, otherwise keep delta as is.
 * 
 * If motorposition is not at lowermost bound, keep delta as is
 */

/* Reference2
 * If motor is at uppermost bound (-215),
 * check if the driver wants to move the lift up
 *  if the driver wants to move the lift up, set delta to 0, otherwise keep delta as is.
 * 
 * If motorposition is not at uppermost bound, keep delta as is.
 */
public class RobotLift {
    private Joystick inputDevice;
    private CANSparkMax liftMotor;
    private double liftSpeed;

    private int downButton;
    private int upButton;

    public RobotLift(Joystick inputDevice, int liftMotorID, double liftSpeed, int upButton, int downButton) {
        liftMotor = new CANSparkMax(liftMotorID, MotorType.kBrushless);
        this.inputDevice = inputDevice;
        this.liftSpeed = liftSpeed;
        this.downButton = downButton;
        this.upButton = upButton;
    }

    /*
     * Main RobotDrive loop. (Called from Robot.java TeleopPeriodic)
     */
    public void pollLift() {
        double delta = -getLiftDirection(downButton, upButton) * liftSpeed;
        double currentMotorPosition = liftMotor.getEncoder().getPosition();

        delta = currentMotorPosition <= -215 ? (delta < 0 ? 0 : delta) : delta; //refer to Reference1
        delta = currentMotorPosition >= 0 ? (delta > 0 ? 0 : delta) : delta; //refer to Reference2
        liftMotor.set(delta);
    }

    private double getLiftDirection(int downButton, int upButton) {
        boolean upPressed = inputDevice.getRawButton(upButton);
        boolean downPressed = inputDevice.getRawButton(downButton);
        return upPressed ? 1 : downPressed ? -1 : 0;
    }
}