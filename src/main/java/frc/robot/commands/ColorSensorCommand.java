package frc.robot.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class ColorSensorCommand extends Command {
    public ColorSensorCommand() {
        requires(Robot.m_colorsensorsubsystem);
    }

    @Override
    public void execute() {
        Color detectedColor = Robot.m_colorsensorsubsystem.m_colorSensor.getColor(); // Gets the colour reading from the colour sensor

        String colorString;    
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
}