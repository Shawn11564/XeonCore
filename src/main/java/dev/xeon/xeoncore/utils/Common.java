package dev.xeon.xeoncore.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.xeon.xeoncore.XeonCore;

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

	@SuppressWarnings("deprecation")
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
	
	public static void sendMinimalizedMsg(String text, int limit, Player p) {
		String message = "";
		int check = 0;
		while(check != text.length()) {
			for(int i = 0; i < text.length(); i++) {
				message += text.charAt(i);
				check++;
				if(message.length() >= limit) {
					if(text.charAt(i) == ' ') {
						p.sendMessage(message);
						message = "";					
					}
					
					if(check == text.length()) {
						p.sendMessage(message);
					}
					
				} else {
					if(check == text.length()) {
						p.sendMessage(message);
					}
				}
			}	
		}
	}

	public static void addMinimalizedLore(String text, ArrayList<String> al, int limit) {
		String message = "";
		int check = 0;
		while(check != text.length()) {
			for(int i = 0; i < text.length(); i++) {
				message += text.charAt(i);
				check++;
				if(message.length() >= limit) {
					if(text.charAt(i) == ' ') {
						al.add(message);
						al.add("------");
						message = "";					
					}
					
					if(check == text.length()) {
						al.add(message);
						al.add("------");
					}
					
				} else {
					if(check == text.length()) {
						al.add(message);
						al.add("------");
					}
				}
			}	
		}
	}
	
}
