package com.VDK.StarCraftBot;

import java.util.*;
import bwapi.*;

/**
 * This class exists because I want to store all the commands in one place for convenience
 */
class CommandList
{
    static final HashMap<String, UnitType> buildCommands;
	static
	{
		buildCommands = new HashMap<>();
		buildCommands.put("b.a", UnitType.Terran_Academy);
		buildCommands.put("b.am", UnitType.Terran_Armory);
		buildCommands.put("b.ba", UnitType.Terran_Barracks);
		buildCommands.put("b.bu", UnitType.Terran_Bunker);
		buildCommands.put("b.cc", UnitType.Terran_Command_Center);
		buildCommands.put("b.eb", UnitType.Terran_Engineering_Bay);
		buildCommands.put("b.f", UnitType.Terran_Factory);
		buildCommands.put("b.ms", UnitType.Terran_Missile_Turret);
		buildCommands.put("b.r", UnitType.Terran_Refinery);
		buildCommands.put("b.sf", UnitType.Terran_Science_Facility);
		buildCommands.put("b.sp", UnitType.Terran_Starport);
		buildCommands.put("b.sd", UnitType.Terran_Supply_Depot);
	}
	static final Map<String, UnitType> buildAddonCommands;
	static
	{
		buildAddonCommands = new HashMap<>();
		buildAddonCommands.put("bo.cs", UnitType.Terran_Comsat_Station);
		buildAddonCommands.put("bo.ct", UnitType.Terran_Control_Tower);
		buildAddonCommands.put("bo.co", UnitType.Terran_Covert_Ops);
		buildCommands.put("bo.ms", UnitType.Terran_Machine_Shop);
		buildAddonCommands.put("bo.ns", UnitType.Terran_Nuclear_Silo);
		buildAddonCommands.put("bo.pl", UnitType.	Terran_Physics_Lab);

	}
	static final Map<String, UnitType> trainCommands;
	static
	{
		trainCommands = new HashMap<>();
		trainCommands.put("t.bc", UnitType.Terran_Battlecruiser);
		trainCommands.put("t.ds", UnitType.Terran_Dropship);
		trainCommands.put("t.fb", UnitType.Terran_Firebat);
		trainCommands.put("t.gh", UnitType.Terran_Ghost);
		trainCommands.put("t.go", UnitType.Terran_Goliath);
		trainCommands.put("t.ma", UnitType.Terran_Marine);
		trainCommands.put("t.me", UnitType.Terran_Medic);
		trainCommands.put("t.nu", UnitType.Terran_Nuclear_Missile);
		trainCommands.put("t.sv", UnitType.Terran_Science_Vessel);
		trainCommands.put("t.s", UnitType.Terran_SCV);
		trainCommands.put("t.st", UnitType.Terran_Siege_Tank_Tank_Mode);
		trainCommands.put("t.va", UnitType.Terran_Valkyrie);
		trainCommands.put("t.vu", UnitType.Terran_Vulture);
		trainCommands.put("t.w", UnitType.Terran_Wraith);
	}
	static final Map<String, String> armyCommands;
        static
	{
		armyCommands = new HashMap<>();
		armyCommands.put("a.a", "attack");
		armyCommands.put("a.h", "hold");
		armyCommands.put("a.r", "retreat");
	}

	static final ArrayList<String> commonCommands;
		static
	{
		commonCommands = new ArrayList();
		commonCommands.add("mine");
	}
}