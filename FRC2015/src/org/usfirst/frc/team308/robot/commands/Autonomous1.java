package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous1 extends CommandGroup {

	public Autonomous1() {
		addParallel(new OpenClaw());
		addParallel(new MoveArm(0));
		double dist = Robot.ultrasonic.getDistance();
		addSequential(new Drive(dist, false));
		addSequential(new SetSweeper(0.4));
		addSequential(new DoNothing(2.0));
		addSequential(new CloseClaw());
		addSequential(new DoNothing(2.0));
		addParallel(new MoveArm(Globals.toteHeight));
		addSequential(new Drive(-dist, false));
		addSequential(new Drive(60, true));
		
		dist = Robot.ultrasonic.getDistance();
		addSequential(new Drive(dist, false));
		addSequential(new MoveArm(Globals.toteHeight-100));
		addSequential(new DoNothing(2.0));
		addSequential(new CloseClaw());
		addSequential(new DoNothing(2.0));
		addParallel(new MoveArm(Globals.toteHeight));
		addSequential(new Drive(-dist, false));
		addSequential(new Drive(60, true));
	}

}
