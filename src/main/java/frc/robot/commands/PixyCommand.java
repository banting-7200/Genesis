package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.Config;

public class PixyCommand extends Command{

    //Controller Pilot = Config.getController("controls.main");
    //boolean shootColorWheel = CoPilot.getPOV(Config.getInt("controls.shoot.color.wheel"));

    @Override
    protected boolean isFinished() {
        return false;
    }
}