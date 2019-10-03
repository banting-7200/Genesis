package frc.robot.controllers;

public abstract class Controller {

    public int port;

    public void setPort(int port) {
        this.port = port;
    }
    
    public abstract double getJoystick();

}