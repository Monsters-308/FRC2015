package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmManager extends Command {

	public ArmManager() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.arm.addHeight(Globals.liftSpeed * -Robot.oi.codriver.getZ());
		Robot.arm.putValues();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		end();
	}

}
