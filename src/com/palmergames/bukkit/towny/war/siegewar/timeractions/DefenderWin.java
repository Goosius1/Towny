package com.palmergames.bukkit.towny.war.siegewar.timeractions;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.event.SiegeWarSiegeEndEvent;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarMoneyUtil;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarSiegeCompletionUtil;
import com.palmergames.bukkit.towny.war.siegewar.objects.Siege;
import com.palmergames.bukkit.towny.war.siegewar.enums.SiegeStatus;
import org.bukkit.Bukkit;

/**
 * This class is responsible for processing siege defender wins
 *
 * @author Goosius
 */
public class DefenderWin
{
	/**
	 * This method triggers siege values to be updated for a defender win
	 *
	 * @param siege the siege
	 * @param winnerTown the winning town
	 */
    public static void defenderWin(Siege siege, Town winnerTown) {
        SiegeWarSiegeCompletionUtil.updateSiegeValuesToComplete(siege, SiegeStatus.DEFENDER_WIN);

		TownyMessaging.sendGlobalMessage(String.format(
			TownySettings.getLangString("msg_siege_war_defender_win"),
			winnerTown.getFormattedName()));

		SiegeWarMoneyUtil.giveWarChestToDefendingTown(siege);
		SiegeWarSiegeEndEvent siegeEndEvent = new SiegeWarSiegeEndEvent(siege.getAttackingNation(), siege.getDefendingTown(),
			SiegeStatus.DEFENDER_WIN);
		Bukkit.getPluginManager().callEvent(siegeEndEvent);
    }

}
