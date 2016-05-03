package com.VDK.StarCraftBot;

import bwapi.*;

/**
 * Controls the army movements
 * @author Administrator
 */
public class ArmyManager implements IGameObserver
{
	private Game game;
	private Player self;
	private enum State {
    AUTO ,ATTACK, DEFENSE, GROUPUP
	}
	private State state;

    /**
     * Update the game state and the player's information
     * @param game The game
     * @param self The player
     */
        @Override
    public void update(Game game, Player self)
	{
		this.game = game;
		this.self = self;
	}

    /**
     * Changing states of the army
     * @param request "ATTACK", "DEFENSE", "GROUPUP", "AUTO"(Let the robot decides)
     */
    public void controlArmy(String request)
	{
		switch (state) {
			case ATTACK:
			break;
			case DEFENSE:
			break;
			case GROUPUP:
			break;
			case AUTO:
			break;
			default:
			break;
		}
	}
}