package fr.k0bus.randomtp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.k0bus.randomtp.MainConfig;

import fr.k0bus.randomtp.Main;

public class ConfigManager
{
	
	public static File mainconfigfile;
	public static FileConfiguration mianconfig;

	private static Main plugin;
	
	public ConfigManager(Main instance)
	{
		plugin = instance;
	}
	
	public static void onServerEnable()
	{

		try 
		{
			fileGenerator();
		} catch (IOException e) 
		{
			return;
		}

		MainConfig.GetMainValues(); //Get base values from config
	}
	

	public static void reloadConfigs() 
	{

		try 
		{
			fileGenerator();
		} catch (IOException e) 
		{
			return;
		}

		MainConfig.GetMainValues(); //Get base values from config
	}
	
	public static void fileGenerator() throws IOException
	{
		saveDefaultMainConfig();
		mianconfig = YamlConfiguration.loadConfiguration(mainconfigfile);
	}
	
	//Check for config, if it is not there save the default
	private static void saveDefaultMainConfig()
	{
		if (mainconfigfile == null) 
		{
			mainconfigfile = new File(plugin.getDataFolder(), "config.yml");
		}
		if (!mainconfigfile.exists())
		{            
			plugin.saveResource("config.yml", true);
		}
	}
}


/*
CheckerPlugins.java
-
Boolean plugins.lands
Boolean plugins.worldguard
-

CommandRandomTP.java
-
Int distance.min
Int distance.max
Int max-try
Int delay.teleport
Int delay.cooldown
*/