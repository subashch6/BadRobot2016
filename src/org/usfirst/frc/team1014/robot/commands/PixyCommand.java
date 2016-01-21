package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.sensors.PixyCam;


public class PixyCommand extends CommandBase
{
	PixyCam pixy;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		pixy  = PixyCam.getInstance();
	}

	@Override
	public String getConsoleIdentity() {
		// TODO Auto-generated method stub
		return "pixyCam";
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		pixy.die();
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		pixy.grabAndLog();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
