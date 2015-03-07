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
	CANTalon clawRotate = RobotMap.clawRotateTalon;

	double clawTimeout = 0;

	double count = 0.0;
	double currentsum = 0.0;

	boolean stopped = false;

	public Claw() {
		claw.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		clawRotate.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		claw.changeControlMode(ControlMode.PercentVbus);
		clawRotate.changeControlMode(ControlMode.Position);
		sweeper.changeControlMode(ControlMode.PercentVbus);
		sweeper2.changeControlMode(ControlMode.Follower);

		sweeper2.set(13); // follow sweeper
		// TODO tune rotate PID
		clawRotate.setPID(Globals.clawRotateP, Globals.clawRotateI,
				Globals.clawRotateD, 0.0, Globals.clawRotateIZone,
				Globals.talonRampRate, 0);
		clawRotate.reverseSensor(true);
		claw.reverseSensor(true);

		sweeper.enableControl();
		sweeper2.enableControl();
		claw.enableControl();
		clawRotate.enableControl();

		resetTimer();
		// TODO limit switches
	}

	public void reset() {
		clawRotate.ClearIaccum();
		clawRotate.setPosition(0);
		clawRotate.set(0);
		claw.setPosition(0);
	}

	public void moveClaw() {
		SmartDashboard.putNumber("Claw Error", clawRotate.getClosedLoopError());
		SmartDashboard.putNumber("Claw setpoint", clawRotate.getSetpoint());
		SmartDashboard.putNumber("Claw Position", clawRotate.getPosition());
		SmartDashboard.putNumber("Claw Grab Current", claw.getOutputCurrent());
		SmartDashboard.putNumber("Claw Grab Voltage",
				claw.getSetpoint() * claw.getBusVoltage());
		SmartDashboard
				.putBoolean("limit1", clawRotate.isFwdLimitSwitchClosed());
		SmartDashboard
				.putBoolean("limit2", clawRotate.isRevLimitSwitchClosed());
		SmartDashboard.putNumber("Claw grab posistion", claw.getPosition());
		double current = new PowerDistributionPanel().getCurrent(15);
		if (count > 15) {
			count = 0;
			currentsum = 0;
		} else {
			currentsum += current;
			count += 1;
		}
		if (Globals.clawOpen) {
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
		} else {
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
		}
		if (!DriverStation.getInstance().isAutonomous()) {
			if (Robot.arm.getArmHeight() > Globals.armMinRotationHeight) {
				addRotate(Globals.clawSpeed * Robot.oi.codriver.getRawAxis(3));
			}
			sweeper.set(Globals.sweeperMaxPercentage * Robot.oi.codriver.getY());
		}
	}

	public void addRotate(double pos) {
		if (clawRotate.getSetpoint() + pos > Globals.clawRotateSoftLimitMax) {
			clawRotate.set(Globals.clawRotateSoftLimitMax);
		} else if (clawRotate.getSetpoint() + pos < 0) {
			clawRotate.set(0);
		} else {
			clawRotate.set(clawRotate.getSetpoint() + pos);
		}
	}

	public void rotateClaw(double pos) {
		if (pos > Globals.clawRotateSoftLimitMax) {
			clawRotate.set(Globals.clawRotateSoftLimitMax);
		} else if (pos < 0) {
			clawRotate.set(0);
		} else {
			clawRotate.set(pos);
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClawManager());
	}

	public int rotateError() {
		return clawRotate.getClosedLoopError();
	}

	public void preCalibration() {
		clawRotate.changeControlMode(ControlMode.PercentVbus);
		clawRotate.set(Globals.calibrationSpeed);
	}

	public void startCalibration() {
		clawRotate.changeControlMode(ControlMode.PercentVbus);
		clawRotate.set(-Globals.calibrationSpeed);
	}

	public void stopCalibration() {
		clawRotate.set(0);
		clawRotate.changeControlMode(ControlMode.Position);
		clawRotate.setPosition(Globals.clawRotateSoftLimitMin);
	}

	public boolean limitSwitch() {
		return clawRotate.isRevLimitSwitchClosed();
	}

	public void openClaw() {
		resetclaw();
		Globals.clawOpen = true;
	}

	public void closeClaw() {
		resetclaw();
		Globals.clawOpen = false;
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
		clawRotate.disableControl();
		clawRotate.setPID(Globals.clawRotateP, Globals.clawRotateI,
				Globals.clawRotateD, 0.0, Globals.clawRotateIZone,
				Globals.talonRampRate, 0);
		clawRotate.enableControl();
	}

	public void resetTimer() {
		clawTimeout = System.currentTimeMillis();
	}

	public void adjustClaw(boolean positive) {
		if (positive) {
			clawRotate.setPosition(clawRotate.getPosition()
					+ Globals.adjustCount);
		} else {
			clawRotate.setPosition(clawRotate.getPosition()
					- Globals.adjustCount);
		}
	}

	public double getRotateSetpoint() {
		return clawRotate.getSetpoint();
	}

}
