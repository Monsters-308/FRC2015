package org.usfirst.frc.team308.robot;

public class Globals {

	public static boolean testMode = true;
	public static boolean simpleDrive = false;

	public static double gyroPIDOutput = 0.0;

	// gyro PID constants
	public static double gyroP = 0.006;
	public static double gyroI = 0.0;
	public static double gyroD = 0.0;

	public static double angletolerance = 1.0;
	public static double gyroratetolerance = 3.0; // in degrees/sec

	public static double gyrocorrection = 0.0;

	// motor position PID constants
	public static double talonP = 0.1;// 0.012;
	public static double talonI = 0.0;// 0.002;
	public static double talonD = 0.0;
	public static int iZone = 1200;

	public static double distancetolerance = 100; // in ticks
	public static double speedtolerance = 50.0; // encoder ticks/100
												// milliseconds

	// motor velocity PID constants
	public static double vtalonP = 0.2;// 0.012;
	public static double vtalonI = 0.0015;// 0.002;
	public static double vtalonD = 0.0;

	// arm lift PID constants
	public static double liftP = 0.2;
	public static double liftI = 0.0;
	public static double liftD = 0.1;
	public static int liftIZone = 0;
	public static int lifttolerance = 0; // not important
	public static int liftspeedtolerance = 0; // not important
	public static double liftSpeed = 500.0;
	public static int armSoftLimitMax = 83000;
	public static int calibrationHeight = -1000;
	public static double armDistanceTolerance = 100; // in ticks
	public static double armSpeedTolerance = 50.0;

	// claw rotate PID constants
	public static double clawRotateP = 0.3;
	public static double clawRotateI = 0.0002;
	public static double clawRotateD = 0.0;
	public static int clawRotateIZone = 0;
	public static int clawRotateSoftLimitMax = 1313;
	public static int clawRotateSoftLimitMin = -1313;

	// maximum change in motor voltage/second (limits acceleration)
	public static double talonRampRate = 30.0;// affects all motors

	// maximum speed of slowest wheel (encoder ticks/100 milliseconds)
	public static double talonMaxSpeed = 1300.0;

	public static boolean trackCan = false;

	// claw constants
	public static int clawOpenThreshold = 100;
	public static int clawClosedThreshold = 10000;
	public static boolean clawOpen = true;
	public static double clawOpenCurrent = 0.5;
	public static double clawCloseCurrent = 2.0;
	public static double currentP = 0.02;
	public static double sweeperMaxPercentage = 0.5;

	// calibration
	public static double calibrationSpeed = 0.2; // from 0.0 to 1.0

	// constants
	public static final double ticksPerInch = 106.103295;
	public static final double ticksPerFoot = 1273.239544;
	public static final double dpx = 60.0 / 640.0; // degrees per camera pixel
	public static final double camWidth = 640.0; // camera width in pixels
	public static final double camHeight = 480.0; // camera height in pixels
	public static final int toteHeight = 100; // height of tote in encoder ticks
}
