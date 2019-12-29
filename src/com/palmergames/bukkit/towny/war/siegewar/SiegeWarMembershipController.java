package com.palmergames.bukkit.towny.war.siegewar;

import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarPointsUtil;

/**
 * This class intercepts 'remove' requests, where a resident is removed from a town,
 * or a town is removed from a nation.
 *
 * The class evaluates the requests and determines if any siege updates are needed.
 * 
 * @author Goosius
 */
public class SiegeWarMembershipController {

	/**
	 * Evaluates a town removing a resident
	 * 
	 * If the resident is a guard in a siegezone which involves the town, siege points are deducted
	 * If the resident is a soldier in a siegezone which involved the nation (if any), siege points are deducted.
	 * 
	 * @param resident The resident who is being removed
	 *  
	 */
	public static void evaluateTownRemoveResident(Resident resident) {
		SiegeWarPointsUtil.evaluateSiegePenaltyPoints(
			resident,
			TownySettings.getLangString("msg_siege_war_resident_leave_town"),
			true,
			true);
	}
	
	/**
	 * Evaluates a nation removing a town
	 *
	 * If any town residents are soldiers in siegezones which involve the nation, siege points are deducted
	 *
	 * @param town The town which is being removed
	 *
	 */
	public static void evaluateNationRemoveTown(Town town) {

		for (Resident resident : town.getResidents()) {
			SiegeWarPointsUtil.evaluateSiegePenaltyPoints(
				resident,
				TownySettings.getLangString("msg_siege_war_town_leave_nation"),
				false,
				true);
		}
	}
	
}