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
        Controller controller = Config.getController("controls.main");
        boolean in = controller.getButton(Config.getInt("controls.intake.in"));
      boolean out = controller.getButton(Config.getInt("controls.intake.out"));
        if (in) {
            System.out.println("Button 8 ");
            intakeSpark.start(0.4);
          } else if (out) {
            intakeSpark.start(-0.4);
          } else {
            intakeSpark.stop();
          }
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}


/*
if (controller.getButton(11)) {
    System.out.println("Button 8 ");
    intakeSpark.start(0.4);
  } else if (controller.getButton(12)) {
    intakeSpark.start(-0.4);
  } else {
    intakeSpark.stop();
  }
  */