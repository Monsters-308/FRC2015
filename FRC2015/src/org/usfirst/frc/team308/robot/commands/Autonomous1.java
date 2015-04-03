package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous1 extends CommandGroup {

	public Autonomous1() {
		addSequential(new CloseClaw());
		addParallel(new ClawManager());
		addSequential(new DoNothing(3.0));
		addParallel(new MoveArm(20000));
		addSequential(new TimedRotate(90,1.0));
		addSequential(new TimedDrive(84.0, 2.0, false));
	}

}
