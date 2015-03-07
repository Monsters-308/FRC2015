package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimedRotate extends Command {

	double angle = 0.0;
	double time = 0.0;

	public TimedRotate(double rotationangle, double seconds) {
		requires(Robot.drivetrain);
		angle = rotationangle;
		time = seconds;
		setTimeout(seconds);
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.rotate(0);
	}

	@Override
	protected void execute() {
		if (time - timeSinceInitialized() < 0.2) {
			Robot.drivetrain.setGyroSetPoint(angle);
		} else {
			Robot.drivetrain.setGyroSetPoint(angle * timeSinceInitialized()
					/ time);
		}
		Robot.drivetrain.mecanumDrive(0, 0, Globals.gyroPIDOutput);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut()
				&& Math.abs(Robot.drivetrain.getPIDController().getError()) < Globals.angletolerance
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
