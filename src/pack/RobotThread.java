package pack;


import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestRegulatedMotor;
import lejos.remote.ev3.RemoteRequestSampleProvider;

public abstract class RobotThread implements Runnable{
	private boolean flag = true;
	RemoteRequestSampleProvider sensor;
	RemoteRequestRegulatedMotor motor;
	RemoteRequestEV3 brick;
	
	
	public void run() {
		//Connect to the robot			        
			try {
				brick = new RemoteRequestEV3("10.0.1.1");
			} catch (Exception e) {					
				e.printStackTrace();
			}   
			sensor = (RemoteRequestSampleProvider) brick.createSampleProvider("S2", "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");            
			motor = (RemoteRequestRegulatedMotor) brick.createRegulatedMotor("A", 'M');       	         
			motor.setSpeed(50);
			
			//While loop to get data
			while(flag){
				int pos = motor.getTachoCount();						
				motor.rotateTo(pos + 110);
				motor.waitComplete();
				sendData(distance(), -45);				
				
				motor.rotateTo(pos + 55);
				motor.waitComplete();
				sendData(distance(), -23);
				
				motor.rotateTo(pos);
				motor.waitComplete();
				sendData(distance(), 0);
		   		
				motor.rotateTo(pos - 55);
				motor.waitComplete();
				sendData(distance(), 23);
   		
				motor.rotateTo(pos - 110);
				motor.waitComplete();
				sendData(distance(), 45);

				motor.rotateTo(pos);
			}
			
			
			//disconnect from robot
			sensor.close();
			motor.close();
			brick.disConnect();		
	}
	
	public void disconnect(){
		flag = false;
	}
		
	
	
	public abstract void sendData(float f, int angle);
	
	//gets the distance from the sensor
	public float distance(){
		float[] sample = new float[1];
		sensor.fetchSample(sample, 0);
		return sample[0];
	}
}
	
