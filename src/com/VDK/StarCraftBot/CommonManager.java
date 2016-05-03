package com.VDK.StarCraftBot;

import bwapi.*;

/**
 * Takes care of the common works
 */
public class CommonManager implements IGameObserver {
	private Game game;
    private Player self;
    /**
     * Update the information about the game
     * @param game The game
     * @param self The player
     */
    public void update(Game game, Player self)
    {
        this.game = game;
        this.self = self;
    }
    /**
     * handling the task in the
     * @param command The work to do
     * See "CommmandList" class to see the list of command
     */
    public void handle(String command)
    {
    	if(command == "mine")
    	{
    		// Anything else goes here:
            if(command == "mine")
            {
                //iterate through my units
                for (Unit myUnit : self.getUnits())
                {
		            //if it's an scv and it's idle, send it to the closest mineral patch
	    	        if (myUnit.getType().isWorker() && myUnit.isIdle())
	    	        {
	                	// Find the closest mineral patch
	                	Unit closeMin = findClosestMineral(myUnit);

	                	//if a mineral patch was found, send the scv to gather it
	                	if (closeMin != null)
	                	{
	                    	myUnit.gather(closeMin, false);
	                	}
	            	}
	        	}
            }
    	}
    }
    private Unit findClosestMineral(Unit unit)
    {
    	Unit closestMineral = null;
        //find the closest mineral
        for (Unit neutralUnit : game.neutral().getUnits())
        {
        	if (neutralUnit.getType().isMineralField())
        	{
            	if (closestMineral == null || unit.getDistance(neutralUnit) < unit.getDistance(closestMineral))
            	{
                	closestMineral = neutralUnit;
            	}
        	}
    	}
    	return closestMineral;
    }
}
