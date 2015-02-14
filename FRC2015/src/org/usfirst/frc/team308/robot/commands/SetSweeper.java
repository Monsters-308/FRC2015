package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetSweeper extends Command {

	double setting;

	public SetSweeper(double percentage) {
		requires(Robot.claw);
		setting = percentage;
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
		Robot.claw.setSweeper(setting);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
