package com.VDK.StarCraftBot;

import bwapi.*;

/**
 * takes care of training units
 * @author Administrator
 */
public class TrainingManager implements IGameObserver{
	private Game game;
	private Player player;
	public void update(Game game, Player player)
	{
		this.game = game;
		this.player = player;
	}
    /**
     * Train a unit
     * @param UnitType The type of unit to train
     */
    public void trainUnit(UnitType uType)
    {
        int buildingID = findBuildingID(uType);
        boolean canFindBuilding = (buildingID != -1);
        if(canFindBuilding)
        {
            Unit building = game.getUnit(buildingID);
            building.train(uType);
            game.printf("Training " + uType.toString());
        }
        else
        {
            game.printf("Error: Can't train " + uType.toString());
        }
    }
    /**
     * Iterate through the units and find the most unoccupied one to train the unit
     * @param  uType the type of building need to train the unit
     * @return The building ID needed, returns -1 if can't find any
     */
    private int findBuildingID(UnitType uType)
    {
        int lowestTrainCount = 0;
        int mostSuitableBuildingID = -1;
        for (Unit currentBuilding : player.getUnits())
        {
            if (currentBuilding.canTrain(uType))
            {
                int trainCount = currentBuilding.getRemainingTrainTime();
            // If this building is suitable or if it it the first building we check
            // First off, the ideal situation:
                if (!currentBuilding.isTraining()) return currentBuilding.getID();
                if (trainCount < lowestTrainCount || lowestTrainCount == 0){
                    lowestTrainCount = trainCount;
                    mostSuitableBuildingID = currentBuilding.getID();
                }
            }
        }
        return mostSuitableBuildingID;
    }
}
