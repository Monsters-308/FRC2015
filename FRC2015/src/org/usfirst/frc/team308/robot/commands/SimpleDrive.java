package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SimpleDrive extends Command {

	boolean strafe = false;
	double strength = 0.0;

	/**
	 * Drive a specified time
	 * 
	 * @param seconds
	 *            The time in seconds
	 * @param straffe
	 *            true to straffe, false to drive straight
	 */
	public SimpleDrive(double power, double seconds, boolean straffe) {
		requires(Robot.drivetrain);
		strafe = straffe;
		strength = -power;
		setTimeout(seconds);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.simpleDriveInit(strength, strafe);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.putDashboard();
		Robot.drivetrain.simpleDrive(strength, strafe);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drivetrain.mecanumDrive(0, 0, 0, true);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
