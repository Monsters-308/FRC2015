package org.usfirst.frc.team308.robot;

public class Globals {

	//set to 1 for competition bot
	public static final double armLiftPracticeBotMultiplier = 1.0;
	
	public static boolean testMode = false;
	public static boolean simpleDrive = true;

	public static double gyroPIDOutput = 0.0;

	// gyro PID constants
	public static double gyroP = 0.011;
	public static double gyroI = 0.0009;
	public static double gyroD = 0.07;
	public static double gyroIZone = 15;
	public static double gyroIAccumulation = 0; // do not change

	public static double angletolerance = 15.0;
	public static double gyroratetolerance = 3.0; // in degrees/sec

	public static double gyrocorrection = 0.005;
	
	public static double gyrodrift = 0; //per second

	// motor position PID constants
	public static double talonP = 0.1;// 0.012;
	public static double talonI = 0.0;// 0.002;
	public static double talonD = 0.0;
	public static int iZone = 1061;

	public static double distancetolerance = 3000; // in ticks
	public static double speedtolerance = 100.0; // encoder ticks/100
												// milliseconds

	// motor velocity PID constants
	public static double vtalonP = 0.2;// 0.012;
	public static double vtalonI = 0.0015;// 0.002;
	public static double vtalonD = 0.0;

	// arm lift PID constants
	public static double liftP = 0.2;
	public static double liftI = 0.0001;
	public static double liftD = 15.0;
	public static int liftIZone = 10000;
	public static int lifttolerance = 4000; // not important
	public static int liftspeedtolerance = 100;
	public static double liftSpeed = 2500.0*Globals.armLiftPracticeBotMultiplier;
	public static int calibrationHeight = (int)(-2000*Globals.armLiftPracticeBotMultiplier);
	public static double armDistanceTolerance = 100; // in ticks
	public static double armSpeedTolerance = 50.0;
	public static double drawerSlideHeightMinimum = 0.0;
	
	public static int armSoftLimitMax = (int)(86500.0*Globals.armLiftPracticeBotMultiplier);
	public static int armMinRotationHeight = 0;
	public static int armMaxDelay = 6000;
	public static int armToteHeight = (int)Math.round(19000.0*Globals.armLiftPracticeBotMultiplier);

	// claw rotate PID constants
	public static double clawRotateP = 1;
	public static double clawRotateI = 0.01;
	public static double clawRotateD = 0.0;
	public static int clawRotateIZone = 300;
	public static int clawRotateSoftLimitMax = 2600;
	public static int clawRotateSoftLimitMin = -130;
	public static int clawSpeed = 150;
	public static int adjustCount = 20;

	// maximum change in motor voltage/second (limits acceleration)
	public static double talonRampRate = 30.0;// affects all motors

	// maximum speed of slowest wheel (encoder ticks/100 milliseconds)
	public static double talonMaxSpeed = 1300.0;

	public static boolean trackCan = false;

	// claw constants
	public static int clawOpenThreshold = 100;
	public static int clawClosedThreshold = 10000;
	public static boolean clawOpen = false;
	public static boolean clawClosed = false;
	public static double clawOpenCurrent = 0.4;
	public static double clawCloseCurrent = 1.1;
	public static double clawOpenMinVoltPercent = 0.5;
	public static double currentP = 0.03;
	public static double sweeperMaxPercentage = 1.0;
	public static double clawTimeout = 4000; // in milliseconds

	// calibration
	public static double calibrationSpeed = 0.2; // from 0.0 to 1.0

	// constants
	public static final double ticksPerInch = 106.103295*5;
	public static final double ticksPerFoot = 1273.239544*5;
	public static final double dpx = 60.0 / 640.0; // degrees per camera pixel
	public static final double camWidth = 640.0; // camera width in pixels
	public static final double camHeight = 480.0; // camera height in pixels
	public static final int toteHeight = 100; // height of tote in encoder ticks
}
