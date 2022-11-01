package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;

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

    public void pollLift() {
        
        // invert delta as up moves lift down, and down moves lift up
        double delta = -getLiftDirection(downButton, upButton) * liftSpeed;
        double currentMotorPosition = liftMotor.getEncoder().getPosition();

        // -215 is upper bound of lift, and 0 is lower bound of lift
        if(currentMotorPosition <= -215) {
            if(delta < 0) {
                delta = 0;
            }
        } else if(currentMotorPosition >= 0) {
            if(delta > 0) {
                delta = 0;
            }
        }

        liftMotor.set(delta);
    }

    private double getLiftDirection(int downButton, int upButton) {
        if(inputDevice.getRawButton(upButton)) {
            return 1;
        } else if(inputDevice.getRawButton(downButton)) {
            return -1;
        }

        return 0;
    }
}