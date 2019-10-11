
package frc.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.Robot;

public class Ping {
    public static Ultrasonic ultra;

    static {
        ultra = new Ultrasonic(6, 5);
    }

    // creaes the ultra object andassigns ultra to be an ultrasonic sensor which
    // uses DigitalOutput 1 for
    // the echo pulse and DigitalInput 1 for the trigger pulse

    public void ultrasonicSample() {
        ultra.setAutomaticMode(true);

        double range = ultra.getRangeInches();
        System.out.println(range);
        if (range >= 20 && range <= 35) {
            System.out.println("In range");
            Robot.m_drivetrainsubsystem.drive(0.2, 5, 1);
        }
    }
}