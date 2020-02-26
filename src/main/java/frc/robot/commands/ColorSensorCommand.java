package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import frc.robot.subsystems.ColorSensorSubsystem;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class ColorSensorCommand extends Command {
    public ColorSensorCommand() {
        requires(Robot.m_colorsensorsubsystem);
    }

    public ColorSensorSubsystem findColor;

    public final I2C.Port i2cPort = I2C.Port.kOnboard; // Initializes the I2C port on the RoboRIO for the Color Sensor -
                                                       // D
    public final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort); // Initializes the color sensor for use
    public final ColorMatch m_colorMatcher = new ColorMatch(); // Creates an object that lets us match colours
    public final ColorSensorSubsystem fingcolor = new ColorSensorSubsystem();

    @Override
    public void execute() {

        this.findColor.initialise();

        Color detectedColor = m_colorSensor.getColor();

        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == Robot.m_colorsensorsubsystem.kBlueTarget) {
            colorString = "Blue";
        } else if (match.color == Robot.m_colorsensorsubsystem.kRedTarget) {
            colorString = "Red";
        } else if (match.color == Robot.m_colorsensorsubsystem.kGreenTarget) {
            colorString = "Green";
        } else if (match.color == Robot.m_colorsensorsubsystem.kYellowTarget) {
            colorString = "Yellow";
        } else {
            colorString = "Unknown";
        }
        /*
         * The below section sends the data we recieved to the Smart Dashboard
         */
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}