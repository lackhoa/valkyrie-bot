package com.VDK.StarCraftBot;

import bwapi.*;
import java.util.*;

/**
 * The list of things in the game
 *
 */
public class BroodWarList {
    /**
     * All of the units of the game
     */
    public static final ArrayList<UnitType> unitList;
	static
	{
		unitList = new ArrayList<>();
		unitList.add(UnitType.Terran_Battlecruiser);
		unitList.add(UnitType.Terran_Dropship);
		unitList.add(UnitType.Terran_Firebat);
		unitList.add(UnitType.Terran_Ghost);
		unitList.add(UnitType.Terran_Goliath);
		unitList.add(UnitType.Terran_Marine);
		unitList.add(UnitType.Terran_Medic);
		unitList.add(UnitType.Terran_Nuclear_Missile);
		unitList.add(UnitType.Terran_Science_Vessel);
		unitList.add(UnitType.Terran_SCV);
		unitList.add(UnitType.Terran_Siege_Tank_Tank_Mode);
		unitList.add(UnitType.Terran_Valkyrie);
		unitList.add(UnitType.Terran_Vulture);
		unitList.add(UnitType.Terran_Wraith);
		unitList.add(UnitType.Terran_Academy);
		unitList.add(UnitType.Terran_Armory);
		unitList.add(UnitType.Terran_Barracks);
		unitList.add(UnitType.Terran_Bunker);
		unitList.add(UnitType.Terran_Command_Center);
		unitList.add(UnitType.Terran_Engineering_Bay);
		unitList.add(UnitType.Terran_Factory);
		unitList.add(UnitType.Terran_Missile_Turret);
		unitList.add(UnitType.Terran_Refinery);
		unitList.add(UnitType.Terran_Science_Facility);
		unitList.add(UnitType.Terran_Starport);
		unitList.add(UnitType.Terran_Supply_Depot);
		unitList.add(UnitType.Terran_Comsat_Station);
		unitList.add(UnitType.Terran_Control_Tower);
		unitList.add(UnitType.Terran_Covert_Ops);
		unitList.add(UnitType.Terran_Machine_Shop);
		unitList.add(UnitType.Terran_Nuclear_Silo);
		unitList.add(UnitType.Terran_Physics_Lab);
	}
}