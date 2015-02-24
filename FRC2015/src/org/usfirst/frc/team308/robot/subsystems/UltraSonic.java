package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.RobotMap;
import org.usfirst.frc.team308.robot.commands.RunSensor;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UltraSonic extends Subsystem {

	Ultrasonic sensor = RobotMap.distanceSensor;

	public UltraSonic() {
		
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunSensor());
	}

	public void setup(){
		sensor.setEnabled(true);
		sensor.setAutomaticMode(true);
	}
	
	public double getDistance() {
		return sensor.getRangeInches();
	}

}
