package org.usfirst.frc.team1014.robot.commands.auto.defenses;

import org.usfirst.frc.team1014.robot.commands.auto.AutoDriveDistanceEncoder;
import org.usfirst.frc.team1014.robot.commands.auto.AutoDriveServo;
import org.usfirst.frc.team1014.robot.commands.auto.PreDefinedRotation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBar extends CommandGroup
{
	public LowBar()
	{
		this.addSequential(new AutoDriveServo(true));
		this.addSequential(new PreDefinedRotation(true));
		this.addSequential(new AutoDriveDistanceEncoder(.6, 8.409));
		// this.addSequential(new AutoRotate(ShooterAndGrabber.SHOOTER_HIGHEST_POS));
	}

}