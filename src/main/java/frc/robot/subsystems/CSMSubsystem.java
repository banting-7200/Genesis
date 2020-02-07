package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;
import com.revrobotics.CANEncoder;


import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
 
public class CSMSubsystem extends Subsystem {
    
    private CANSparkMax Name;
    private CANEncoder m_encoder;

    public void NEWCSM(int Name, int canadress) {
        this.Name = new CANSparkMax(canadress, MotorType.kBrushless);// new brushless motor object for elevator
		m_encoder = this.Name.getEncoder();// defining the encoder
		this.Name.set(0);// initially sets the motor to stop
    }

    public void start(int speed){
        this.Name.set(speed);
    }
//new CANSparkMax(DeviceID, MotorType.kBrushless);
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        // fucking usless garbage 

    }

}