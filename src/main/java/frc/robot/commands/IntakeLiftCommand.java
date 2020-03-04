package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.SparkSubsystem;

import frc.robot.utils.Config;

public class IntakeLiftCommand extends Command{

    private SparkSubsystem liftSpark;

    public IntakeLiftCommand(){
        int SparkID = (Config.getInt("intake.lift.spark.id"));
        liftSpark = new SparkSubsystem(SparkID);

    }

    @Override
    protected void execute() {
      Controller Pilot = Config.getController("pilot.controll");
      Controller CoPilot = Config.getController("co.pilot.controll");

        boolean in = CoPilot.getButton(Config.getInt("controls.intake.lift.in"));
        boolean out = CoPilot.getButton(Config.getInt("controls.intake.lift.out"));
        if (in) {
            liftSpark.start(0.5);
          } else if (out) {
            liftSpark.start(-0.5);
          } else {
            liftSpark.stop();
          }

      
    }

    @Override
    protected boolean isFinished() {

      return false;
    }

}