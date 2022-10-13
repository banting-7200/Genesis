package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
 * This class is responsible for the vertical lift on the Genesis robot
 */
public class LiftSubSystem extends SubsystemBase  {

    private Joystick targetJoyStick;

    // Create new instance of subsystem
    public LiftSubSystem() {
        targetJoyStick = (Joystick)Constants.getValue("robot.joystick");
    }

    // Runs every scheduler run
    @Override
    public void periodic() {
        // Get the top little joystick vertical axis
        double liftDirection = targetJoyStick.getRawAxis(6);

        //TODO: Run lift motor in the direction determined by the liftDirection.
    }
}
