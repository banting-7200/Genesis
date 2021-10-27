package frc.robot.subsystems.base;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.controllers.Controller;
import java.util.Arrays;

public abstract class DriveTrainSubsystem extends Subsystem {
    /* Motors */

    public abstract SpeedController createSpeedController(int id);

    private double speed;

    public DriveTrainSubsystem(int[] leftMotorIDs, int[] rightMotorIDs) {

        left = new ArrayList<>();
        right = new ArrayList<>();

        for (int i : leftMotorIDs) {
            left.add(createSpeedController(i));
        }

        for (int i : rightMotorIDs) {
            right.add(createSpeedController(i));
        }

        left_group = new SpeedControllerGroup(left.get(0), Arrays.copyOfRange(left.toArray(new SpeedController[]{}), 1, left.size()));

        right_group = new SpeedControllerGroup(right.get(0), Arrays.copyOfRange(right.toArray(new SpeedController[]{}), 1, right.size()));

        drive_train = new DifferentialDrive(left_group, right_group);
    }

    public static ArrayList<SpeedController> left;
    public static ArrayList<SpeedController> right;

    /* Motor Controllers */
    public static SpeedControllerGroup left_group;
    public static SpeedControllerGroup right_group;

    /* Drive Train */
    public static DifferentialDrive drive_train;

    @Override
    public void initDefaultCommand() {
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public abstract void drive(Controller joystick);

    public abstract void drive(double x, double y);
}
