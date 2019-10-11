package frc.robot.utils;

import edu.wpi.first.wpilibj.Ultrasonic;

public class PingSensor {

    public Ultrasonic pingSensor;

    public boolean printDistance;

    public PingSensor(int trigPin, int echoPin) {
        this.pingSensor = new Ultrasonic(trigPin, echoPin);
        this.pingSensor.setAutomaticMode(true);
        this.printDistance = true;
    }

    public double getDistance() {
        double dist = this.pingSensor.getRangeMM();

        if (printDistance) {
            System.out.println(dist/100.0+" cm");
        }

        return dist;
    }
}