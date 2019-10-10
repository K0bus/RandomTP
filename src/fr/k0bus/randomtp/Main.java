package fr.k0bus.randomtp;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static String tag;
	public static FileConfiguration config;
	
	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		Main.config = this.getConfig();
		Main.tag = ChatColor.translateAlternateColorCodes('&', Main.config.getString("tag") + "&r ");
		this.getCommand("randomtp").setExecutor(new CommandRandomTP());
	}
	@Override
	public void onDisable()
	{
		this.saveConfig();
	}
	
}
