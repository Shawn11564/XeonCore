package dev.xeon.xeoncore;

import dev.xeon.xeoncore.filehandlers.FileHandlers;
import dev.xeon.xeoncore.iphandlers.IPHandlers;
import dev.xeon.xeoncore.playerhandlers.PlayerHandlers;
import dev.xeon.xeoncore.utils.Common;
import org.bukkit.plugin.java.JavaPlugin;

public final class XeonCore extends JavaPlugin {

	private static XeonCore instance;

	public IPHandlers ipHandlers;
	public PlayerHandlers playerHandlers;
	public FileHandlers fileHandlers;
	public Common common;

	@Override
	public void onEnable() {
		instance = this;
		ipHandlers = new IPHandlers();
		playerHandlers = new PlayerHandlers();
		fileHandlers = new FileHandlers();
		common = new Common();

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static XeonCore getInstance() {
		return instance;
	}

}
