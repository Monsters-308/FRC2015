package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.Robot;
import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.ArmManager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {

	CANTalon liftR = RobotMap.armLiftTalonR;
	CANTalon liftL = RobotMap.armLiftTalonL;

	public Arm() {
		liftR.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		liftR.changeControlMode(ControlMode.Position);
		liftL.changeControlMode(ControlMode.Follower);

		liftL.set(9); // follow armLiftTalonR
		// TODO tune lift PID
		liftL.reverseOutput(true);
		liftR.reverseOutput(true);
		liftR.reverseSensor(true);
		liftR.setPID(Globals.liftP, Globals.liftI, Globals.liftD, 0.0,
				Globals.liftIZone, Globals.talonRampRate, 0);
		liftR.enableControl();
		liftL.enableControl();
		liftR.ClearIaccum();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArmManager());
	}

	public void reset() {
		liftR.setPosition(0);
		liftR.ClearIaccum();
		liftR.set(0);
		// TODO only reset during enabled
	}

	public void putValues() {
		SmartDashboard.putNumber("Arm Error", liftR.getClosedLoopError());
		SmartDashboard.putNumber("Arm setpoint", liftR.getSetpoint());
		SmartDashboard.putNumber("Arm Position", liftR.getPosition());
		SmartDashboard.putNumber("Arm Voltage", liftR.getOutputVoltage()
				/ new PowerDistributionPanel().getVoltage());
	}

	public void addHeight(double height) {
		if (liftR.getSetpoint() >= Globals.armMinRotationHeight
				&& liftR.getSetpoint() + height < Globals.armMinRotationHeight) {
			if (Robot.claw.getRotateSetpoint() != 0
					&& Robot.claw.getRotateSetpoint() != Globals.clawRotateSoftLimitMax) {
				liftR.set(Globals.armMinRotationHeight);
				return;
			}
		}
		if (liftR.getSetpoint() + height > Globals.armSoftLimitMax) {
			liftR.set(Globals.armSoftLimitMax);
		} else if (liftR.getSetpoint() + height < Globals.armSoftLimitMin) {
			liftR.set(Globals.armSoftLimitMin);
		} else {
			liftR.set(liftR.getSetpoint() + height);
		}
	}

	public void setHeight(double height) {
		if (liftR.getSetpoint() >= Globals.armMinRotationHeight
				&& height < Globals.armMinRotationHeight) {
			if (Robot.claw.getRotateSetpoint() != 0
					&& Robot.claw.getRotateSetpoint() != Globals.clawRotateSoftLimitMax) {
				liftR.set(Globals.armMinRotationHeight);
				return;
			}
		}
		if (height > Globals.armSoftLimitMax) {
			liftR.set(Globals.armSoftLimitMax);
		} else if (height < Globals.armSoftLimitMin) {
			liftR.set(Globals.armSoftLimitMin);
		} else {
			liftR.set(height);
		}
	}

	public boolean onTarget() {
		return (Math.abs(liftR.getClosedLoopError()) < Globals.lifttolerance)
				&& (Math.abs(liftR.getSpeed()) < Globals.liftspeedtolerance);
	}

	public void preCalibration() {
		liftR.changeControlMode(ControlMode.PercentVbus);
		liftR.set(-Globals.calibrationSpeed);
	}

	public void startCalibration() {
		liftR.changeControlMode(ControlMode.PercentVbus);
		liftR.set(Globals.calibrationSpeed);
	}

	public void stopCalibration() {
		liftR.set(0);
		liftR.changeControlMode(ControlMode.Position);
		liftR.setPosition(Globals.calibrationHeight);
	}

	public boolean limitSwitch() {
		return liftR.isFwdLimitSwitchClosed();
	}

	public double getArmHeight() {
		return liftR.getPosition();
	}
}