package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ComplexDrive extends Command {

	double dist = 0.0;
	boolean strafe = false;

	/**
	 * Drive a specified distance
	 * 
	 * @param distance
	 *            The distance in inches
	 * @param straffe
	 *            true to straffe, false to drive straight
	 */
	public ComplexDrive(double distance, boolean straffe) {
		requires(Robot.drivetrain);
		dist = distance;
		strafe = straffe;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.complexDriveInit(dist, strafe);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.putDashboard();
		Robot.drivetrain.complexDrive(strafe);
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.isComplexDriveFinished();
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
