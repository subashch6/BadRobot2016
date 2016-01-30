package org.usfirst.frc.team1014.utilities;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.utilities.Logger;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class SmartDashboard
{
	public static SmartDashboard smartDashboard;
	public static NetworkTable table;
	public String[] commands = { "TeleDrive", "TeleopGroup", "UseShooter" };
	private static final String commandsPackageName = "org.usfirst.frc.team1014.robot.commands.";
	private static String commandToRun;
	private static final String commandRunKey = "Command running: ";

	public SmartDashboard()
	{
		table = NetworkTable.getTable("SmartDashboard");
		setup();
		// initDashboard();
	}

	public static SmartDashboard getInstance()
	{
		if(smartDashboard == null)
		{
			smartDashboard = new SmartDashboard();
		}
		return smartDashboard;
	}

	private void initDashboard()
	{
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture("cam0");
		Logger.log(Logger.Level.Debug, "SmartDash", "Camera initialized");
	}

	private void setup()
	{
		table.putString(commandRunKey, "");
		for(String str : commands)
		{
			try
			{
				Class.forName(commandsPackageName + str);
			} catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
			table.putBoolean(str, false);
		}
	}

	public void poll()
	{
		for(String str : commands)
		{
			if(table.getBoolean(str, false))
			{
				try
				{
					Scheduler.getInstance().add((Command) Class.forName(commandsPackageName + str).newInstance());
				} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				break;
			}
		}
		table.putString(commandRunKey, commandToRun);
	}

	public void update()
	{
		table.putNumber("FrontLeftMotorValue", DriveTrain.getInstance().getFrontLeft().get());
		table.putNumber("BackLeftMotorValue", DriveTrain.getInstance().getBackLeft().get());
		table.putNumber("FrontRightMotorValue", DriveTrain.getInstance().getFrontRight().get());
		table.putNumber("BackRightMotorValue", DriveTrain.getInstance().getBackRight().get());
		table.putBoolean("IsRingLightOn", DriveTrain.getInstance().isRingLightOn());
	}
}
