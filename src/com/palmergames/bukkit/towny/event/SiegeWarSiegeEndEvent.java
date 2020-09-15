package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.war.siegewar.enums.SiegeStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SiegeWarSiegeEndEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private SiegeStatus status;
	private Town defendingTown;
	private Nation attackingNation;

	public SiegeWarSiegeEndEvent(Nation attackingNation, Town defendingTown, SiegeStatus status) {
		this.status = status;
		this.attackingNation = attackingNation;
		this.defendingTown = defendingTown;
	}

	public SiegeStatus getStatus() {
		return status;
	}

	public Nation getAttackingNation() {
		return attackingNation;
	}

	public Town getDefendingTown() {
		return defendingTown;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	
}
