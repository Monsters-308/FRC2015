package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PreCalibrateClaw extends Command {

	public PreCalibrateClaw() {
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		Robot.claw.preCalibration();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return !Robot.claw.limitSwitch();
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
