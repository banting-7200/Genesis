
package frc.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Ping {
  public static Ultrasonic ultra;

  static {
    ultra = new Ultrasonic(6, 5);
  }

  // creaes the ultra object andassigns ultra to be an ultrasonic sensor which
  // uses DigitalOutput 1 for
  // the echo pulse and DigitalInput 1 for the trigger pulse

  public void ultrasonicSample(DriveTrainSubsystem drive_train) {
    ultra.setAutomaticMode(true);
  
      double range = ultra.getRangeInches();
      System.out.println(range);
      if (range >= 20 && range <= 35) {
        System.out.println("In range");
        drive_train.drive_train.arcadeDrive(0.2, 5);
     }
  }
}