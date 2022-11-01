package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class RobotDrive {
    private CANSparkMax[] motorControllers;

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

        /*
         * Store each given motor in a CANSparkMax array.
         * Assign the left and right motor controllers and create new DifferentialDrive instance.
         */
        motorControllers = new CANSparkMax[leftMotors.length + rightMotors.length];
        for(int i = 0; i < leftMotors.length; i++) {
            motorControllers[i] = new CANSparkMax(leftMotors[i], MotorType.kBrushless);
        }

        leftGroup = new MotorControllerGroup(motorControllers[0], motorControllers[1]);
        for(int i = 0; i < rightMotors.length; i++) {
            motorControllers[leftMotors.length + i] = new CANSparkMax(rightMotors[i], MotorType.kBrushless);
        }

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