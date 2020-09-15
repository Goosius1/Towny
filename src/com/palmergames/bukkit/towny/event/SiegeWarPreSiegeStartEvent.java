package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SiegeWarPreSiegeStartEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private Nation attackingNation;
	private Town defendingTown;
	boolean cancelled;
	
	public SiegeWarPreSiegeStartEvent(Player player, Nation attackingNation, Town defendingTown) {
		this.player = player;
		this.attackingNation = attackingNation;
		this.defendingTown = defendingTown;
	}
	
	public Player getPlayer() {
		return player;
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

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
