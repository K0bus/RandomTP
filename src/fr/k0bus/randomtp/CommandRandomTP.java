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
					Location playerLocation = player.getLocation();
					World playerWorld = player.getWorld();
					if(distance >= minDistance && distance <= maxDistance)
					{
						player.sendMessage(Main.tag + "Recherche d'une position adequate !");
						boolean safePos = false;
						while(safePos == false)
						{
							double yRPos = 255.0;
							double[] rPos = this.generatePos(playerLocation, distance); // 0 - X // 1-Y
							//Check if world is safe
							while(safePos == false && yRPos > 50)
							{
								yRPos = yRPos - 1;
								Location rLocation = new Location(playerWorld, rPos[0], yRPos, rPos[1]);
								Block rBlock = playerWorld.getBlockAt(rLocation);
								if(rBlock.getBlockData().getMaterial() != Material.AIR)
								{
									if(!rBlock.isEmpty() && !rBlock.isLiquid() && !rBlock.isPassable())
									{
										safePos = true;
										player.teleport(new Location(playerWorld, rPos[0], yRPos +1 , rPos[1]));
										player.sendMessage(Main.tag + "Position trouve : X: " + rPos[0] + " - Z: " + rPos[1] + " - Y: " + yRPos);
									}
									else
									{
										yRPos = 0;
									}
								}
							}
						}
						
						
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
					player.sendMessage(Main.tag + "Merci de renseigner  un distance maximum !");
					player.sendMessage(Main.tag + "/randomtp <distance>");
					player.sendMessage(Main.tag + "Distance : " + minDistance + " - " + maxDistance);
				}
			}
			else
			{
				
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
