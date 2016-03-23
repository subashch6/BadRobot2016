package org.usfirst.frc.team1014.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team1014.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team1014.robot.commands.auto.AutoDriveDistanceUltrasonic;
import org.usfirst.frc.team1014.robot.commands.auto.AutoGrab;
import org.usfirst.frc.team1014.robot.commands.auto.AutoShoot;
import org.usfirst.frc.team1014.robot.commands.auto.AutoTurn;
import org.usfirst.frc.team1014.robot.commands.auto.FindTarget;
// The imports for the final subsystems
import org.usfirst.frc.team1014.robot.controls.ControlsManager;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.subsystems.LEDLights;
import org.usfirst.frc.team1014.robot.subsystems.SallyPortArm;
import org.usfirst.frc.team1014.robot.subsystems.ShooterAndGrabber;
import org.usfirst.frc.team1014.robot.utilities.Logger;
import org.usfirst.frc.team1014.robot.utilities.Logger.Level;

import edu.wpi.first.wpilibj.command.Command;

/**
 * {@code CommandBase} handles all commands and instances.
 * 
 * All commands should extend this class to get access to the subsystems. This is an abstract class
 * so no instances of {@code CommandBase} should be created.
 */
public abstract class CommandBase extends Command
{
	public static DriveTrain driveTrain;
	public static ShooterAndGrabber shooter;
	public static SallyPortArm arm;
	public static LEDLights lights;

	public static ArrayList<Command> commandClasses = new ArrayList<Command>();

	// The subsystems on the final robot go here

	/**
	 * Initializes all commands that the robot will needed. This function should only be called
	 * once, from the main Robot class.
	 */
	public static void init()
	{
		// Final Subsystems

		driveTrain = DriveTrain.getInstance();
		shooter = ShooterAndGrabber.getInstance();
		arm = SallyPortArm.getInstance();
		lights = LEDLights.getInstance();
		// camera = new AxisCamera("axis-camera.local");

		ControlsManager.init();
		addAuto();
	}

	/**
	 * Creates an instance of each command used in autonomous and adds it to the
	 * {@code commandClasses} {@link ArrayList}.
	 */
	public static void addAuto()
	{
		commandClasses.add(new AutoGrab(0.0));
		commandClasses.add(new AutoShoot(0.0));
		commandClasses.add(new AutoTurn(90.0));
		commandClasses.add(new AutoDrive(0.0, 0.0));
		commandClasses.add(new AutoDriveDistanceUltrasonic(0.0, 0.0));
		commandClasses.add(new FindTarget());
	}

	public CommandBase(String name)
	{
		super(name);
	}

	public CommandBase()
	{
		super();
	}

	@Override
	/**
	 * {@inheritDoc} This should be used to initialize the command. After this method finishes
	 * execution the command should be ready to be used.
	 */
	protected abstract void initialize();

	/**
	 * @return a {@link String} with the name of the class.
	 */
	public abstract String getConsoleIdentity();

	public boolean isFinished;

	/**
	 * Called when class is {@code cancel()} or multiple commands use the same subsystem.
	 */
	@Override
	protected void interrupted()
	{
		Logger.log(Level.Error, getConsoleIdentity(), "I've been interrupted!");
		end();
	}
}
