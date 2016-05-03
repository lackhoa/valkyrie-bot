package com.VDK.StarCraftBot;

import bwapi.*;

/**
 * Takes care of building things
 * @author Administrator
 */
public class BuildingManager implements IGameObserver
{
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
     * Get an scv to build something
     * @param buildingType
     */
    public void build(UnitType buildingType)
    {
        for (Unit myUnit : self.getUnits())
        {
            // If the scv is idle or mining something
            boolean isWorker = myUnit.getType().isWorker();
            if (isWorker && (myUnit.isIdle() || myUnit.isStuck() || myUnit.isGatheringMinerals()))
            {
                // Check if we can build the building or not
                if (!myUnit.canBuild(buildingType)) {
                    return;
                }
                //get a nice place to build
                TilePosition buildTile =
                getBuildTile(myUnit, buildingType, self.getStartLocation());
                //and, if found, send the worker to build it (and leave others alone - break;)
                if (buildTile != null)
                {
                    if(myUnit.canBuild(buildingType, buildTile, true, true, true))
                    {
                        myUnit.build(buildingType, buildTile);
                    }
                    break;
                }
                else
                {
                    game.printf("Error: Can't find a place to build " + buildingType.toString());
                    return;
                }
            }
        }
    }
    /**
     * Buid an addon
     * @param addonType the addon type
     */
    public void buildAddon(UnitType addonType)
    {
        for (Unit currentBuilding : self.getUnits()) {
            if(currentBuilding.canBuildAddon(addonType))
            {
                currentBuilding.buildAddon(addonType);
                game.printf("Building " + addonType.toString());
                break;
            }
        }
        // If can't find anything to build addon
        game.printf("Can't build update-on " + addonType.toString());
    }
    // Returns a suitable TilePosition to build a given building type near
    // specified TilePosition aroundTile, or null if not found. (builder parameter is our worker)
    private TilePosition getBuildTile(Unit builder, UnitType buildingType, TilePosition aroundTile)
    {
        TilePosition ret = null;
        int maxDist = 3;
        int stopDist = 40;

        // Refinery, Assimilator, Extractor
        if (buildingType.isRefinery())
        {
            for (Unit n : game.neutral().getUnits())
            {
                if ((n.getType() == UnitType.Resource_Vespene_Geyser) &&
                   ( Math.abs(n.getTilePosition().getX() - aroundTile.getX()) < stopDist ) &&
                   ( Math.abs(n.getTilePosition().getY() - aroundTile.getY()) < stopDist )
                   ) return n.getTilePosition();
            }
        }
        // If it is a normal building
        while ((maxDist < stopDist) && (ret == null))
        {
            for (int i=aroundTile.getX()-maxDist; i<=aroundTile.getX()+maxDist; i++) {
                for (int j=aroundTile.getY()-maxDist; j<=aroundTile.getY()+maxDist; j++) {
                if (game.canBuildHere(new TilePosition(i,j), buildingType, builder, false)) {
                                // units that are blocking the tile
                    boolean unitsInWay = false;
                    for (Unit u : game.getAllUnits())
                    {
                        if (u.getID() == builder.getID()) continue;
                        if ((Math.abs(u.getTilePosition().getX()-i) < 4) && (Math.abs(u.getTilePosition().getY()-j) < 4)) unitsInWay = true;
                        }
                        if (!unitsInWay) {
                          return new TilePosition(i, j);
                        }
                    }
                }
            }
            maxDist += 2;
        }
        return ret;
    }
}