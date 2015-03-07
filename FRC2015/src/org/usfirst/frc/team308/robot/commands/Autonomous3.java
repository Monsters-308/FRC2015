package org.usfirst.frc.team308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous3 extends CommandGroup {
	
	public Autonomous3(){
		addParallel(new MoveArm(0));
		addSequential(new SimpleDrive(0.4,2,false));
	}

}
