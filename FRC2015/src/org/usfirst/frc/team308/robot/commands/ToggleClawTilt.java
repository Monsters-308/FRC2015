package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClawTilt extends Command {

	public ToggleClawTilt() {
		requires(Robot.pneumatics);
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
		Robot.pneumatics.toggleClawTilt();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
