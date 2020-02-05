package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensorSubsystem extends Subsystem{
    
  public static I2C.Port i2cPort = I2C.Port.kOnboard;
  public static ColorSensorV3 colorSensor = new ColorSensorV3 (i2cPort);

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}