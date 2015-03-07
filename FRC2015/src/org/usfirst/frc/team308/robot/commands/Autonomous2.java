package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous2 extends CommandGroup {

	public Autonomous2() {
		addSequential(new CloseClaw());
		addParallel(new ClawManager());
		addSequential(new DoNothing(1.5));
		addParallel(new MoveArm(20000));
		addSequential(new TimedRotate(-100.0,1.0));
		addSequential(new SimpleDrive(0.4,2.5,false));
	}

}
