package frc.robot.utils;

import java.util.HashMap;
import java.util.Map;

import frc.robot.controllers.LogitechJoystick;
import frc.robot.controllers.Controller;

public class Config {

    // Semantics
    public static Map<String, Object> configMap = new HashMap<>();

    static {
        set("sem.name", "Wall-E 2");
        set("sem.description", "The most epicest robot in this site of the galaxy");

        set("controls.base_speed", 1.0);
        set("defaults.controller_port", 0);
        set("defaults.controller_base_speed", 1.0);
        set("motors.left_motors", new int[]{4, 3});
        set("motors.right_motors", new int[]{5, 6});
        set("lift.system", new int[]{10});

        set("ping.trig", 1);
        set("ping.echo", 2);

        Controller mainController = new LogitechJoystick();
        mainController.setPort(getInt("defaults.controller_port"));
        set("controls.main", mainController);

        //************lift*************//
        set("controls.liftup", 5);
        set("controls.liftdown", 3);
        set("lift.id",7);
        set("number.of.rotations",200);
        //**********lift end************//

        //*********ColorWheel***********//
        set("pcm.id", 1);
        set("pcm.port", 1);
        set("controls.colorwheel.shoot", 8);
        set("controls.colorwheel.retract", 4);
        //*******ColorWheel End*********//

    }
    
    public static Object get(String name) {
        return configMap.get(name);
    }

    public static int getInt(String name) {
        return (int) get(name);
    }

    public static String getString(String name) {
        return (String) get(name);
    }

    public static double getDouble(String name) {
        return (double) get(name);
    }

    public static int[] getIntArray(String name) {
        return (int[]) get(name);
    }

    public static Controller getController(String name) {
        return (Controller) get(name);
    }

    public static void set(String name, Object obj) {
        configMap.put(name, obj);
    }
}