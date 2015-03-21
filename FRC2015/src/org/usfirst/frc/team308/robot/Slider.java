package org.usfirst.frc.team308.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class Slider extends Trigger {
    
	boolean positive = false;
	double threshold = 0.0;
	Joystick J;
	
	public Slider(Joystick stick, double threshold, boolean positive){
		this.positive = positive;
		this.threshold = threshold;
		J = stick;
	}
	
    public boolean get() {
    	if(positive){
    		return J.getRawAxis(4)>threshold;
    	} else {
    		return J.getRawAxis(4)<threshold;
    	}
    }
}
