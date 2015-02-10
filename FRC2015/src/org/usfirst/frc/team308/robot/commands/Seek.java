package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Seek extends Command {

	public Seek() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.vision.lastDetected() < 500.0) {
			Robot.drivetrain.rotate(Robot.vision.angleError());
			Robot.drivetrain.mecanumDrive(0, 0, Globals.gyroPIDOutput);
		} else {
			Robot.drivetrain.disablePID();
			Robot.drivetrain.mecanumDrive(0, 0, 0.02);
		}
		Timer.delay(0.1);
	}

	@Override
	protected boolean isFinished() {
		return false;
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
