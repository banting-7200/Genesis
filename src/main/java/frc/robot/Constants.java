// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static Map<String, Object> map = new HashMap<String, Object>();

    private static Joystick currentJoystick;
    private static Joystick getJoystickInstance() {
        if(currentJoystick == null) {
            currentJoystick = new Joystick(0);
        }

        return currentJoystick;
    }

    static {
        set("robot.name", "Genesis-Updated");
        
        /* Joystick Control Constants */
        //Store the primary joystick as a constant
        set("robot.joystick", getJoystickInstance());

        //Axis labels
        set("robot.joystick.x", 0); //left-right axis on big joystick
        set("robot.joystick.y", 1); //fwd-back axis on big joystick
        
        //Declare buttons responsible for moving lift up and down.
        set("robot.joystick.button_lift_up", 5);
        set("robot.joystick.button_lift_down", 3);

        //Some Personal Preference Constants
        set("robot.joystick.invert_stick_x", false); //Invert the large joystick left-right?
        set("robot.joystick.invert_stick_y", true); //Invert the large joystick fwd-back?

        /* Robot Constants */
        //Store motors on robot grouped by sides.
        set("robot.motors.left_motors", new int[]{3, 4});
        set("robot.motors.right_motors", new int[]{5, 6});

        //Make speed a value from 0 to 1
        //The speed of motors is represented as a percentage.
        set("robot.speed", 0.45);
        set("robot.liftspeed", 0.33);
    }

    private static void set(String key, Object value) {
        //Does the key in the constants map already exist?
        if(map.putIfAbsent(key, value) != null) {
            //Notify on runtime that the given key has a duplicate.
            //Override the unabsent key afterwards
            System.out.println("Constants: key of {" + key + "} already exists, Overriding value...");
        }

        map.put(key, value);
    }

    public static Object getValue(String key) {
        // If the given key does not exist,
        // Notify on runtime and return null
        if(!map.containsKey(key)) {
            System.out.println("Constants: key of {" + key + "} does not exist within the constants.");
            return null;
        }

        return map.get(key);
    }
}
