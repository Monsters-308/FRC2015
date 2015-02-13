package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	double dist = 0.0;
	boolean strafe = false;

	public Drive(double distance, boolean straffe) {
		requires(Robot.drivetrain);
		dist = distance;
		strafe = straffe;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.drive(dist, strafe);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.putDashboard();
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getDriveError()) < Globals.distancetolerance
				&& Math.abs(Robot.drivetrain.getSpeed()) < Globals.speedtolerance;
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
