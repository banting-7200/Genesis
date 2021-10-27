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
        
        set("joystick.pilot",0);
        set("joystick.co.pilot",1);

        set("controls.base_speed", 1.0);
        set("defaults.controller_base_speed", 1.0);
        set("motors.left_motors", new int[]{3, 4});
        set("motors.right_motors", new int[]{5, 6});
        set("lift.system", new int[]{10});

        set("ping.trig", 1);
        set("ping.echo", 2);

        Controller mainController = new LogitechJoystick(getInt("joystick.pilot"));
        Controller coPilotController = new LogitechJoystick(getInt("joystick.co.pilot"));
        set("pilot.controll", mainController);
        set("co.pilot.controll", coPilotController);

         //************lift*************//
         set("controls.liftup", 11);
         set("controls.liftdown", 12);
         set("controls.lift.shift.l",5); 
         set("controls.lift.shift.r",6); 
         set("number.of.rotations",-200);
         set("lift.up.fast.pos",-179);
         set("lift.up.slow.pos",-180);
         set("lift.down.fast.pos",-50);
         set("lift.down.slow.pos",-49);
         set("lift.can.id",7);
         set("lift.switch.pin",8);
         //**********lift end************//

        //*********ColorWheel***********//
        set("color.wheel.spin.id", 1);
        set("color.wheel.shoot.id", 0);
        set("color.wheel.retract.id", 1);
        set("color.wheel.pcm.id", 1);
        set("controls.shoot.color.wheel",0);
        set("controls.retract.color.wheel",0);
        set("controls.spin.color.wheelr",9);
        set("controls.spin.color.wheell",10);
        //*******ColorWheel End*********//

        //**********Intake***********//
        set("intake.spark.id", 3);
        set("controls.intake.in", 1);
        set("controls.intake.out", 2);
        //*********Intake end*********//

        //**********Intake Lift***********//
        set("intake.lift.spark.id", 2);
        set("controls.intake.lift.in", 7);
        set("controls.intake.lift.out", 8);
        //*********Intake Lift end*********//

        //*************Pixy****************//
        set("pixy.ball.track", 3);
        //***********Pixy End**************//

        set("drive.reverse", false);

    }//hello
    
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


    /* Static variables */

    /** Semantics **/
    public static String ROBOT_NAME;
    public static String DESCRIPTION;

    /** Controls **/
    public static double CONTROLLER_BASE_SPEED;
    public static int CONTROLLER_1_PORT;
}