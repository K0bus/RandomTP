package fr.k0bus.randomtp;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
				if (args.length == 1 && ExceptionClass.isNumeric(args[0]))
				{
					double distance = Double.parseDouble(args[0]);
					if(distance >= minDistance && distance <= maxDistance)
					{
						player.sendMessage(Main.tag + "Recherche d'une position adequate !");
						for(int i = 0; i < 50; i++)
						{
							double[] rPos = this.generatePos(player.getLocation(), distance); // index 0 is X  |  index 1 is Y
							Block b = player.getWorld().getHighestBlockAt((int) rPos[0], (int) rPos[1]);
							if(!b.isLiquid()) {
								player.teleport(b.getLocation().add(0, 1, 0));
								player.sendMessage(Main.tag + "Position trouve : X: " + rPos[0] + " - Z: " + rPos[1] + " - Y: " + (b.getLocation().getY() + 1));
								return true;
							}
						}
						player.sendMessage(Main.tag + "Max amounts of tries exceeded! Please try again!"); // TODO change to french - I dont speak french...
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

}
