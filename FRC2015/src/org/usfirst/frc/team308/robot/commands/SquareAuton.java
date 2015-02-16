package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SquareAuton extends CommandGroup {

	public SquareAuton() {
		addSequential(new RotateClaw(1313));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
		//addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
	}

}
