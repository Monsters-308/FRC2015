package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PreCalibrateArm extends Command {

	public PreCalibrateArm() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		Robot.arm.preCalibration();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return !Robot.arm.limitSwitch();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
