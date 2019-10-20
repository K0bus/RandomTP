package fr.k0bus.randomtp.checker;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player && args.length > 0)
		{
			Player player = (Player) sender;
			if(player.hasPermission("randomtp.debug"))
			{
				if(args[0].contains("plc"))
				{
					player.sendMessage(Main.tag + ChatColor.LIGHT_PURPLE + "Vérification des claims...");
					if(isClaimed(player.getLocation()))
					{
						player.sendMessage(Main.tag + ChatColor.DARK_RED + "Cette endroit est protégé par un claim !");
					}
					else
					{
						player.sendMessage(Main.tag + ChatColor.DARK_GREEN + "Cette endroit n'est pas protégé par un claim !");
					}
				}
			}
		}
		return true;
	}
}
