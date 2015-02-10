package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateClaw extends Command {

	double position = 0.0;

	public RotateClaw(double setpoint) {
		requires(Robot.claw);
		position = setpoint;
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
		Robot.claw.rotateClaw(position);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
