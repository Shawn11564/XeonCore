package dev.xeon.xeoncore.playerhandlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerHandlers {

	public Player getPlayerFromString(String target) {
		Player player;
		if (isRealPlayer(target)) {
			player = Bukkit.getPlayer(target);
		} else {
			player = null;
		}

		return player;
	}

	public boolean isRealPlayer(String name) {
		if (Bukkit.getPlayerExact(name) != null)
			return true;
		if (Bukkit.getPlayer(name) != null)
			return true;
		if (Bukkit.getOfflinePlayer(name) != null)
			return true;

		return false;
	}

}
