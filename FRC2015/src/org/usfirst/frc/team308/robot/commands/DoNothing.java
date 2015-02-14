package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothing extends Command {

	public DoNothing(double seconds) {
		setTimeout(seconds);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drivetrain.putDashboard();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
