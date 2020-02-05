package frc.robot.controllers;

import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.PingSensorSubsystem;

public class PingController extends Controller {

    int trigPin;
    int echoPin;

    PingSensorSubsystem subsystem;

    public PingController(PingSensorSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    public double getX() {
        return 0.0;
    }

    public double getY() {
        double centimetres = this.subsystem.getDistance() / 100.0;
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
        return this.subsystem.pingSensor;
    }

    public boolean getButton(int button) {
        return false;
    }

}