package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.subsystems.base.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.Config;


public class AutonomousCommand extends Command{

    public Gyro gyro;
    protected Timer timer;	
    private SparkSubsystem liftSpark;
    private SparkSubsystem intakeSpark;
		

    public AutonomousCommand(){
      gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
      gyro = new AnalogGyro(0);
      timer = new Timer();
      gyro.reset();									
		  timer.start();
      timer.reset();
      int SparkID = (Config.getInt("intake.lift.spark.id"));
      liftSpark = new SparkSubsystem(SparkID);
      int SparkID2 = (Config.getInt("intake.spark.id"));
      intakeSpark = new SparkSubsystem(SparkID);
    }

    @Override
    protected void execute() {
      double angle = gyro.getAngle();
      angle = gyro.getAngle();
      System.out.println("gyro angle:  ");
      System.out.println(angle);

      if (timer.get()<0.1){
        liftSpark.start(0.5);
      }else{
        liftSpark.stop();
      } 
      if (timer.get()>0.1){
        Robot.m_CsmDriveTrain.manualdrive(0.5, 0.5);
      }if (timer.get()>3){
        Robot.m_CsmDriveTrain.manualdrive(0, 0);
        liftSpark.start(-0.5);
        intakeSpark.start(0.6);
      }else{
        liftSpark.stop();
        intakeSpark.stop();
      }
      if (timer.get()>7){
        Robot.m_CsmDriveTrain.manualdrive(0.5, -0.5);
        if(angle<90){
          Robot.m_CsmDriveTrain.manualdrive(0, 0);
        }else{
          Robot.m_CsmDriveTrain.manualdrive(0.5, -0.5);
        }
      }


    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}