package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SiegeWarSiegeStartEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Nation attackingNation;
	private Town defendingTown;

	public SiegeWarSiegeStartEvent(Nation attackingNation, Town defendingTown) {
		this.attackingNation = attackingNation;
		this.defendingTown = defendingTown;
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
