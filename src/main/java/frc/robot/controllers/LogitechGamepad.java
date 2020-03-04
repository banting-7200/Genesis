package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamepad extends Controller {

    Joystick joystick = new Joystick(this.port);
    
    public LogitechGamepad(int port) {
        this.port = port;
        this.speed = 0.75;
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
    public int getPOV(int angle) {
        return joystick.getPOV(angle);
    }
}