package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command {

	double rotationangle = 0.0;

	public Rotate(double angle) {
		requires(Robot.drivetrain);
		rotationangle = angle;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.rotate(rotationangle);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.mecanumDrive(0, 0, Globals.gyroPIDOutput);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getPIDController().getError()) < Globals.angletolerance
				&& Math.abs(Robot.drivetrain.getGyro()) < Globals.gyroratetolerance;
	}

	@Override
	protected void end() {
		Robot.drivetrain.mecanumDrive(0, 0, 0);
		Robot.drivetrain.disablePID();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
