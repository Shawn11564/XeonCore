package dev.xeon.xeoncore.utils;

import dev.xeon.xeoncore.XeonCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Common {

	private static XeonCore instance = XeonCore.getInstance();

	public static void tell(CommandSender toWhom, String... messages) {
		for (final String message : messages)
			tell(toWhom, message);
	}

	public static void tell(CommandSender toWhom, String message) {
		toWhom.sendMessage(colorize(message));
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static boolean isValidPlayer(String player) {
		if (Bukkit.getPlayerExact(player) != null)
			return true;
		if (Bukkit.getPlayer(player) != null)
			return true;
		if (Bukkit.getOfflinePlayer(player) != null)
			return true;
		return false;
	}

	public static Date convertToDate(String date) {
		long current = System.currentTimeMillis();
		long total = 0L;

		String str = date;
		String[] part = str.split("(?<=\\D)(?=\\d)");
		String timeScale = part[0];
		String time = part[1];

		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.second")))
			total += TimeUnit.SECONDS.toMillis(Long.parseLong(time)) + current;
		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.minute")))
			total += TimeUnit.MINUTES.toMillis(Long.parseLong(time)) + current;
		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.hour")))
			total += TimeUnit.HOURS.toMillis(Long.parseLong(time)) + current;
		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.day")))
			total += TimeUnit.DAYS.toMillis(Long.parseLong(time)) + current;
		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.week")))
			total += (TimeUnit.DAYS.toMillis(Long.parseLong(time)) * 7) + current;
		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.month")))
			total += (TimeUnit.DAYS.toMillis(Long.parseLong(time)) * 31) + current;
		if (timeScale.equalsIgnoreCase(instance.getConfig().getString("time-scale-aliases.year")))
			total += (TimeUnit.DAYS.toMillis(Long.parseLong(time)) * 365) + current;

		return new Date(total);
	}

}
