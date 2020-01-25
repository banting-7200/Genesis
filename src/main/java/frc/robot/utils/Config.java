package frc.robot.utils;

import java.util.HashMap;
import java.util.Map;

import frc.robot.controllers.LogitechJoystick;
import frc.robot.controllers.Controller;

public class Config {

    // Semantics
    public static Map<String, Object> configMap = new HashMap<>();

    static {
        set("sem.name", "Mr. Robot Man :)");
        set("sem.description", "The most epicest robot in this site of the galaxy");
        set("controls.base_speed", 1.0);
        set("defaults.controller_port", 0);
        set("defaults.controller_base_speed", 1.0);
        set("motors.left_motors", new int[]{0, 1});
        set("motors.right_motors", new int[]{1, 2});

        Controller mainController = new LogitechJoystick();
        mainController.setPort(0);
        set("controls.main", mainController);

    }

    public static Object get(String name) {
        return configMap.get(name);
    }

    public static void set(String name, Object obj) {
        configMap.put(name, obj);
    }
}