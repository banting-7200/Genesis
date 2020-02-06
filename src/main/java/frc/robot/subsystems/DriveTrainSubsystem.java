package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.controllers.Controller;
import java.util.Arrays;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public abstract class DriveTrainSubsystem extends Subsystem {
    /* Motors */

    public abstract SpeedController createSpeedController(int id);

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

        drive_train = new DifferentialDrive(new CANSparkMax(3, MotorType.kBrushless), new CANSparkMax(4, MotorType.kBrushless));
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



    public abstract void drive(Controller joystick, double speed); /* {
        System.out.println(joystick.getY());
        drive_train.arcadeDrive(joystick.getY() * speed * joystick.speed, joystick.getX() * speed * joystick.speed);
    }*/

    public abstract void drive(double movementSpeed, double turnSpeed, double speed); /*{
        drive_train.arcadeDrive(movementSpeed * speed, turnSpeed * speed);
    }*/
}
