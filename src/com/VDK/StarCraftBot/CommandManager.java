package com.VDK.StarCraftBot;

import java.util.ArrayList;
import java.util.Stack;
import bwapi.*;

/**
 * The master of all movements made, either by the player or by the AI
 * Warning: it can only control through other managers that inherits AbstractManager
 * Warning #2: don't put specific actions in this one, put them in the managers
 */
public class CommandManager
{
    private BuildingManager buildMng;
    private TrainingManager trainMng;
    private ArmyManager armyManager;
	private CommonManager commonManager;
    public ArrayList<IGameObserver> getAllManager()
    {
    	ArrayList<IGameObserver> result = new ArrayList<>();
    	result.add(buildMng);
    	result.add(trainMng);
    	result.add(armyManager);
		result.add(commonManager);
    	return result;
    }
    private Stack<String> commandStack;
    public Stack<String>getCommandStack()
    {
    	return this.commandStack;
    }
    //_______________PUBLIC METHODS____________
    // Add command:
    /**
     * Adding a command to the command stack
     * @param command the command to be added
     */
    public void addCommand(String command)
    {
    	commandStack.push(command);
    }
    /**
     * Adding the commands in the collection to the command stack
     * @param commands the command collection
     */
    public void addCommands(Iterable<String> commands)
    {
    	for (String command : commands) {
    		commandStack.push(command);
    	}
    }
    // Construtor
	public CommandManager()
	{
		commandStack = new Stack<>();
		buildMng = new BuildingManager();
		trainMng = new TrainingManager();
		armyManager = new ArmyManager();
		commonManager = new CommonManager();
	}
	// We must update information about the game and the player to control things
    /**
     * execute all the commands by the user
     */
	public void executeCommands()
	{
        // Analyze the command first:
        while(commandStack.empty() != true)
        {
        	String command = commandStack.pop();
        	// Let's start with buildings:
			if (command.startsWith("b."))
			{
				if(CommandList.buildCommands.containsKey(command))
				{
                    buildMng.build(CommandList.buildCommands.get(command));
					continue;
				}
			}
			// And if the user want to train unit:
			if (command.startsWith("t."))
			{
				if(CommandList.trainCommands.containsKey(command))
				{
					trainMng.trainUnit(CommandList.trainCommands.get(command));
					System.out.println("Passed command to train unit: " + command);
					continue;
				}
			}
			// Or update-on
			if (command.startsWith("bo.")) {
				if (CommandList.buildAddonCommands.containsKey(command)) {
					buildMng.buildAddon(CommandList.buildAddonCommands.get(command));
					continue;
				}
			}
			// Army movements control goes here
			if (command.startsWith("a."))
			{
				if (CommandList.armyCommands.containsKey(command))
				{
					armyManager.controlArmy(CommandList.armyCommands.get(command));
					continue;
				}
			}
            // Anything else goes here
            if (CommandList.commonCommands.contains(command))
			{
				commonManager.handle(command);
			}
        }
	}
}