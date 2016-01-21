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
		pixyPort = new I2C(I2C.Port.kOnboard, 4);
		//pixyPort = new SerialPort(19200, SerialPort.Port.kMXP);
	}
	
	public void grabAndLog()
	{
		byte[] readData = new byte[1];
		String write = "A";
		char[] arr = write.toCharArray();
		byte[] bytes = new byte[arr.length];
		for(int i = 0; i < arr.length; i++)
		{
			bytes[i] = (byte) arr[i];
		}
		boolean worked = pixyPort.writeBulk(bytes);
		if(!worked)
		{
			Logger.logThis("Works!!");
		}
		else
		{
			Logger.logThis("Didn't Work!!");
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*pixyPort.readOnly(readData, readData.length);
		
		for(byte x:readData)
		{
			Logger.logThis((char) x);
		}*/
	}
	
	public void die()
	{
		pixyPort.free();
	}
	

}
