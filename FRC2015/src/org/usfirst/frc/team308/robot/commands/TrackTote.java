package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.command.Command;

public class TrackTote extends Command {
	
	public TrackTote() {
		this.requires(Robot.vision);
	}

	@Override
	protected void initialize() {
		NIVision.IMAQdxStartAcquisition(Robot.vision.session);

	}

	@Override
	protected void execute() {
		Robot.vision.process();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		NIVision.IMAQdxStopAcquisition(Robot.vision.session);

	}

	@Override
	protected void interrupted() {
		end();
	}

}
