package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClawCalibration extends CommandGroup {

	public ClawCalibration() {
		addSequential(new PreCalibrateClaw());
		addSequential(new CalibrateClaw());
	}
}
