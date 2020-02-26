package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.utils.Config;

public class ColorWheelCommand extends Command{
    private PneumaticsSubsystem colorwheelpiston;


    public ColorWheelCommand(){
        int CANPCM = (Config.getInt("pcm.id"));
        int PCMPort = (Config.getInt("pcm.port"));

        colorwheelpiston = new PneumaticsSubsystem(CANPCM, PCMPort);
    }


    @Override
    protected void execute() {
        Controller controller = Config.getController("controls.main");
        boolean shootColorWheel = controller.getButton(Config.getInt("controls.colorwheel.shoot"));
        boolean retractColorWheel = controller.getButton(Config.getInt("controls.colorwheel.retract"));
        if(shootColorWheel){
          colorwheelpiston.ToggleSolenoid(true);
          Robot.m_drivetrainsubsystem.setSpeed(0.5);
        } 
        if (retractColorWheel){
          this.colorwheelpiston.ToggleSolenoid(false);
          Robot.m_drivetrainsubsystem.setSpeed(1);
        }

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}