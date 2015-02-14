package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveArm extends Command {

	double position;

	public MoveArm(double pos) {
		requires(Robot.arm);
		position = pos;
	}

	@Override
	protected void initialize() {
		Robot.arm.setHeight(position);
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
