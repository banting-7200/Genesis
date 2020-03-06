package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.subsystems.CSMDriveTrain;
import frc.robot.subsystems.base.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI.Port;
import frc.robot.utils.Config;


public class AutonomousCommand extends Command{

    public Gyro gyro;		
    public AutonomousCommand(){
      requires(Robot.m_drivetrainsubsystem);
      System.out.println("haha");


    //  gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
    //  gyro = new AnalogGyro(0);
    //  gyro.reset();									
    }

    @Override
    public void execute() {
      Robot.m_drivetrainsubsystem.drive(-0.5, 0);
      System.out.println("haha");

    //  double angle = gyro.getAngle();
    //  angle = gyro.getAngle();
    //  System.out.println("gyro angle:  ");
    //  System.out.println(angle);
/*
      if (Robot.a_timer.get()<0.1){
        Robot.m_intakelift.start(0.5);
      }else{
        Robot.m_intakelift.stop();
      } 
      if (Robot.a_timer.get()>0.1){
        Robot.m_drivetrainsubsystem.drive(0.5, 0.5, 0.5);
      }if (Robot.a_timer.get()>3){
        Robot.m_drivetrainsubsystem.drive(0, 0, 0);
       // liftSpark.start(-0.5);
        //intakeSpark.start(0.6);
      }else{
        //liftSpark.stop();
        //intakeSpark.stop();
      }
      if (Robot.a_timer.get()>7){
        Robot.m_drivetrainsubsystem.drive(0.5, -0.5, 0.5);
        if(angle<90){
          Robot.m_drivetrainsubsystem.drive(0, 0, 0);
        }else{
          Robot.m_drivetrainsubsystem.drive(0.5, -0.5, 0.5);
        }
      }
*/

    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}