package fr.k0bus.randomtp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRandomTP implements CommandExecutor{

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String args[])
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				double minDistance = Main.config.getInt("distance.min");
				double maxDistance = Main.config.getInt("distance.max");
				double maxTry = Main.config.getInt("max-try");
				double delayTP = Main.config.getInt("delay.teleport");
				double cooldownTP = Main.config.getInt("delay.cooldown");
				if (args.length == 1 && ExceptionClass.isNumeric(args[0]))
				{
					double distance = Double.parseDouble(args[0]);
					if(distance >= minDistance && distance <= maxDistance)
					{
						player.sendMessage(Main.tag + "Recherche d'une position adequate !");
						for(int i = 0; i < maxTry; i++)
						{
							
							double[] rPos = this.generatePos(player.getLocation(), distance); // index 0 is X  |  index 1 is Y
							Location randomLocation = new Location(player.getWorld(), rPos[0], 0, rPos[1]);
							Location toTeleport = this.getSafeLocation(randomLocation);
							if(toTeleport != null) {
								player.sendMessage(Main.tag + "Position trouve : X: " + toTeleport.getX() + " - Z: " + toTeleport.getZ() + " - Y: " + (toTeleport.getY() + 1));
								player.sendMessage(Main.tag + "Téléportation dans " + delayTP + " secondes !");
								player.teleport(toTeleport.add(0.5, 1, 0.5));
								return true;
							}
						}
						player.sendMessage(Main.tag + "Aucune position trouve malgre " + maxTry + " essaies !");
					}
					else
					{
						player.sendMessage(Main.tag + "La distance renseigner n'est pas possible !");
						player.sendMessage(Main.tag + "/randomtp <distance>");
						player.sendMessage(Main.tag + "Distance : " + minDistance + " - " + maxDistance);
						
					}
				}
				else
				{
					player.sendMessage(Main.tag + "Merci de renseigner un distance maximum !");
					player.sendMessage(Main.tag + "/randomtp <distance>");
					player.sendMessage(Main.tag + "Distance : " + minDistance + " - " + maxDistance);
				}
			}
			return true;
		}
		
		private double[] generatePos(Location playerLocation, double distance)
		{
			double[] xBorder = {playerLocation.getBlockX() - distance, playerLocation.getBlockX() + distance};
			double[] zBorder = {playerLocation.getBlockZ() - distance, playerLocation.getBlockZ() + distance};
			
			Random rand = new Random();
			
			double xRPos = rand.nextInt((int) (Math.floor(xBorder[1]) - Math.floor(xBorder[0]))) + xBorder[0] + 0.5;
			double zRPos = rand.nextInt((int) (Math.floor(zBorder[1]) - Math.floor(zBorder[0]))) + zBorder[0] + 0.5;
			
			double[] pos = {xRPos, zRPos};
			return pos;
		}
		private Location getSafeLocation(Location location)
		{
			Block b = location.getWorld().getHighestBlockAt(location);
			List<Material> blacklist = new ArrayList<Material>();
			if(!b.isEmpty() && !b.isLiquid()) //Simple bukkit check
			{
				if(!blacklist.contains(b.getBlockData().getMaterial()))
				{
					return b.getLocation();
				}
			}
			return null;
		}

}
