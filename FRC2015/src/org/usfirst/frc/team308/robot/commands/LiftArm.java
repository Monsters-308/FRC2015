package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftArm extends Command {

	double height = 0.0;

	public LiftArm(double setpoint) {
		requires(Robot.arm);
		height = setpoint;
	}

	@Override
	protected void initialize() {
		Robot.arm.setHeight(height);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.arm.onTarget();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
