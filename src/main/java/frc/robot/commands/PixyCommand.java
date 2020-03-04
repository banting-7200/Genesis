package frc.robot.commands;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.utils.Config;

public class PixyCommand extends Command{

    private SparkSubsystem intakeSpark; 
    public double turnSpeed;

    public PixyCommand(){
        int SparkID = (Config.getInt("intake.spark.id"));
        intakeSpark = new SparkSubsystem(SparkID);
    }
    private static I2C Wire = new I2C(Port.kOnboard, 1);

    public boolean ballDetect = false;
    byte[] i2cbuffer = new byte[8];

    @Override
    protected void execute() {
    Controller Pilot = Config.getController("pilot.controll");
    Controller CoPilot = Config.getController("co.pilot.controll");
    
    Wire.read(1, 2, i2cbuffer);

    if (i2cbuffer[1] == 1) {
        ballDetect = true;
    }

    boolean ballTract = CoPilot.getButton(Config.getInt("pixy.ball.track"));
    if (ballTract) {
        if (ballDetect) {
            Wire.read(1, 1, i2cbuffer);// read the pixy values
            intakeSpark.start(0.5);//starts the intake motor

            double servoangle = (i2cbuffer[0]);
            double driveAngle = (servoangle) / 20; //creats angle to make the bot follow balls

            Robot.m_CsmDriveTrain.manualdrive(0.6, turnSpeed);// makes roBoi follow balls

            turnSpeed = driveAngle;

            if (turnSpeed > 0.6) {
                turnSpeed = 0.6;
            }

            if (turnSpeed < -0.6) {
                turnSpeed = -0.6;
            }
            }
        }

    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}