package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;
import java.util.Arrays;

public class DriveTrainSubsystem extends Subsystem {
    /* Motors */

    public DriveTrainSubsystem() {
        for (int i : (int[]) Config.get("left_motors")) {
            left.add(new Spark(i));
        }

        for (int i : (int[]) Config.get("right_motors")) {
            right.add(new Spark(i));
        }
    }

    public static ArrayList<SpeedController> left;
    public static ArrayList<SpeedController> right;

    /* Motor Controllers */
    public static SpeedControllerGroup left_group = new SpeedControllerGroup(left.get(0), Arrays.copyOfRange(left.toArray(new SpeedControllerGroup[]{}), 1, left.size()));
    public static SpeedControllerGroup right_group = new SpeedControllerGroup(right.get(0), Arrays.copyOfRange(right.toArray(new SpeedControllerGroup[]{}), 1, right.size()));

    /* Drive Train */
    public static DifferentialDrive drive_train = new DifferentialDrive(left_group, right_group);

    @Override
    public void initDefaultCommand() {
    }

    public void drive(Controller joystick, double speed) {
        drive_train.arcadeDrive(joystick.getY() * speed * joystick.speed, joystick.getX() * speed * joystick.speed);
    }

    public void drive(double movementSpeed, double turnSpeed, double speed) {
        drive_train.arcadeDrive(movementSpeed * speed, turnSpeed * speed);
    }
}
