package org.usfirst.frc.team308.robot.subsystems;

import org.usfirst.frc.team308.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	Compressor compressor = RobotMap.compressor;
	DoubleSolenoid drawerSlides = RobotMap.drawerSlides;
	DoubleSolenoid clawTilt = RobotMap.clawTilt;

	public Pneumatics() {
	}

	@Override
	protected void initDefaultCommand() {
	}
	
	

}
