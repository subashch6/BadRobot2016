package org.usfirst.frc.team1014.robot.sensors;
import org.usfirst.frc.team1014.robot.utilities.Logger;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;

public class PixyCam{

	I2C pixyPort;
	private static PixyCam instance;
	
	public static PixyCam getInstance()
	{
		if(instance == null)
		{
			instance = new PixyCam();
		}
		
		return instance;
	}
	
	public PixyCam()
	{
		pixyPort = new I2C(I2C.Port.kMXP,6);
	}
	
	
	public void grabAndLog()
	{
		byte[] readData = new byte[3];
		byte[] call = new byte[1];
		call[0] = (byte) 'A';
		pixyPort.transaction(call, call.length, readData, readData.length);
		for(byte x:readData)
		{
			Logger.logThis((int) x);
		}
	}
	
	public void die()
	{
		pixyPort.free();
	}
	

}
