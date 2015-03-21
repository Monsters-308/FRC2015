package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AddArmHeight extends Command {

	double position;

	public AddArmHeight(double height) {
		requires(Robot.arm);
		position = height;
	}

	@Override
	protected void initialize() {
		Robot.arm.addHeight(position);
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
	}

	@Override
	protected void interrupted() {
		end();
	}

}
