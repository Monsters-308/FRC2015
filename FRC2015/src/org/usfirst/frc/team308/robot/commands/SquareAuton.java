package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SquareAuton extends CommandGroup {

	public SquareAuton() {
		addSequential(new OpenClaw());
		addParallel(new ClawManager());
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(5.0));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(5.0));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(5.0));
		// addSequential(new Rotate(90.0));
	}

}
