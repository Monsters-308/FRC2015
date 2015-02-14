package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunSensor extends Command {

	public RunSensor() {
		requires(Robot.ultrasonic);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Sonic Distance",
				Robot.ultrasonic.getDistance());
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
