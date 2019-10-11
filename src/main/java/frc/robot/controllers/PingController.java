package frc.robot.controllers;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.utils.PingSensor;

public class PingController extends Controller {

    int trigPin;
    int echoPin;

    PingSensor pingSensor;

    public PingController(int trigPin, int echoPin) {
        this.trigPin = trigPin;
        this.echoPin = echoPin;
        try {
            pingSensor = new PingSensor(trigPin, echoPin);
        } catch (UncleanStatusException e) {
            System.out.println("Error initializing pingsensor.");
        }
    }

    public double getX() {
        return 0.0;
    }

    public double getY() {
        double centimetres = this.pingSensor.getDistance() / 100.0;
        if (centimetres <= 10) {
            return -0.5;
        } else if (centimetres >= 10 && centimetres <= 30) {
            return 0.0;
        } else if (30 >= centimetres) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    public double getZ() {
        return 0.0;
    }

    public Ultrasonic getUltrasonic() {
        return this.pingSensor.pingSensor;
    }

}