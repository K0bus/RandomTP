package fr.k0bus.randomtp;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.randomtp.commands.CommandDebug;
import fr.k0bus.randomtp.commands.CommandRandomTP;
import fr.k0bus.randomtp.plugins.LandsAddonsPlugins;

public class Main extends JavaPlugin {

	public static String tag;
	public static FileConfiguration config;
	public static Plugin plugin;
	public static LandsAddonsPlugins landsaddon;
	
	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		Main.config = this.getConfig();
		Main.tag = ChatColor.translateAlternateColorCodes('&', Main.config.getString("tag") + "&r ");
		Main.plugin = this;
		this.getCommand("randomtp").setExecutor(new CommandRandomTP());
		this.getCommand("rtp_debug").setExecutor(new CommandDebug());
		this.setDefaultConfig();
		if(Main.plugin.getServer().getPluginManager().getPlugin("Lands")!=null)
		{
			landsaddon = new LandsAddonsPlugins(Main.plugin);
		}
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
		//Exception plugins
		Main.config.addDefault("plugins.lands", true);
		Main.config.addDefault("plugins.worldguard", true);
		Main.config.options().copyDefaults(true);
		this.saveConfig();
	}
	
}
