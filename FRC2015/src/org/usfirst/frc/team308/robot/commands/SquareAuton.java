package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SquareAuton extends CommandGroup {

	public SquareAuton() {
		addSequential(new CloseClaw());
		addParallel(new ClawManager());
		addSequential(new DoNothing(2));
		addSequential(new MoveArm(20000));
		addSequential(new SimpleDrive(0.4,4,false));
		addSequential(new OpenClaw());
		addParallel(new ClawManager());
		// addSequential(new RotateClaw(1313));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
		// addSequential(new Drive(60.0, false));
		// addSequential(new Rotate(90.0));
	}

}
