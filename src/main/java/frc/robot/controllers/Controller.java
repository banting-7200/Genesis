package frc.robot.controllers;

import frc.robot.utils.Config;

public abstract class Controller {

    public int port;

    public Controller() {
        setPort((int) Config.get("defaults.controller_port"));
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public abstract double getX();
    public abstract double getY();
    public abstract double getZ();

}