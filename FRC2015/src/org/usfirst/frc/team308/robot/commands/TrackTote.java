package org.usfirst.frc.team308.robot.commands;

import org.usfirst.frc.team308.robot.Robot;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TrackTote extends Command {

	processinparallel visionProcessing;

	public TrackTote() {
		requires(Robot.vision);
	}

	@Override
	protected void initialize() {
		//NIVision.IMAQdxStartAcquisition(Robot.vision.session);
		//visionProcessing = new processinparallel();
		//new Thread(visionProcessing).start();// process vision in another thread
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		//visionProcessing.kill();
		//NIVision.IMAQdxStopAcquisition(Robot.vision.session);
	}

	@Override
	protected void interrupted() {
		end();
	}

	class processinparallel implements Runnable {

		volatile boolean running = true;

		@Override
		public void run() {
			while (running) {
				Robot.vision.process();
			}
		}

		public void kill() {
			running = false;
		}
	}

}