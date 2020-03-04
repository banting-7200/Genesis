package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.SparkSubsystem;

import frc.robot.utils.Config;

public class IntakeCommand extends Command {

    private SparkSubsystem intakeSpark; 

    public IntakeCommand() {
        int SparkID = (Config.getInt("intake.spark.id"));
        intakeSpark = new SparkSubsystem(SparkID);
    }

    @Override
    protected void execute() {
      Controller Pilot = Config.getController("joystick.pilot");
      Controller CoPilot = Config.getController("joystick.co.pilot");

        boolean in = CoPilot.getButton(Config.getInt("controls.intake.in"));
        boolean out = CoPilot.getButton(Config.getInt("controls.intake.out"));
        
        boolean frontroller = Pilot.getButton(Config.getInt("controls.front.roller.pilot"));

        if (in) {
            intakeSpark.start(0.4);
          } else if (out) {
            intakeSpark.start(-0.4);
          } else {
            intakeSpark.stop();
          }
        
        if (frontroller){
          intakeSpark.start(-0.4);
        }else{
          intakeSpark.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}