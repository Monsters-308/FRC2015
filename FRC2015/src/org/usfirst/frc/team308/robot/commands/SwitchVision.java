package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchVision extends Command{

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		Globals.trackcan = !Globals.trackcan;
	}

	@Override
	protected void interrupted() {
		end();
	}

}
