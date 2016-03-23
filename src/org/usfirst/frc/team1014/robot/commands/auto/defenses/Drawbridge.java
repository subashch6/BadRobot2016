package org.usfirst.frc.team1014.robot.commands.auto.defenses;

import org.usfirst.frc.team1014.robot.commands.auto.AutoDriveServo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1014.robot.commands.auto.AutoDriveDistanceEncoder;
import org.usfirst.frc.team1014.robot.commands.auto.AutoSallyPortArm;
import org.usfirst.frc.team1014.robot.commands.auto.PreDefinedRotation;

public class Drawbridge extends CommandGroup
{
	public Drawbridge()
	{
		this.addSequential(new AutoDriveServo(true));
		this.addSequential(new AutoSallyPortArm(new Double(3), true));
		this.addSequential(new AutoDriveDistanceEncoder(-.25, .25));
		this.addSequential(new AutoSallyPortArm(new Double(3), true));
		this.addSequential(new AutoDriveDistanceEncoder(.25, .5));
		this.addSequential(new AutoSallyPortArm(new Double(6), false));
		this.addSequential(new AutoDriveDistanceEncoder(.5, 4.3));
		this.addSequential(new PreDefinedRotation(true));
	}
}