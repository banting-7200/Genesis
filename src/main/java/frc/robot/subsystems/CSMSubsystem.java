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

    public void stop(){
        this.NEWCSM.set(0);
    }

    public void encoderup(int canadress, int rotation){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);
        System.out.println(m_encoder.getPosition());
        double liftspeed = (m_encoder.getPosition() - rotation) / -20;// this sets the lift speed and slows the motor
																			// down as it gets nearer to its stopping
																			// point

				if (liftspeed >= 1.0) {
					liftspeed = 1.0;// the motor cannot run faster than 1.0, so if a faster run is requested, this
									// will constrain the value
				}
				if (liftspeed <= -0.6) {
					liftspeed = 0.6;
				}

				this.NEWCSM.set(liftspeed);// run the motor
    }

    public void encoderdown(int canadress){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);
        System.out.println(m_encoder.getPosition());
        double liftspeed = (m_encoder.getPosition()) / -20;

				if (liftspeed <= -0.6) {
					liftspeed = -0.6;
				}

				this.NEWCSM.set(liftspeed);
       
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        // fucking usless garbage 

    }

}