package org.usfirst.frc.team308.robot;

public class Globals {

	public static boolean testMode = false;

	public static double gyroPIDOutput = 0.0;

	// gyro PID constants
	public static double gyroP = 0.01;
	public static double gyroI = 0.003;
	public static double gyroD = 0.0;

	public static double angletolerance = 1.0;
	public static double gyroratetolerance = 3.0; // in degrees/sec

	public static double gyrocorrection = 0.0;

	// motor position PID constants
	public static double talonP = 0.1;// 0.012;
	public static double talonI = 0.002;// 0.002;
	public static double talonD = 0.0;
	public static int iZone = 0;

	public static double distancetolerance = 0.2; // in feet
	public static double speedtolerance = 50.0; // encoder ticks/100
												// milliseconds

	// motor velocity PID constants
	public static double vtalonP = 0.1;// 0.012;
	public static double vtalonI = 0.002;// 0.002;
	public static double vtalonD = 0.0;

	// maximum change in motor voltage/second (limits acceleration)
	public static double talonRampRate = 36.0;

	// maximum speed of slowest wheel (encoder ticks/100 milliseconds)
	public static double talonMaxSpeed = 1300.0;

	public static boolean trackCan = false;

	// constants
	public static final double dpx = 60.0 / 640.0; // degrees per camera pixel
	public static final double ticksToFeet = 0.0; // encoder ticks in a foot
	public static final double camWidth = 640.0; // camera width in pixels
	public static final double camHeight = 480.0; // camera height in pixels
}
