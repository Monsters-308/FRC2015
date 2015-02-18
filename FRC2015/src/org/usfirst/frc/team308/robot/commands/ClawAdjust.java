package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawAdjust extends Command {

	boolean pos;

	public ClawAdjust(boolean positive) {
		pos = positive;
	}

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
		Robot.claw.adjustClaw(pos);
	}

	@Override
	protected void interrupted() {
	}

}
