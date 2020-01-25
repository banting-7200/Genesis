package frc.robot.controllers;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticsController extends Controller {

    int CANID;
    int DeviceAddress;

    private Solenoid pneumatics;

public void pneumatics(int CANID, int DeviceAddress) {
    this.CANID = CANID;
    this.DeviceAddress = DeviceAddress;
    try {
        pneumatics = new Solenoid(CANID, DeviceAddress);
    } catch (UncleanStatusException e) {
        System.out.println("Error initializing PneumaticsController.");
    }
}

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getZ() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getButton(int button) {
        // TODO Auto-generated method stub
        return false;
    }

}