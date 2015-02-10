package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateClaw extends Command {

	public CalibrateClaw() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		Robot.claw.startCalibration();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.claw.limitSwitch();
	}

	@Override
	protected void end() {
		Robot.claw.stopCalibration();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
