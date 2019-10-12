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
		this.setDefaultConfig();
	}
	@Override
	public void onDisable()
	{
		this.saveConfig();
	}
	
	private void setDefaultConfig()
	{
		Main.config.addDefault("tag", "&r[&cRandomRP&r]");
		Main.config.addDefault("distance.min", 200);
		Main.config.addDefault("distance.max", 1000);
		Main.config.addDefault("max-try", 50);
		Main.config.addDefault("delay.teleport", 0);
		Main.config.addDefault("delay.cooldown", 0);
	}
	
}
