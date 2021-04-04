package com.palmergames.bukkit.towny.event.plot;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PrePlayerKeepInventoryInArenaPlotEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean isCancelled = false;
	private String cancelMessage = "Sorry this event was cancelled";

	@Override
	public HandlerList getHandlers() {

		return handlers;
	}

	public static HandlerList getHandlerList() {

		return handlers;
	}

	public PrePlayerKeepInventoryInArenaPlotEvent() {
		super(!Bukkit.getServer().isPrimaryThread());
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		isCancelled = cancelled;
	}

	public String getCancelMessage() {
		return cancelMessage;
	}

	public void setCancelMessage(String message) {
		this.cancelMessage = message;
	}
}
