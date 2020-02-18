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

        String colorString; // Creates our string that we can use for the Smart Dashboard
        //ColorMatchResult match = Robot.m_colorMatcher.matchClosestColor(detectedColor); // Matches what we got from the colour sensor to the closest colour we have listed
    
        /*if (match.color == kBlueTarget) {
          colorString = "Blue"; // If the colour we got is closer to the blue target, set the string to Blue.
        } else if (match.color == kRedTarget) {
          colorString = "Red"; // If the colour we got is closer to the red target, set the string to Red.
        } else if (match.color == kGreenTarget) {
          colorString = "Green"; // If the colour we got is closer to the green target, set the string to Green.
        } else if (match.color == kYellowTarget) {
          colorString = "Yellow"; // If the colour we got is closer to the yellow target, set the string to Yellow.
        } else {
          colorString = "Unknown"; // If a colour doesn't match any of the above, the colour is unknown and we should disregard it.
        }
        /*
        * The below section sends the data we recieved to the Smart Dashboard
        
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);*/
    
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
}