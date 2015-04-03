package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous3 extends CommandGroup {

	public Autonomous3() {
		addSequential(new TimedDrive(84.0, 2.0, false));
	}

}
