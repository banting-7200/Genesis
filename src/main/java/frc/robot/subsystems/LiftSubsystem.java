// Created by Danny at the bequest of Kaden
// Also makes for good coding practice

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.controllers.Controller;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.utils.Config;

public class LiftSubsystem extends Subsystem {

    public CSMSubsystem Lift;//creats a vareable for a CSM (CSMSubsystem)
    public PneumaticsSubsystem LiftlockPiston;
    public static int SRotation;

    public LiftSubsystem() {
        this.Lift = new CSMSubsystem(7);
        this.LiftlockPiston = new PneumaticsSubsystem(1, 2);
    }

    @Override
    protected void initDefaultCommand() {
      // TODO Auto-generated method stub

    }



    /*
    boolean theLift = controller.getButton(5);
    boolean theLift1 = controller.getButton(3);

    this.LiftlockPiston.ToggleSolenoid(false);

    public void checkLift(){ // srotation -- Specified Rotation
        if (theLift){
            this.Lift.encoderup(7, SRotation);
            this.LiftlockPiston.ToggleSolenoid(true);//turns the lift lock off
          }else{
            this.Lift.stop();
            this.LiftlockPiston.ToggleSolenoid(false);//urns the lift lock on
          }
          if (theLift1){
            this.Lift.encoderdown(7);
            this.LiftlockPiston.ToggleSolenoid(true);
      
          }else{
            this.Lift.stop();
            this.LiftlockPiston.ToggleSolenoid(false);
          }
    }
    */
}