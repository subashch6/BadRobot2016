package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.controls.ControlsManager;
import org.usfirst.frc.team1014.robot.utilities.Logger;

public class UseShooter extends CommandBase {

	boolean usingShooter;
	double maxSpeed;

	public UseShooter()
	{
		requires(shooter);
	}

	@Override
	protected void initialize()
	{
		usingShooter = false;
		maxSpeed = .5;

		shooter.shoot(0.0);
		shooter.rotate(0.0);
	}

	@Override
	public String getConsoleIdentity()
	{
		return "UseShooter";
	}

	@Override
	protected void execute()
	{
		// TODO Auto-generated method stub
				if(ControlsManager.secondaryXboxController.isBButtonPressed() || ControlsManager.secondaryXboxController.isXButtonPressed())
				{
					if(ControlsManager.secondaryXboxController.isXButtonPressed() && maxSpeed > .5)
						maxSpeed -= .1;
					else if(ControlsManager.secondaryXboxController.isBButtonPressed() && maxSpeed < 1.0)
						maxSpeed += .1;
				}

				shooter.shoot(ControlsManager.secondaryXboxController.getRightStickY());
				
				shooter.rotate(ControlsManager.secondaryXboxController.getLeftStickY());
				
				if(ControlsManager.secondaryXboxController.isLBButtonPressed())
				{
					shooter.ringLightOn();
				}
				if(ControlsManager.secondaryXboxController.getLeftTrigger() > 0.5f)
				{
					shooter.ringLightOff();
				}

	}

	public double scaleSpeed(double speed)
	{
		return speed * maxSpeed;
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

	@Override
	protected void end()
	{
		shooter.shoot(0.0);
		shooter.rotate(0.0);
	}

	@Override
	protected void interrupted()
	{
		Logger.logThis(getConsoleIdentity() + ": I've been interrupted!");
	}

}
