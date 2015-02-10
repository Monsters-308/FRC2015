package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateArm extends Command {

	public CalibrateArm() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		Robot.arm.startCalibration();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.arm.limitSwitch();
	}

	@Override
	protected void end() {
		Robot.arm.stopCalibration();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
