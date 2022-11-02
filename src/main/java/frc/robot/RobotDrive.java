package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/* About
 * This class uses the DifferentialDrive class to drive the robot.
 */
public class RobotDrive {
    private MotorControllerGroup leftGroup;
    private MotorControllerGroup rightGroup;
    private DifferentialDrive driveTrain;

    private Joystick inputDevice;
    private double motorSpeed;
    private boolean canDrive;

    public RobotDrive(int[] leftMotors, int[] rightMotors, Joystick inputDevice, double motorSpeed){
        this.inputDevice = inputDevice;
        this.motorSpeed = motorSpeed;
        canDrive = true;

        //Check if provided motor port number counts are valid (2-left, 2-right)
        if(leftMotors.length != 2 || rightMotors.length != 2) {
            System.out.println("RobotDrive: Couldn't initialise drive with invalid motor IDs!");
            canDrive = false;
            return;
        }

        CANSparkMax[] motorControllers = new CANSparkMax[] {
            new CANSparkMax(leftMotors[0], MotorType.kBrushless),
            new CANSparkMax(leftMotors[1], MotorType.kBrushless),

            new CANSparkMax(rightMotors[0], MotorType.kBrushless),
            new CANSparkMax(rightMotors[1], MotorType.kBrushless)
        };

        leftGroup = new MotorControllerGroup(motorControllers[0], motorControllers[1]);
        rightGroup = new MotorControllerGroup(motorControllers[2], motorControllers[3]);
        rightGroup.setInverted(true);

        driveTrain = new DifferentialDrive(leftGroup, rightGroup);
    }

    /*
     * Main RobotDrive loop. (Called from Robot.java TeleopPeriodic)
     */
    void pollDrive() {
        if(!canDrive) {
            return;
        }

        double forward = inputDevice.getY();
        double turn = inputDevice.getX();
        driveTrain.arcadeDrive(forward * motorSpeed, turn * motorSpeed);
    }
}