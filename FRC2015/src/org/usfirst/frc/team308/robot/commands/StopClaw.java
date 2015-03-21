package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopClaw extends Command {

	public StopClaw() {
		requires(Robot.claw);
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
		Robot.claw.stopClaw();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
