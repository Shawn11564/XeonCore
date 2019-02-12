package dev.xeon.xeoncore;

import org.bukkit.plugin.java.JavaPlugin;

public final class XeonCore extends JavaPlugin {

	private static XeonCore instance;

	@Override
	public void onEnable() {
		instance = this;

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static XeonCore getInstance() {
		return instance;
	}

}
