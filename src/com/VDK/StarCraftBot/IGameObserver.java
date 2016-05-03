package com.VDK.StarCraftBot;

import bwapi.*;

/**
 * Classes that knows of the game state
 * @author Administrator
 */
public interface IGameObserver
{
    /**
     * Updating infromation about the game and the player
     * @param game the game
     * @param self the player
     */
	public void update(Game game, Player self);
}
