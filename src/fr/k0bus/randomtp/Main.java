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
		ConfigManager.onServerEnable();
		Main.tag = ChatColor.translateAlternateColorCodes('&', MainConfig.tag + "&r ");
		Main.plugin = this;
		this.getCommand("randomtp").setExecutor(new CommandRandomTP());
		this.getCommand("rtp_debug").setExecutor(new CommandDebug());
		if(Main.plugin.getServer().getPluginManager().getPlugin("Lands")!=null)
		{
			landsaddon = new LandsAddonsPlugins(Main.plugin);
		}
	}
	@Override
	public void onDisable()
	{
		
	}
	
}
