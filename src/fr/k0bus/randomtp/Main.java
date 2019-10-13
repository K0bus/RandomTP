package fr.k0bus.randomtp;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.randomtp.commands.CommandRandomTP;

public class Main extends JavaPlugin {

	public static String tag;
	public static FileConfiguration config;
	public static Plugin plugin;
	
	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		Main.config = this.getConfig();
		Main.tag = ChatColor.translateAlternateColorCodes('&', Main.config.getString("tag") + "&r ");
		Main.plugin = this;
		this.getCommand("randomtp").setExecutor(new CommandRandomTP());
		//this.getCommand("rtp_debug").setExecutor(new CommandDebug());
		this.setDefaultConfig();
	}
	@Override
	public void onDisable()
	{
		this.saveConfig();
	}
	
	private void setDefaultConfig()
	{
		Main.config.addDefault("tag", "&r[&cRandomTP&r]");
		Main.config.addDefault("distance.min", 200);
		Main.config.addDefault("distance.max", 1000);
		Main.config.addDefault("max-try", 50);
		Main.config.addDefault("delay.teleport", 5);
		Main.config.addDefault("delay.cooldown", 360);
		Main.config.options().copyDefaults(true);
		this.saveConfig();
	}
	
}
