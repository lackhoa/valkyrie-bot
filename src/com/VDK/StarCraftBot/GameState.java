package com.VDK.StarCraftBot;

import bwapi.*;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * This holding the game state, this exists because it would be
 * a problem to just passing these value repeatedly
 */
class GameState {
	private ArrayList<IGameObserver> subscribers;
    public GameState() {
        this.subscribers = new ArrayList<>();
    }
    /**
     * Tell the subscribers about the new game state
     * @param game the game
     * @param player the player
     */
    public void notify(Game game, Player player)
    {
        for(IGameObserver manager : subscribers)
        {
           manager.update(game, player);
        }
    }
    public void addSub(IGameObserver manager)
    {
    	this.subscribers.add(manager);
    }
}
