package com.palmergames.bukkit.towny.event;

import java.util.List;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NewDayEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	private final List<String> fallenTowns;
	private final List<String> fallenNations;
	private final double townUpkeepCollected;
	private final double nationUpkeepCollected;
	private final long time;
	private final List<Town> peacefulTowns;

	public NewDayEvent(final List<String> fallenTowns, final List<String> fallenNations, final double townUpkeepCollected, final double nationUpkeepCollected, final long time, List<Town> peacefulTowns) {
		super(!Bukkit.getServer().isPrimaryThread());
		this.fallenTowns = fallenTowns;
		this.fallenNations = fallenNations;
		this.townUpkeepCollected = townUpkeepCollected;
		this.nationUpkeepCollected = nationUpkeepCollected;
		this.time = time;
		this.peacefulTowns = peacefulTowns;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public List<Town> getPeacefulTowns() { 
		return peacefulTowns;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	public List<String> getFallenTowns() {
		return fallenTowns;
	}

	public List<String> getFallenNations() {
		return fallenNations;
	}

	public double getTownUpkeepCollected() {
		return townUpkeepCollected;
	}

	public double getNationUpkeepCollected() {
		return nationUpkeepCollected;
	}
	
	/**
	 * To turn this into something you could display use the following:
	 * 
	 * timeFormat = new SimpleDateFormat("MMMMM dd '@' HH:mm")
	 * timeFormat.format(event.getTime())
	 * 
	 * @return time 
	 */
	public long getTime() {
		return time;
	}

}
