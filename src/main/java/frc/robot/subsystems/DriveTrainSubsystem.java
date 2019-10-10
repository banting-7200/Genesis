package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.controllers.Controller;

public class DriveTrainSubsystem extends Subsystem {
  /* Motors */
  public static Spark left_1 = new Spark(0);
  public static Spark left_2 = new Spark(1);
  public static Spark right_1 = new Spark(2);
  public static Spark right_2 = new Spark(3);

  /* Motor Controllers */
  public static SpeedControllerGroup left_group = new SpeedControllerGroup(left_1, left_2);
  public static SpeedControllerGroup right_group = new SpeedControllerGroup(right_1, right_2);

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

  /*public void drive(Controller joystick, double speed, boolean reverse) {
    int direction = reverse ? 1 : -1;
    drive_train.arcadeDrive(joystick.getY() * speed * direction, joystick.getX() * speed * direction * -1);
  }*/
}
