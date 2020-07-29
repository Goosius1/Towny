package com.palmergames.bukkit.towny.war.siegewar;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Coord;
import com.palmergames.bukkit.towny.war.siegewar.objects.Siege;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarBlockUtil;
import com.palmergames.bukkit.towny.war.siegewar.utils.SiegeWarDistanceUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * This class intercepts 'break block' events coming from the towny block listener class
 *
 * The class evaluates the event, and determines if it siege related.
 * For example, if somebody tries to destroy a siege attack banner, they will be prevented.
 * 
 * @author Goosius
 */
public class SiegeWarBreakBlockController {

	/**
	 * Evaluates a block break request.
	 * While a siege is in progress, nobody can destroy the siege banner or nearby blocks
	 * Also prevents destroying blocks below the banner control zone if active zone is enabled
	 *
	 * @param player The player breaking the block
	 * @param block The block about to be broken
	 * @param event The event object related to the block break    	
	 * @return true if subsequent perm checks for the event should be skipped
	 */
	public static boolean evaluateSiegeWarBreakBlockRequest(Player player, Block block, BlockBreakEvent event)  {
		try {
			if (SiegeWarBlockUtil.isBlockNearAnActiveSiegeBanner(block)) {
				event.setCancelled(true);
				TownyMessaging.sendErrorMsg(player, TownySettings.getLangString("msg_err_siege_war_cannot_destroy_siege_banner"));
				return true;
			}
				
			//Active zone: if enabled, prohibit breaking blocks below the banner control zone.
			if(TownySettings.isWarSiegeActiveZoneEnabled() && !TownyUniverse.getInstance().getDataSource().getWorld(block.getWorld().getName()).hasTownBlock(Coord.parseCoord(block))) {
				Siege nearestSiege = SiegeWarDistanceUtil.findNearestSiege(block);
				Location delta = block.getLocation().subtract(nearestSiege.getFlagLocation());
				if(-(delta.getY() + 1) > TownySettings.getBannerControlVerticalDistanceBlocks()) {
					//Below the maximum allowable height below the banner's resting block
					double distanceToNearestSiegeBannerHorizontal = Math.sqrt(Math.pow(delta.getX(),2) + Math.pow(delta.getZ(),2));
					if(distanceToNearestSiegeBannerHorizontal <= TownySettings.getBannerControlHorizontalDistanceBlocks()) {
						//Within the prohibited radius
						event.setCancelled(true);
						TownyMessaging.sendErrorMsg(player, String.format(
							TownySettings.getLangString("msg_war_siege_active_zone_destroy_forbidden"),
							TownySettings.getBannerControlHorizontalDistanceBlocks(),
							TownySettings.getBannerControlVerticalDistanceBlocks()));
						return true;
					}
				}
			}
			
			//Break request unaffected
			return false;
			
		} catch (NotRegisteredException e) {
			System.out.println("Problem evaluating siege block destroy request");
			e.printStackTrace();
			return false;
		}
	}
}
