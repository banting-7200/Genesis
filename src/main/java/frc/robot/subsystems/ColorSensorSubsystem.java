package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensorSubsystem extends Subsystem{
  public final ColorMatch m_colorMatcher = new ColorMatch(); // Creates an object that lets us match colours

  public final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429); // This line establishes what should be a like-blue colour
  public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240); // This line establishes what should be a like-green colour
  public final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114); // This line establishes what should be a like-red colour
  public final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113); // This line establishes what should be a like-yellow colour


  public void initialise() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }
  
  @Override
  public void initDefaultCommand() {


  }
}