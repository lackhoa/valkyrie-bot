package com.VDK.AI;

// The class in charge of deciding the movements of the robot

import bwapi.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class which receives information from the game and return commands
 * @author Administrator
 */
public class Commander implements com.VDK.StarCraftBot.IGameObserver
{
	// ________________FIELDS_________________
	private Game game;
	private Player player;
	// This shows how the AI reacts to signals that it receives
	private Plan plan;
	public Plan getPlan()
	{
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	// _______________CONSTRUCTORS_______________
	public Commander(Game game, Player player)
	{
		this.game = game;
		this.player = player;
		Path path = Paths.get("Plan 0.txt");
		Plan reactionPlan = new Plan();
	}
    @Override
	public void update(Game game, Player player)
	{
		this.game = game;
		this.player = player;
	}
	/**
	 * The result is based on the reaction script in the source folder
	 * @return The commands decided
     */
	public ArrayList<String> returnCommands()
	{
		ArrayList<String> result = new ArrayList<String>();
		int frameCount = game.getFrameCount();
		// The commander gets to decide things every 100 frames
		if (frameCount % 100 == 0)
		{
			String signal = "t: " + (frameCount / 100);
			// Tell the idling workers to mine: that's the rule
			result.add("mine");
			if (plan.hasReactionTo(signal))
			{
				// Do what the plan says
				result.add(plan.getReaction(signal));
			}
			else
			{
				String reaction = makeRandomCommand();
				result.add(reaction);
				// Save this reaction for future use:
				plan.update(signal, reaction);
			}
		}
		return result;
	}

	// This is just the prototype:
	private String makeRandomCommand()
	{
		ArrayList<String> possibleCommand = new ArrayList<String>();
		possibleCommand.add("t.s");
		possibleCommand.add("b.sd");
		possibleCommand.add("b.ba");
		possibleCommand.add("t.ma");
		possibleCommand.add(""); // Doing nothing is a choice, too!
		Random random = new Random();
		int index = random.nextInt(possibleCommand.size() - 1);
		return possibleCommand.get(index);
	}
}