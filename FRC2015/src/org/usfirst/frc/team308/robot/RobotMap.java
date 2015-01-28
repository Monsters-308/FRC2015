// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team308.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * 1/23/2015
 * 
 * @version 0.1.0
 */
public class RobotMap {

	public static Gyro drivetrainGyro;
	public static CANTalon drivetrainTalonRF;
	// public static CANTalon drivetrainTalonRF2;
	public static CANTalon drivetrainTalonLF;
	// public static CANTalon drivetrainTalonLF2;
	public static CANTalon drivetrainTalonRB;

	// public static CANTalon drivetrainTalonRB2;
	public static CANTalon drivetrainTalonLB;

	// public static CANTalon drivetrainTalonLB2;

	static int session;
	static Image frame;
	static NIVision.Rect rect;

	public static void init() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		drivetrainGyro = new Gyro(0);
		LiveWindow.addSensor("Drivetrain", "Gyro", drivetrainGyro);
		drivetrainGyro.setSensitivity(0.007);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		session = NIVision.IMAQdxOpenCamera("cam0",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);

		drivetrainTalonRF = new CANTalon(2);
		// drivetrainTalonRF2 = new CANTalon(3);
		drivetrainTalonLF = new CANTalon(4);
		// drivetrainTalonLF2 = new CANTalon(5);
		drivetrainTalonRB = new CANTalon(6);
		// drivetrainTalonRB2 = new CANTalon(7);
		drivetrainTalonLB = new CANTalon(8);
		// drivetrainTalonLB2 = new CANTalon(9);

		drivetrainTalonRF.setSafetyEnabled(false);
		// drivetrainTalonRF2.setSafetyEnabled(true);
		drivetrainTalonLF.setSafetyEnabled(false);
		// drivetrainTalonLF2.setSafetyEnabled(true);
		drivetrainTalonRB.setSafetyEnabled(false);
		// drivetrainTalonRB2.setSafetyEnabled(true);
		drivetrainTalonLB.setSafetyEnabled(false);
		// drivetrainTalonLB2.setSafetyEnabled(true);

		drivetrainTalonRF.setExpiration(1);
		drivetrainTalonLF.setExpiration(1);
		drivetrainTalonRB.setExpiration(1);
		drivetrainTalonLB.setExpiration(1);

		// drivetrainTalonRF2.changeControlMode(ControlMode.Follower);
		// drivetrainTalonRF2.set(2); // follow talonRF
		// drivetrainTalonLF2.changeControlMode(ControlMode.Follower);
		// drivetrainTalonLF2.set(4); // follow talonLF
		// drivetrainTalonRB2.changeControlMode(ControlMode.Follower);
		// drivetrainTalonRB2.set(6); // follow talonRB
		// drivetrainTalonLB2.changeControlMode(ControlMode.Follower);
		// drivetrainTalonLB2.set(8); // follow talonLB

		drivetrainTalonRF.setProfile(0);
		drivetrainTalonLF.setProfile(0);
		drivetrainTalonRB.setProfile(0);
		drivetrainTalonLB.setProfile(0);

		drivetrainTalonRF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		drivetrainTalonRF.changeControlMode(ControlMode.Speed);
		drivetrainTalonLF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		drivetrainTalonLF.changeControlMode(ControlMode.Speed);
		drivetrainTalonRB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		drivetrainTalonRB.changeControlMode(ControlMode.Speed);
		drivetrainTalonLB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		drivetrainTalonLB.changeControlMode(ControlMode.Speed);

		drivetrainTalonRF.reverseSensor(true);
		drivetrainTalonLF.reverseSensor(true);
		drivetrainTalonRB.reverseSensor(true);
		drivetrainTalonLB.reverseSensor(true);

		SmartDashboard.putNumber("gyroP", Globals.gyroP);
		SmartDashboard.putNumber("gyroI", Globals.gyroI);
		SmartDashboard.putNumber("gyroD", Globals.gyroD);
	}
}
