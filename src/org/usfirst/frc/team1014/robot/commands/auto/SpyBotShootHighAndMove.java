package org.usfirst.frc.team1014.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpyBotShootHighAndMove extends CommandGroup
{
	public SpyBotShootHighAndMove()
	{
		this.addSequential(new AutoDriveServo(true));
		this.addSequential(new MoveToLowerRetroTape());
		this.addSequential(new AutoShoot(1.5));
		this.addSequential(new TankDriveTime(.9, .8, 1.5));
	}
}