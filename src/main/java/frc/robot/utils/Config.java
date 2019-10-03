package frc.robot.utils;

import frc.robot.controllers.Controller;
import frc.robot.controllers.LogitechGamepad;
import frc.robot.controllers.LogitechJoystick;

public class Config {

    // Semantics
    public static String name;
    public static String description;
    public static double baseSpeed;
    public static Controller mainController;

    static {
        name = "Mr. Robot Man";
        description = "The Banting 7200 robot.";
        baseSpeed = 1.0;
        mainController = new LogitechJoystick();
    }

}