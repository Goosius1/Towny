package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.war.siegewar.enums.SiegeSide;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SiegeWarPlayerDeathRewardSiegePointsEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private Player deadPlayer;
	private SiegeSide siegeSide;
	private boolean cancelled;

	public SiegeWarPlayerDeathRewardSiegePointsEvent(Player deadPlayer, SiegeSide siegeSide) {
		this.deadPlayer = deadPlayer;
		this.siegeSide = siegeSide;
	}

	public SiegeSide getSiegeSide() {
		return siegeSide;
	}

	public Player getDeadPlayer() {
		return deadPlayer;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
