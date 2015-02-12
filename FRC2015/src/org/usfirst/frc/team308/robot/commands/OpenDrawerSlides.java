package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class OpenDrawerSlides extends Command {

	public OpenDrawerSlides() {
		requires(Robot.pneumatics);
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
		Robot.pneumatics.setDrawerSlides(Value.kForward);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
