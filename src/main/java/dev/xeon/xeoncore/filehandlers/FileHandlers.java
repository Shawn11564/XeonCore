package dev.xeon.xeoncore.filehandlers;

import dev.xeon.xeoncore.utils.Common;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileHandlers {

	public static void createCustomYML(String fileName, JavaPlugin plugin) {
		File newFile = new File(plugin.getDataFolder(),fileName + ".yml");
		if (doesFileExist(newFile))
			return;
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			Common.tell(plugin.getServer().getConsoleSender(),
					"&cError creating file: &b"
					+ fileName
					+ " &cError Log: " + Arrays.toString(e.getStackTrace()));
		}
	}

	public static boolean doesFileExist(File file) {
		return file.exists();
	}

	public static void copyResourceContentsToFile(String resourceFrom, String fileTo, JavaPlugin plugin) {
		File customConfigFile = new File(plugin.getDataFolder(), fileTo + ".yml");
		YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

		// Looks for the default file in the jar.
		InputStream defConfigStream = plugin.getResource(resourceFrom + ".yml");
		if(defConfigStream != null){
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			customConfig.setDefaults(defConfig);
		}
		try {
			customConfig.save(customConfigFile);
		} catch (IOException e) {
			Common.tell(plugin.getServer().getConsoleSender(),
					"&cError copying resource contents to file: &b"
							+ fileTo
							+ " &cError Log: " + Arrays.toString(e.getStackTrace()));
		}
	}

}
