package fr.k0bus.randomtp;

import org.bukkit.ChatColor;

import fr.k0bus.randomtp.Main;

public class MainConfig {

	@SuppressWarnings("unused")
	private static Main plugin;
	
	public MainConfig(Main instance)
	{
		plugin = instance;
	}
	
	public static String tag;
	public static Boolean lands;
	public static Boolean worldguard;
	public static int minDistance;
	public static int maxDistance;
	public static int maxTry;
	public static int teleportDelay;
	public static int cooldownDelay;
	
	
	//Hint: If you want a reload command tie it to this script and it will refresh as well.
	public static void GetMainValues()
	{
		tag = ChatColor.translateAlternateColorCodes('&', ConfigManager.mianconfig.getString("tag") + "&r ");
		
		lands = ConfigManager.mianconfig.getBoolean("Lands");
		worldguard = ConfigManager.mianconfig.getBoolean("plugins.worldguard");
		
		minDistance = ConfigManager.mianconfig.getInt("distance.min");
		maxDistance = ConfigManager.mianconfig.getInt("distance.max");
		maxTry = ConfigManager.mianconfig.getInt("max-try");
		teleportDelay = ConfigManager.mianconfig.getInt("delay.teleport");
		cooldownDelay = ConfigManager.mianconfig.getInt("delay.cooldown");
	}
}
