package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechJoystick extends Controller {

    Joystick joystick = new Joystick(0);
    Joystick joystick = new Joystick(1);

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY() * -1;
    }

    public double getZ() {
        return joystick.getZ();
    }

    public boolean getButton(int button) {
        return joystick.getRawButton(button);
    }

    public Object getController() {
        return joystick;
    }

}