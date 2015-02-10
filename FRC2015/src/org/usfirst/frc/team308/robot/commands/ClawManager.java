package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawManager extends Command {

	public ClawManager() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		Robot.claw.reset();
	}

	@Override
	protected void execute() {
		Robot.claw.moveClaw();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
