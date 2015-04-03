package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;
import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.ClawManager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem {

	CANTalon sweeper = RobotMap.sweeperTalon;
	CANTalon sweeper2 = RobotMap.sweeperTalon2;
	CANTalon claw = RobotMap.clawTalon;

	double clawTimeout = 0;

	double count = 0.0;
	double currentsum = 0.0;

	boolean stopped = false;

	public Claw() {
		claw.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		claw.changeControlMode(ControlMode.PercentVbus);
		sweeper.changeControlMode(ControlMode.PercentVbus);
		sweeper2.changeControlMode(ControlMode.Follower);
		sweeper2.set(13); // follow sweeper
		sweeper2.reverseOutput(true);
		claw.reverseSensor(true);

		sweeper.enableControl();
		sweeper2.enableControl();
		claw.enableControl();

		resetTimer();
		// TODO limit switches
	}

	public void reset() {
		claw.setPosition(0);
	}

	public void moveClaw() {
		SmartDashboard.putNumber("Claw Grab Current", claw.getOutputCurrent());
		SmartDashboard.putNumber("Claw Grab Voltage",
				claw.getSetpoint() * claw.getBusVoltage());
		SmartDashboard.putNumber("Claw grab posistion", claw.getPosition());
		double current = new PowerDistributionPanel().getCurrent(15);
		if (count > 15) {
			count = 0;
			currentsum = 0;
		} else {
			currentsum += current;
			count += 1;
		}
		if (Globals.clawOpen && !Globals.clawClosed) {
			if (claw.getOutputCurrent() == 0) {
				claw.set(-Globals.clawOpenCurrent);
			}
			double volts = claw.getSetpoint() + Globals.currentP
					* (claw.getOutputCurrent() - Globals.clawOpenCurrent);
			if (volts < 0) {
				claw.set(volts);
			} else {
				claw.set(0);
			}
		} else if (Globals.clawClosed && !Globals.clawOpen) {
			if (claw.getOutputCurrent() == 0) {
				claw.set(Globals.clawCloseCurrent);
			}
			double volts = claw.getSetpoint() - Globals.currentP
					* (claw.getOutputCurrent() - Globals.clawCloseCurrent);
			if (volts > 0) {
				claw.set(volts);
			} else {
				claw.set(0);
			}
		} else {
			claw.set(0);
		}
		if (!DriverStation.getInstance().isAutonomous()) {
			if (Robot.arm.getArmHeight() >= Globals.armMinRotationHeight) {
				addRotate(Globals.clawSpeed * Robot.oi.codriver.getRawAxis(3));
			}
			sweeper.set(Globals.sweeperMaxPercentage * Robot.oi.codriver.getY());
		}
	}

	public void addRotate(double pos) {
		
	}

	public void rotateClaw(double pos) {
		
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClawManager());
	}

	public int rotateError() {
		return 0;
	}

	public void preCalibration() {
	}

	public void startCalibration() {
	}

	public void stopCalibration() {
	}

	public boolean limitSwitch() {
		return true;
	}

	public void openClaw() {
		resetclaw();
		Globals.clawOpen = true;
	}

	public void closeClaw() {
		resetclaw();
		Globals.clawClosed = true;
	}
	
	public void stopClaw() {
		resetclaw();
		Globals.clawOpen = false;
		Globals.clawClosed = false;
	}

	public void setSweeper(double setting) {
		sweeper.set(setting);
	}

	public void resetclaw() {
		resetTimer();
		stopped = false;
		claw.set(0);
	}

	public void setPID() {
	}

	public void resetTimer() {
		clawTimeout = System.currentTimeMillis();
	}

	public void adjustClaw(boolean positive) {
	}

	public double getRotateSetpoint() {
		return 0;
	}

}
