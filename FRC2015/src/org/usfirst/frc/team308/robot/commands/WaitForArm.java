package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WaitForArm extends Command {

	public WaitForArm(){
		requires(Robot.arm);
		setTimeout(0.5);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.arm.onTarget() && isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}

}
