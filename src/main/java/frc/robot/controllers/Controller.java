package frc.robot.controllers;

import frc.robot.utils.Config;

public abstract class Controller {

    public int port;
    public double speed;

    public Controller() {
        setPort(Config.getInt("defaults.controller_port"));
        this.speed = Config.getDouble("defaults.controller_base_speed");
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public abstract double getX(); // This is used for the turning speed (i.e, left & right);
    public abstract double getY(); // This is used for the directional speed (i.e, forwards & backwards)
    public abstract double getZ(); 

    public abstract boolean getButton(int button);

}