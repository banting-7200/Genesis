package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamepad extends Controller {

    Joystick joystick;
    
    public LogitechGamepad(int port) {
        joystick = new Joystick(this.port);
        this.speed = 0.6;
    }

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY();
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