package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;
import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.ClawManager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem {

	CANTalon sweeper = RobotMap.sweeperTalon;
	CANTalon sweeper2 = RobotMap.sweeperTalon2;
	CANTalon claw = RobotMap.clawTalon;
	CANTalon clawRotate = RobotMap.clawRotateTalon;

	public Claw() {
		claw.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		clawRotate.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		sweeper2.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		claw.changeControlMode(ControlMode.PercentVbus);
		clawRotate.changeControlMode(ControlMode.Position);
		sweeper.changeControlMode(ControlMode.PercentVbus);
		sweeper2.changeControlMode(ControlMode.Follower);

		sweeper2.set(13); // follow sweeper
		// TODO tune rotate PID
		clawRotate.setPID(Globals.clawRotateP, Globals.clawRotateI,
				Globals.clawRotateD, 0.0, Globals.clawRotateIZone,
				Globals.talonRampRate, 0);
		claw.reverseSensor(true);

		sweeper.enableControl();
		sweeper2.enableControl();
		claw.enableControl();
		clawRotate.enableControl();

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
		SmartDashboard.putNumber("Claw Grab Current",
				new PowerDistributionPanel().getCurrent(15));
		SmartDashboard
				.putBoolean("limit1", clawRotate.isFwdLimitSwitchClosed());
		SmartDashboard
				.putBoolean("limit2", clawRotate.isRevLimitSwitchClosed());
		SmartDashboard.putNumber("Claw grab posistion", claw.getPosition());
		addRotate(Globals.liftSpeed * Robot.oi.codriver.getZ());
		if (!Robot.oi.codriver.getRawButton(6)) {
			if (claw.getPosition() >= Globals.clawOpenThreshold) {
				claw.set(-Globals.clawOpenPower);
			} else {
				claw.set(0);
			}
		} else {
			if (claw.getPosition() <= Globals.clawClosedThreshold) {
				claw.set(Globals.clawClosePower);
			} else {
				claw.set(0);
			}
		}
		sweeper.set(Globals.sweeperMaxPercentage * Robot.oi.codriver.getY());
	}

	public void addRotate(double pos) {
		clawRotate.set(clawRotate.getSetpoint() + pos);
	}

	public void rotateClaw(double pos) {
		clawRotate.set(pos);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClawManager());
	}

	public int rotateError(){
		return clawRotate.getClosedLoopError();
	}
	
	public void preCalibration() {
		clawRotate.changeControlMode(ControlMode.Position);
		if (clawRotate.isRevLimitSwitchClosed()) {
			clawRotate.set(clawRotate.getPosition()+500);
		} else {
			clawRotate.set(clawRotate.getPosition());
		}
	}

	public void startCalibration() {
		clawRotate.changeControlMode(ControlMode.PercentVbus);
		clawRotate.set(-Globals.calibrationSpeed);
	}

	public void stopCalibration() {
		clawRotate.set(0);
		clawRotate.changeControlMode(ControlMode.Position);
		clawRotate.setPosition(0.0);
	}

	public boolean limitSwitch() {
		return clawRotate.isFwdLimitSwitchClosed();
	}

}
