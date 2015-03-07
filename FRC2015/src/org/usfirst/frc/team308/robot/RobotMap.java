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

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * 1/23/2015
 * 
 * @version 0.2.0
 */
public class RobotMap {

	public static Gyro drivetrainGyro;
	public static CANTalon drivetrainTalonRF;
	public static CANTalon drivetrainTalonRF2;
	public static CANTalon drivetrainTalonLF;
	public static CANTalon drivetrainTalonLF2;
	public static CANTalon drivetrainTalonRB;
	public static CANTalon drivetrainTalonRB2;
	public static CANTalon drivetrainTalonLB;
	public static CANTalon drivetrainTalonLB2;
	public static CANTalon armLiftTalonR;
	public static CANTalon armLiftTalonL;
	public static CANTalon clawRotateTalon;
	public static CANTalon clawTalon;
	public static CANTalon sweeperTalon;
	public static CANTalon sweeperTalon2;

	public static Ultrasonic distanceSensor;

	public static Compressor compressor;
	public static DoubleSolenoid drawerSlides;
	public static DoubleSolenoid clawTilt;

	// pneumatics drawerslides 0
	// pneumatics claw tilt 1

	public static void init() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		drivetrainGyro = new Gyro(1);
		LiveWindow.addSensor("Drivetrain", "Gyro", drivetrainGyro);
		drivetrainGyro.setSensitivity(0.007);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTdRUCTORS
		drivetrainTalonRF = new CANTalon(1);
		drivetrainTalonRF2 = new CANTalon(2);
		drivetrainTalonLF = new CANTalon(3);
		drivetrainTalonLF2 = new CANTalon(4);
		drivetrainTalonRB = new CANTalon(5);
		drivetrainTalonRB2 = new CANTalon(6);
		drivetrainTalonLB = new CANTalon(7);
		drivetrainTalonLB2 = new CANTalon(8);

		armLiftTalonR = new CANTalon(9);
		armLiftTalonL = new CANTalon(10);

		clawRotateTalon = new CANTalon(11);
		clawTalon = new CANTalon(12);
		sweeperTalon = new CANTalon(13);
		sweeperTalon2 = new CANTalon(14);

		drivetrainTalonRF.setSafetyEnabled(false);
		drivetrainTalonRF2.setSafetyEnabled(false);
		drivetrainTalonLF.setSafetyEnabled(false);
		drivetrainTalonLF2.setSafetyEnabled(false);
		drivetrainTalonRB.setSafetyEnabled(false);
		drivetrainTalonRB2.setSafetyEnabled(false);
		drivetrainTalonLB.setSafetyEnabled(false);
		drivetrainTalonLB2.setSafetyEnabled(false);
		armLiftTalonR.setSafetyEnabled(false);
		armLiftTalonL.setSafetyEnabled(false);
		clawRotateTalon.setSafetyEnabled(false);
		clawTalon.setSafetyEnabled(false);
		sweeperTalon.setSafetyEnabled(false);
		sweeperTalon2.setSafetyEnabled(false);

		distanceSensor = new Ultrasonic(1, 2, Unit.kInches);

		compressor = new Compressor(0);
		drawerSlides = new DoubleSolenoid(0, 1);
		clawTilt = new DoubleSolenoid(2, 3);
	}
}
