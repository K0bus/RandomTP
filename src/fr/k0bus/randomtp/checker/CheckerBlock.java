package fr.k0bus.randomtp.checker;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.k0bus.randomtp.Main;
import fr.k0bus.randomtp.commands.CommandDebug;

public class CheckerBlock {
	
	CheckerPlugins cp = new CheckerPlugins();

	public Location getSafeLocation(Location location)
	{
		Block b = location.getWorld().getHighestBlockAt(location);
		if(!b.isEmpty() && !b.isLiquid())
		{
				return b.getLocation();
		}
		return null;
	}

	public boolean onCommand(CommandDebug commandDebug, CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player && args.length > 0)
		{
			Player player = (Player) sender;
			if(player.hasPermission("randomtp.debug"))
			{
				if(args[0].contains("plc"))
				{
					player.sendMessage(Main.tag + ChatColor.LIGHT_PURPLE + "Vérification des claims...");
					if(cp.isClaimed(player.getLocation()))
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
