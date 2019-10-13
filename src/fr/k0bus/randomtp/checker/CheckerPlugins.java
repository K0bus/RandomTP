package fr.k0bus.randomtp.checker;

import org.bukkit.Location;

import fr.k0bus.randomtp.Main;
import fr.k0bus.randomtp.plugins.WorldGuardPlugins;

public class CheckerPlugins {
	
	public boolean isClaimed(Location location)
	{
		boolean claimed = false;
		if(Main.plugin.getServer().getPluginManager().getPlugin("Lands")!=null && !claimed && Main.config.getBoolean("plugins.lands"))
		{
			claimed = Main.landsaddon.isClaimed(location);
		}
		if(Main.plugin.getServer().getPluginManager().getPlugin("WorldGuard")!=null && !claimed && Main.config.getBoolean("plugins.worldguard"))
		{
			claimed = new WorldGuardPlugins().isClaimed(location);
		}
		return claimed;
	}
}
