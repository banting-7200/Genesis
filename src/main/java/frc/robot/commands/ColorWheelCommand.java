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
        colorWheelMotor = new SparkSubsystem(1);
    }


    @Override
    protected void execute() {
        Controller controller = Config.getController("controls.main");
        boolean shootColorWheel = controller.getButton(11);
        boolean retractColorWheel = controller.getButton(12);
        if(shootColorWheel){
          System.out.println("Colr whel! XDDDD");
          colorWheelMotor.start(1);
          Robot.m_drivetrainsubsystem.setSpeed(0.5);
        } else if (retractColorWheel){
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