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
import org.usfirst.frc.team308.robot.Robot;
import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.TeleopDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;
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

	double lasterror = 0;
	double error = 0;
	Timer t = new Timer();

	// Initialize your subsystem here
	public Drivetrain() {
		super("Drivetrain", Globals.gyroP, 0, Globals.gyroD);
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
		setAbsoluteTolerance(0.2);
		getPIDController().setContinuous(true);
		LiveWindow.addActuator("Drivetrain", "PIDSubsystem Controller",
				getPIDController());
		getPIDController().setOutputRange(-1.0, 1.0);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
		gyro.initGyro();
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
		error = getPIDController().getSetpoint() - getGyroAngle();
		error *= 4;
		if (Math.abs(error) < Globals.gyroIZone
				&& Math.abs(error) > Globals.angletolerance
				&& error * lasterror >= 0) {
			Globals.gyroIAccumulation += error;
		} else {
			Globals.gyroIAccumulation = 0;
		}
		lasterror = error;
		return getGyroAngle();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		Globals.gyroPIDOutput = output + Globals.gyroI
				* Globals.gyroIAccumulation;
	}

	public double getGyro() {
		return -gyro.getRate();
	}

	public double getSpeed() {
		// TODO verify speeds have the correct sign
		return 0.25 * (-talonRF.getEncVelocity() + talonLF.getEncVelocity()
				- talonRB.getEncVelocity() + talonLB.getEncVelocity());
	}

	public double getDriveError() {
		return 0.25 * (Math.abs(talonRF.getClosedLoopError())
				+ Math.abs(talonLF.getClosedLoopError())
				+ Math.abs(talonRB.getClosedLoopError()) + Math.abs(talonLB
				.getClosedLoopError()));
	}

	public void disablePID() {
		if (getPIDController().isEnable()) {
			Globals.gyroIAccumulation = 0;
			getPIDController().reset(); // disables and resets integral
		}
	}

	public void enablePID() {
		if (!getPIDController().isEnable()) {
			t.stop();
			t.reset();
			t.start();
			gyro.reset();
			Globals.gyroIAccumulation = 0;
			getPIDController().enable();
			setSetpoint(0.0);
		}
	}

	public void teleop(double x, double y, double rotation) {
		if (Robot.oi.driver.getRawButton(1)) {
			x = 0.4 * x;
			y = 0.4 * y;
		}
		x = deadzone(x, 0.1, 1.0);
		y = deadzone(y, 0.1, 1.0);
		rotation = rotation * 0.5;
		rotation = deadzone(rotation, 0.2, 1.0);
		if (x == 0 && y == 0 && rotation == 0
				&& Math.abs(getGyro()) < Globals.gyroratetolerance) {
			gyro.reset();
			t.reset();
			t.start();
			Globals.gyroIAccumulation = 0;
			setSetpoint(0);
		}
		if ((x == 0.0 && y == 0) || rotation != 0 || Globals.testMode) {
			disablePID();
			mecanumDrive(x, y, rotation);
		} else if (!getPIDController().isEnable()) {
			gyro.reset();
			t.reset();
			t.start();
			Globals.gyroIAccumulation = 0;
			getPIDController().enable();
			if (getGyro() < 0) {
				setSetpoint(-getGyro() * getGyro() * Globals.gyrocorrection);
			} else {
				setSetpoint(getGyro() * getGyro() * Globals.gyrocorrection);
			}
			mecanumDrive(x, y, Globals.gyroPIDOutput);
		} else {
			mecanumDrive(x, y, Globals.gyroPIDOutput);
		}
	}

	public void mecanumDrive(double x, double y, double rotation) {
		mecanumDrive(x, y, rotation, false);
	}

	public void mecanumDrive(double x, double y, double rotation, boolean simple) {
		putDashboard();
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

		if (Globals.testMode || Globals.simpleDrive || simple) {
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
		t.reset();
		t.start();
		Globals.gyroIAccumulation = 0;
		setSetpoint(angle);
	}

	public void setGyroSetPoint(double angle) {
		setSetpoint(angle);
	}

	public void simpleDriveInit(double power, boolean strafe) {
		disablePID();
		enablePID();
		talonRF.setPosition(0);
		talonLF.setPosition(0);
		talonRB.setPosition(0);
		talonLB.setPosition(0);
		talonRF.changeControlMode(ControlMode.PercentVbus);
		talonLF.changeControlMode(ControlMode.PercentVbus);
		talonRB.changeControlMode(ControlMode.PercentVbus);
		talonLB.changeControlMode(ControlMode.PercentVbus);
	}

	public void simpleDrive(double power, boolean strafe) {
		if (strafe) {
			mecanumDrive(power, 0, Globals.gyroPIDOutput);
		} else {
			mecanumDrive(0, power, Globals.gyroPIDOutput);
		}
	}

	public double getGyroAngle() {
		return -(gyro.getAngle() - t.get() * Globals.gyrodrift);
	}

	public void drive(double distance, boolean strafe) {
		// TODO verify signs
		talonRF.changeControlMode(ControlMode.Position);
		talonLF.changeControlMode(ControlMode.Position);
		talonRB.changeControlMode(ControlMode.Position);
		talonLB.changeControlMode(ControlMode.Position);
		talonRF.setProfile(1);
		talonLF.setProfile(1);
		talonRB.setProfile(1);
		talonLB.setProfile(1);
		talonRF.setPosition(0);
		talonLF.setPosition(0);
		talonRB.setPosition(0);
		talonLB.setPosition(0);
		if (strafe) {
			talonRF.set(distance * Globals.ticksPerInch);
			talonLF.set(distance * Globals.ticksPerInch);
			talonRB.set(-distance * Globals.ticksPerInch);
			talonLB.set(-distance * Globals.ticksPerInch);
		} else {
			talonRF.set(-distance * Globals.ticksPerInch);
			talonLF.set(distance * Globals.ticksPerInch);
			talonRB.set(-distance * Globals.ticksPerInch);
			talonLB.set(distance * Globals.ticksPerInch);
		}
	}

	public void complexDriveInit(double distance, boolean strafe) {
		// TODO verify signs
		disablePID();
		enablePID();
		talonRF.changeControlMode(ControlMode.PercentVbus);
		talonRB.changeControlMode(ControlMode.PercentVbus);
		talonLB.changeControlMode(ControlMode.PercentVbus);
		talonLF.changeControlMode(ControlMode.Position);
		talonLF.setProfile(1);
		talonLF.setPosition(0);
		if (strafe) {
			talonLF.set(distance * Globals.ticksPerInch);
		} else {
			talonLF.set(distance * Globals.ticksPerInch);
		}
	}

	public boolean isComplexDriveFinished() {
		return Math.abs(talonLF.getClosedLoopError()) < Globals.distancetolerance
				&& Math.abs(talonLF.getSpeed()) < Globals.speedtolerance;
	}

	public void complexDrive(boolean strafe) {
		double lfpow = talonLF.getOutputVoltage() / talonLF.getBusVoltage();
		double magnitude = lfpow - Globals.gyroPIDOutput;
		double rf = magnitude - Globals.gyroPIDOutput;
		double lb = magnitude + Globals.gyroPIDOutput;
		double rb = magnitude - Globals.gyroPIDOutput;
		if (strafe) {
			rf = -magnitude - Globals.gyroPIDOutput;
			lb = -magnitude + Globals.gyroPIDOutput;
		}
		double largest = Math.abs(lfpow);
		if (Math.abs(rf) > largest) {
			largest = Math.abs(rf);
		} else if (Math.abs(lb) > largest) {
			largest = Math.abs(lb);
		} else if (Math.abs(rb) > largest) {
			largest = Math.abs(rb);
		}
		if (largest > 1.0) {
			rf = rf / largest;
			lb = lb / largest;
			rb = rb / largest;
		}
		talonRF.set(-rf);
		talonLB.set(lb);
		talonRB.set(-rb);
	}

	public void normalmode() { // sets motors in speed mode (with pid)
		talonRF.changeControlMode(ControlMode.Speed);
		talonLF.changeControlMode(ControlMode.Speed);
		talonRB.changeControlMode(ControlMode.Speed);
		talonLB.changeControlMode(ControlMode.Speed);
		talonRF.setProfile(0);
		talonLF.setProfile(0);
		talonRB.setProfile(0);
		talonLB.setProfile(0);
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

	public void putDashboard() {
		SmartDashboard.putNumber("rf", talonRF.getSpeed());
		SmartDashboard.putNumber("lf", talonLF.getSpeed());
		SmartDashboard.putNumber("rb", talonRB.getSpeed());
		SmartDashboard.putNumber("lb", talonLB.getSpeed());
		SmartDashboard.putNumber("rf2", talonRF.getClosedLoopError());
		SmartDashboard.putNumber("lf2", talonLF.getClosedLoopError());
		SmartDashboard.putNumber("rb2", talonRB.getClosedLoopError());
		SmartDashboard.putNumber("lb2", talonLB.getClosedLoopError());
		SmartDashboard.putNumber("lf integral", talonLF.GetIaccum());
		SmartDashboard.putNumber("gyroangle", 4 * gyro.getAngle());
		SmartDashboard.putNumber("gyroval", getGyroAngle());
		SmartDashboard.putNumber("gyroError", getPIDController().getError());
		SmartDashboard.putNumber("GyroIAcc", Globals.gyroIAccumulation);
		SmartDashboard.putNumber("gyrocorrection", Globals.gyrocorrection);
		SmartDashboard.putNumber("lf distance", talonLF.getPosition());
	}

	public void setPID() {
		getPIDController().setPID(Globals.gyroP, 0, Globals.gyroD);
		talonRF.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonLF.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonRB.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);
		talonLB.setPID(Globals.talonP, Globals.talonI, Globals.talonD, 0,
				Globals.iZone, Globals.talonRampRate, 1);

	}

	public void calibrateGyro() {
		gyro.initGyro();
	}

}
