package org.usfirst.frc.team308.robot;

public class Globals {
	
	public static boolean testMode = false;

	public static double gyroPIDOutput = 0.0;

	// gyro PID constants
	public static double gyroP = 0.0007;
	public static double gyroI = 0.0;
	public static double gyroD = 0.00085;

	// motor PID constants
	public static double talonP = 0.1;//0.002;
	public static double talonI = 0;//0.012;
	public static double talonD = 0;

	// maximum change in motor voltage/second (limits acceleration)
	public static double talonRampRate = 4.0;

	// maximum speed of slowest wheel (encoder ticks/100 milliseconds)
	public static double talonMaxSpeed = 1300.0;
}
