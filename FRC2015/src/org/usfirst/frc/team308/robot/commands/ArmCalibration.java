package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmCalibration extends CommandGroup {

	public ArmCalibration() {
		addSequential(new PreCalibrateArm());
		addSequential(new CalibrateArm());
	}
}
