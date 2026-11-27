package com.palmergames.bukkit.towny.utils;

import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class MapUtil {

	private static final Pattern HEX_COLOUR_PATTERN = Pattern.compile("^[0-9a-f]{6}$");

	/**
	 * Parse a player-entered hex colour, eg. "#FF8800" or "ff8800".
	 *
	 * @param input the raw input
	 * @return the colour as a 6 character lowercase hex code without a leading #,
	 *         or null if the input is not a valid hex colour.
	 */
	@Nullable
	public static String parseHexColour(String input) {
		if (input == null)
			return null;
		String hex = input.trim().toLowerCase(Locale.ROOT);
		if (hex.startsWith("#"))
			hex = hex.substring(1);
		return HEX_COLOUR_PATTERN.matcher(hex).matches() ? hex : null;
	}

	/**
	 * @return a random colour as a 6 character lowercase hex code without a leading #.
	 */
	public static String generateRandomColourAsHexCode() {
		return String.format("%06x", ThreadLocalRandom.current().nextInt(0x1000000));
	}
}
