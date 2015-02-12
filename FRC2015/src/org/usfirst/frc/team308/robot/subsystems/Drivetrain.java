// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.Globals;
import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends PIDSubsystem {

	Gyro gyro = RobotMap.drivetrainGyro;
	CANTalon talonRF = RobotMap.drivetrainTalonRF;
	CANTalon talonRF2 = RobotMap.drivetrainTalonRF2;
	CANTalon talonLF = RobotMap.drivetrainTalonLF;
	CANTalon talonLF2 = RobotMap.drivetrainTalonLF2;
	CANTalon talonRB = RobotMap.drivetrainTalonRB;
	CANTalon talonRB2 = RobotMap.drivetrainTalonRB2;
	CANTalon talonLB = RobotMap.drivetrainTalonLB;
	CANTalon talonLB2 = RobotMap.drivetrainTalonLB2;

	// Initialize your subsystem here
	public Drivetrain() {
		super("Drivetrain", Globals.gyroP, Globals.gyroI, Globals.gyroD);
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
		setAbsoluteTolerance(0.2);
		getPIDController().setContinuous(true);
		LiveWindow.addActuator("Drivetrain", "PIDSubsystem Controller",
				getPIDController());
		getPIDController().setOutputRange(-1.0, 1.0);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
		talonRF2.changeControlMode(ControlMode.Follower);
		talonRF2.set(2); // follow talonRF
		talonLF2.changeControlMode(ControlMode.Follower);
		talonLF2.set(4); // follow talonLF
		talonRB2.changeControlMode(ControlMode.Follower);
		talonRB2.set(6); // follow talonRB
		talonLB2.changeControlMode(ControlMode.Follower);
		talonLB2.set(8); // follow talonLB

		talonRF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonLF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonRB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonLB.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		talonRF.reverseSensor(true);
		talonLF.reverseSensor(true);
		talonRB.reverseSensor(true);
		talonLB.reverseSensor(true);

		talonRF.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonLF.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonRB.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonLB.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonRF.setPID(Globals.vtalonP, Globals.vtalonI, Globals.vtalonD, 0, 0,
				Globals.talonRampRate, 0);
		talonLF.setPID(Globals.vtalonP, Globals.vtalonI, Globals.vtalonD, 0, 0,
				Globals.talonRampRate, 0);
		talonRB.setPID(Globals.vtalonP, Globals.vtalonI, Globals.vtalonD, 0, 0,
				Globals.talonRampRate, 0);
		talonLB.setPID(Globals.vtalonP, Globals.vtalonI, Globals.vtalonD, 0, 0,
				Globals.talonRampRate, 0);
		talonRF.ClearIaccum();
		talonLF.ClearIaccum();
		talonRB.ClearIaccum();
		talonLB.ClearIaccum();
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		setDefaultCommand(new TeleopDrive());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return gyro.getAngle();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		Globals.gyroPIDOutput = output;
	}

	public double getGyro() {
		return gyro.getRate();
	}

	public double getSpeed() {
		// TODO verify speeds have the correct sign
		return 0.25 * (talonRF.getEncVelocity() + talonLF.getEncVelocity()
				+ talonRB.getEncVelocity() + talonLB.getEncVelocity());
	}

	public double getDriveError() {
		return 0.25
				* (talonRF.getClosedLoopError() + talonLF.getClosedLoopError()
						+ talonRB.getClosedLoopError() + talonLB
							.getClosedLoopError()) / Globals.ticksToFeet;
	}

	public void disablePID() {
		if (getPIDController().isEnable()) {
			getPIDController().reset(); // disables and resets integral
		}
	}

	public void enablePID() {
		if (!getPIDController().isEnable()) {
			gyro.reset();
			getPIDController().enable();
			setSetpoint(0.0);
		}
	}

	public void teleop(double x, double y, double rotation) {
		x = deadzone(x, 0.1, 1.0);
		y = deadzone(y, 0.1, 1.0);
		rotation = deadzone(rotation, 0.1, 1.0);
		if (rotation != 0.0) {
			disablePID();
			mecanumDrive(x, y, rotation);
		} else if (!getPIDController().isEnable()) {
			gyro.reset();
			getPIDController().enable();
			setSetpoint(gyro.getRate() * Globals.gyrocorrection);
			mecanumDrive(x, y, Globals.gyroPIDOutput);
		} else {
			mecanumDrive(x, y, Globals.gyroPIDOutput);
		}
	}

	public void mecanumDrive(double x, double y, double rotation) {
		SmartDashboard.putNumber("rf", talonRF.getSpeed());
		SmartDashboard.putNumber("lf", talonLF.getSpeed());
		SmartDashboard.putNumber("rb", talonRB.getSpeed());
		SmartDashboard.putNumber("lb", talonLB.getSpeed());
		SmartDashboard.putNumber("rf2", talonRF.getClosedLoopError());
		SmartDashboard.putNumber("lf2", talonLF.getClosedLoopError());
		SmartDashboard.putNumber("rb2", talonRB.getClosedLoopError());
		SmartDashboard.putNumber("lb2", talonLB.getClosedLoopError());
		SmartDashboard.putNumber("lf integral", talonLF.GetIaccum());
		SmartDashboard.putNumber("gyro", gyro.getAngle());
		SmartDashboard.putNumber("gyroError", getPIDController().getError());
		double lf;// is what will be set to the left front wheel speed
		double rf;// is what will be set to the right front wheel speed
		double lb; // is what will be set to the left back wheel speed
		double rb; // is what will be set to the right back wheel speed

		// // START MECANUM MATH ////
		x = -x;
		double magnitude = Math.sqrt(y * y + x * x);
		magnitude = magnitude * Math.sqrt(2);
		double direction = Math.atan2(x, y);
		double dirInRad = (direction + Math.PI / 4);
		double sinD = Math.sin(dirInRad);
		double cosD = Math.cos(dirInRad);
		lf = -sinD * magnitude + rotation;
		rf = -cosD * magnitude - rotation;
		lb = -cosD * magnitude + rotation;
		rb = -sinD * magnitude - rotation;
		double largest = Math.abs(lf);
		if (Math.abs(rf) > largest) {
			largest = Math.abs(rf);
		} else if (Math.abs(lb) > largest) {
			largest = Math.abs(lb);
		} else if (Math.abs(rb) > largest) {
			largest = Math.abs(rb);
		}
		if (largest > 1.0) {
			lf = lf / largest;
			rf = rf / largest;
			lb = lb / largest;
			rb = rb / largest;
		}
		// // END MECANUM MATH ////

		if (Globals.testMode) { // keep output from -1 to 1
			talonRF.set(-rf); // invert right front
			talonLF.set(lf);
			talonRB.set(-rb); // invert right back
			talonLB.set(lb);
		} else { // scale output from -maxspeed to maxspeed
			talonRF.set(-rf * Globals.talonMaxSpeed); // invert right front
			talonLF.set(lf * Globals.talonMaxSpeed);
			talonRB.set(-rb * Globals.talonMaxSpeed); // invert right back
			talonLB.set(lb * Globals.talonMaxSpeed);
		}
	}

	public double deadzone(double value, double deadband, double power) {
		if (value > deadband) {
			value -= deadband;
			value /= (1.0 - deadband);
			value = Math.pow(value, power);
			return value;
		} else if (value < -deadband) {
			value += deadband;
			value /= (1.0 - deadband);
			value = -Math.pow(Math.abs(value), power);
			return value;
		}
		return 0;
	}

	public void rotate(double angle) {
		enablePID();
		gyro.reset();
		setSetpoint(angle);
	}

	public void drive(double distance) {
		// TODO verify signs
		talonRF.changeControlMode(ControlMode.Position);
		talonLF.changeControlMode(ControlMode.Position);
		talonRB.changeControlMode(ControlMode.Position);
		talonLB.changeControlMode(ControlMode.Position);
		talonRF.setPosition(0);
		talonLF.setPosition(0);
		talonRB.setPosition(0);
		talonLB.setPosition(0);
		talonRF.set(-distance * Globals.ticksToFeet);
		talonLF.set(distance * Globals.ticksToFeet);
		talonRB.set(-distance * Globals.ticksToFeet);
		talonLB.set(distance * Globals.ticksToFeet);
	}

	public void normalmode() { // sets motors in speed mode (with pid)
		talonRF.changeControlMode(ControlMode.Speed);
		talonLF.changeControlMode(ControlMode.Speed);
		talonRB.changeControlMode(ControlMode.Speed);
		talonLB.changeControlMode(ControlMode.Speed);
		talonRF.enableControl();
		talonRF2.enableControl();
		talonLF.enableControl();
		talonLF2.enableControl();
		talonRB.enableControl();
		talonRB2.enableControl();
		talonLB.enableControl();
		talonLB2.enableControl();
	}

	public void testmode() { // sets motors in classic mode (no pid)
		talonRF.changeControlMode(ControlMode.PercentVbus);
		talonLF.changeControlMode(ControlMode.PercentVbus);
		talonRB.changeControlMode(ControlMode.PercentVbus);
		talonLB.changeControlMode(ControlMode.PercentVbus);
		talonRF.enableControl();
		talonRF2.enableControl();
		talonLF.enableControl();
		talonLF2.enableControl();
		talonRB.enableControl();
		talonRB2.enableControl();
		talonLB.enableControl();
		talonLB2.enableControl();
	}

}
