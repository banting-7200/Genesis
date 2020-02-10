package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensorSubsystem extends Subsystem{
    
  public final I2C.Port i2cPort = I2C.Port.kOnboard; // Initializes the I2C port on the RoboRIO for the Color Sensor - D
  public final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort); // Initializes the color sensor for use
  public final ColorMatch m_colorMatcher = new ColorMatch(); // Creates an object that lets us match colours

  public final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429); // This line establishes what should be a like-blue colour
  public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240); // This line establishes what should be a like-green colour
  public final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114); // This line establishes what should be a like-red colour
  public final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113); // This line establishes what should be a like-yellow colour

  public void bot(){
    m_colorMatcher.addColorMatch(kBlueTarget); // These lines create the objects that let us match the colours to what they should be
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void initDefaultCommand() {
    Color detectedColor = m_colorSensor.getColor(); // Gets the colour reading from the colour sensor

    String colorString; // Creates our string that we can use for the Smart Dashboard
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor); // Matches what we got from the colour sensor to the closest colour we have listed

    if (match.color == kBlueTarget) {
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

  }
}