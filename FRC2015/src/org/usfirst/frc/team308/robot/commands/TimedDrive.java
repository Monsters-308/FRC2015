package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimedDrive extends Command {

	double distance = 0.0;
	double time = 0.0;
	boolean strafe = false;

	public TimedDrive(double drivedistance, double seconds, boolean straffe) {
		requires(Robot.drivetrain);
		distance = drivedistance;
		time = seconds;
		strafe = straffe;
		setTimeout(seconds);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.complexDriveInit(0, strafe);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.putDashboard();
		if (time - timeSinceInitialized() < 0.2) {
			Robot.drivetrain.setDriveDistance(distance);
		} else {
			Robot.drivetrain.setDriveDistance(distance * timeSinceInitialized()
					/ time);
		}
		Robot.drivetrain.complexDrive(strafe);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut()
				&& Robot.drivetrain.isComplexDriveFinished();
	}

	@Override
	protected void end() {
		Robot.drivetrain.normalmode();
		Robot.drivetrain.mecanumDrive(0, 0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
