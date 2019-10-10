package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamepad extends Controller {

    Joystick joystick = new Joystick(this.port);

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY();
    }

    public double getZ() {
        return joystick.getZ();
    }
}