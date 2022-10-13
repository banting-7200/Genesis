package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
 * This class is responsible for handling input on an attached joystick
 * refer to https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html for joystick button IDs
 */
public class JoyStickSubSystem extends SubsystemBase  {

    private Joystick targetJoyStick;

    public JoyStickSubSystem() {
        targetJoyStick = (Joystick)Constants.getValue("robot.joystick");
    }

    // Get specified axis on joystick
    public double getJoyStickAxis(int axis) {
        return targetJoyStick.getRawAxis(axis);
    }

    // Get specified button on joystick
    public boolean getButton(int button) {
        return targetJoyStick.getRawButton(button);
    }

    /*
     * This function takes in 2 buttons that represent the (+) and (-) sides of a 1D axis
     * and returns the 1D axis value based on the buttons passed through.
     */
    public double getAxisButtons(int buttonPositive, int buttonNegative) {

        //Get whether or not the given buttons are being pressed
        boolean buttonPositiveDown = getButton(buttonPositive);
        boolean buttonNegativeDown = getButton(buttonNegative);
        double value = 0;

        //Set value to (+) if positive button is pressed
        if(buttonPositiveDown) {
            value = 1;
        }

        //Set value to (-) if negative button is pressed
        if(buttonNegativeDown) {
            value = -1;
        }

        return value;
    }
}
