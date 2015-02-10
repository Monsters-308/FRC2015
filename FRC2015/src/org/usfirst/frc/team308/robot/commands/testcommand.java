package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class testcommand extends Command {

	public testcommand() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		Robot.arm.reset();
	}

	@Override
	protected void execute() {
		Robot.arm
				.addHeight(Globals.liftSpeed * Robot.oi.codriver.getThrottle());
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
