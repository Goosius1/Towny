package com.palmergames.bukkit.towny.war.siegewar.timeractions;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.event.SiegeWarSiegeEndEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarMoneyUtil;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarSiegeCompletionUtil;
import com.palmergames.bukkit.towny.war.siegewar.objects.Siege;
import com.palmergames.bukkit.towny.war.siegewar.enums.SiegeStatus;
import org.bukkit.Bukkit;

/**
 * This class is responsible for processing siege attacker wins
 *
 * @author Goosius
 */
public class AttackerWin {

	/**
	 * This method triggers siege values to be updated for an attacker win
	 * 
	 * @param siege the siege
	 * @param winnerNation the winning nation
	 */
	public static void attackerWin(Siege siege, Nation winnerNation) {
        SiegeWarSiegeCompletionUtil.updateSiegeValuesToComplete(siege, SiegeStatus.ATTACKER_WIN);

		TownyMessaging.sendGlobalMessage(String.format(
			TownySettings.getLangString("msg_siege_war_attacker_win"),
			winnerNation.getFormattedName(),
			siege.getDefendingTown().getFormattedName()
		));

		SiegeWarMoneyUtil.giveWarChestToAttackingNation(siege);
		SiegeWarSiegeEndEvent siegeEndEvent = new SiegeWarSiegeEndEvent(siege.getAttackingNation(), siege.getDefendingTown(),
			SiegeStatus.ATTACKER_WIN);
		Bukkit.getPluginManager().callEvent(siegeEndEvent);
    }
}
