<<<<<<< HEAD

package org.usfirst.frc.team3603.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    //ADADtest 1 test 222222222222222222222222222222222222
    }
    
}
=======
/****************************************
 * 
 *	THOMAS 2
 *	@author CyberCoyotes
 *
 ****************************************/


package org.usfirst.frc.team3603.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	Joystick xbox1 = new Joystick(0);
	Joystick xbox2 = new Joystick(1);
	Victor left1 = new Victor(1);
	Victor right1 = new Victor(2);
	Victor left2 = new Victor(3);
	Victor right2 = new Victor(4);
	
	AnalogGyro gyro = new AnalogGyro(0);
	RobotDrive mainDrive = new RobotDrive(left2, left1, right2, right1); 
	                             //(frontLeft, rearLeft, frontRight rearRight)
	
	Encoder jerrie = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	DoubleSolenoid thrower = new DoubleSolenoid(0, 1);
	Value out = DoubleSolenoid.Value.kForward;
	Value in = DoubleSolenoid.Value.kForward;
	Timer timer = new Timer();
	
    public void robotInit() {
    	gyro.reset();
    	gyro.setSensitivity(0.5);
    	jerrie.reset();
    	timer.start();
    	
    	jerrie.setMaxPeriod(.1);
    	jerrie.setMinRate(10);
    	jerrie.setDistancePerPulse(5);
    	jerrie.setSamplesToAverage(7);
    }
    
	public void autonomousInit() {
    }
	
    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {
    	jerrie.reset();
    	while (isOperatorControl() && isEnabled()) {
    		/**********************
    		*** DRIVER CONTROLS ***
    		**********************/
    		double mag1 = -Math.pow(xbox1.getRawAxis(1), 3);
    		double mag2 = -Math.pow(xbox1.getRawAxis(5), 3);
    		
    		if(Math.abs(mag1)>=0.1 || Math.abs(mag2)>=0.1) {
    			mainDrive.tankDrive(mag1, mag2);
    		}
    		
    		while(xbox1.getRawButton(1)) {
    			mainDrive.tankDrive(0.75, 0.75);
    		}
    		double max = gyro.getAngle()+5;
			double min = gyro.getAngle()-5;
    		while(xbox1.getRawButton(2)) {
    			if(gyro.getAngle()>=min&&gyro.getAngle()<=max) {
    				left1.set(-0.75);
    				left2.set(0.75);
    				right1.set(-0.75);
    				right2.set(0.75);
    			}
    			if(gyro.getAngle()>max) {
    				left1.set(-0.75);
    				left2.set(0.9);
    				right1.set(-0.9);
    				right2.set(0.75);
    			}
    			if(gyro.getAngle()<min) {
    				left1.set(-0.9);
    				left2.set(0.75);
    				right1.set(-0.75);
    				right2.set(0.9);
    			}
    		}
    		while(xbox1.getRawButton(3)) {
    			if(gyro.getAngle()>=min && gyro.getAngle()<=max) {
    				left1.set(0.75);
    				left2.set(-0.75);
    				right1.set(0.75);
    				right2.set(-0.75);
    			}
    			if(gyro.getAngle()>max) {
    				left1.set(0.75);
    				left2.set(-0.9);
    				right1.set(0.9);
    				right2.set(-0.75);
    			}
    			if(gyro.getAngle()<min) {
    				left1.set(0.9);
    				left2.set(-0.75);
    				right1.set(0.75);
    				right2.set(-0.9);
    			}
    		}
    		while(xbox1.getRawButton(4)) {
    			mainDrive.tankDrive(-0.75, -0.75);
    		}
    		while(xbox1.getRawAxis(2)>=0.2) {
    			mainDrive.tankDrive(xbox1.getRawAxis(2), -xbox1.getRawAxis(2));
    		}
    		while(xbox1.getRawAxis(3)>=0.2) {
    			mainDrive.tankDrive(-xbox1.getRawAxis(3), xbox1.getRawAxis(3));
    		}
    		
    		/****************************
    		 *** MANIPULATOR CONTROLS *** Reserved for testing at the moment
    		 ****************************/
    		//double x = -Math.pow(xbox2.getRawAxis(2), 19/9);
    		//double y = -Math.pow(xbox2.getRawAxis(1), 19/9);
    		//double rot = -Math.pow(xbox2.getRawAxis(4), 19/9);
    		
    		//if(Math.abs(x)>=0.1 || Math.abs(y)>=0.1 || Math.abs(rot)>=0.1) {
    		//	mainDrive.mecanumDrive_Polar(x, y, rot);
    		//}
    		
    		if(xbox2.getRawButton(5)) {
    			thrower.set(out);
    		} else {
    			thrower.set(in);
    		}
    		
    		if(xbox2.getRawButton(1)) {
    			left1.set(0.75);
    		}
    		if(xbox2.getRawButton(2)) {
    			left2.set(0.75);
    		}
    		if(xbox2.getRawButton(3)) {
    			right1.set(0.75);
    		}
    		if(xbox2.getRawButton(4)) {
    			right2.set(0.75);
    		}
    		/**Sensor reading section**/
    		
    		if(gyro.getAngle()>=360) {
    			gyro.reset();
    		}
    		if(gyro.getAngle()<=-360) {
    			gyro.reset();
    		}
    		
    		SmartDashboard.putNumber("Rate", jerrie.getRate());
    		SmartDashboard.putNumber("Distance", jerrie.getDistance());
    		SmartDashboard.putNumber("Gyro Value", gyro.getAngle());
    		SmartDashboard.putNumber("Time", timer.get());
    	}
    }
    public void testPeriodic() {
    
    }
}
>>>>>>> branch 'master' of ssh://git@github.com/Lemon27/LEMON27.git
