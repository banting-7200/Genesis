package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechJoystick extends Controller {

    Joystick joystick = new Joystick(this.port);

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY() * -1;
    }

    public double getZ() {
        return joystick.getZ();
    }

}