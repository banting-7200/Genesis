package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;
import com.revrobotics.CANEncoder;


import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
 
public class CSMSubsystem extends Subsystem {

    public CANSparkMax NEWCSM;
    private CANEncoder m_encoder;

    public CSMSubsystem(int canadress) {
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);// new brushless motor object for elevator
        this.NEWCSM.set(0);// initially sets the motor to stop
    }


    public void start(int speed){
        this.NEWCSM.set(speed);
    }

    public void encoder(int revolutions){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.m_encoder.getPosition();
        this.NEWCSM.set(0);// initially sets the motor to stop
        if (m_encoder.getPosition() <= 0){
            double liftspeed = (m_encoder.getPosition() + 200);
        }

    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        // fucking usless garbage 

    }

}