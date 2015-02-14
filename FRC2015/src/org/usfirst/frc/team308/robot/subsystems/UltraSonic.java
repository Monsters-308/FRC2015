package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.RunSensor;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UltraSonic extends Subsystem {

	Ultrasonic sensor = RobotMap.distanceSensor;

	public UltraSonic() {
		sensor.setEnabled(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunSensor());
	}

	public void ping() {
		sensor.ping();
	}

	public double getDistance() {
		return sensor.getRangeInches();
	}

}
