package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;
import org.usfirst.frc.team308.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	Compressor compressor = RobotMap.compressor;
	DoubleSolenoid drawerSlides = RobotMap.drawerSlides;
	DoubleSolenoid clawTilt = RobotMap.clawTilt;

	public Pneumatics() {
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void toggleDrawerSlides() {
		if (drawerSlides.get() == Value.kForward) {
			drawerSlides.set(Value.kReverse);
		} else if (Robot.arm.getArmHeight() > Globals.drawerSlideHeightMinimum) { // TODO
			drawerSlides.set(Value.kForward);
		}
	}

	public void toggleClawTilt() {
		if (clawTilt.get() == Value.kForward) {
			clawTilt.set(Value.kReverse);
		} else {
			clawTilt.set(Value.kForward);
		}
	}

	public void setDrawerSlides(Value value) {
		if (value == Value.kReverse
				|| (Robot.arm.getArmHeight() > Globals.drawerSlideHeightMinimum)) {
			drawerSlides.set(value);
		}
	}

	public void setClawTilt(Value value) {
		clawTilt.set(value);
	}

}
