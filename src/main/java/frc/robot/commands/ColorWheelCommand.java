package frc.robot.commands;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.utils.Config;

public class ColorWheelCommand extends Command{

    private SparkSubsystem colorWheelMotor;


    public ColorWheelCommand(){
      int SpinWheel = (Config.getInt("color.wheel.spin.id"));
      int Shoot = (Config.getInt("color.wheel.shoot.id"));
      int Retract = (Config.getInt("color.wheel.retract.id"));
      int PCMID = (Config.getInt("color.wheel.pcm.id"));
        colorWheelMotor = new SparkSubsystem(SpinWheel);
        //Robot.m_colourWheelShoot.pneumaticsSetup(PCMID,Shoot);
        //Robot.m_colourWheelsRetract.pneumaticsSetup(PCMID, Retract);
    }

//
    @Override
    protected void execute() {
        Controller Pilot = Config.getController("pilot.controll");
        Controller CoPilot = Config.getController("co.pilot.controll");

       // int shootColorWheel = CoPilot.getPOV(Config.getInt("controls.shoot.color.wheel"));
       // int retractColorWheel = CoPilot.getPOV(Config.getInt("controls.retract.color.wheel"));

        boolean spinColorWheelr = CoPilot.getButton(11);
        boolean spinColorWheell = CoPilot.getButton(12);
        
        if(spinColorWheelr){
          colorWheelMotor.start(1);
          Robot.m_drivetrainsubsystem.setSpeed(0.5);
        } else if (spinColorWheell){
          colorWheelMotor.start(-1);
          Robot.m_drivetrainsubsystem.setSpeed(1);
        } else {
          colorWheelMotor.stop();
        }

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}