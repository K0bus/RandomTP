package fr.k0bus.randomtp.commands;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.k0bus.randomtp.ExceptionClass;
import fr.k0bus.randomtp.Main;
import fr.k0bus.randomtp.checker.CheckerBlock;
import fr.k0bus.randomtp.checker.CheckerPlugins;

public class CommandRandomTP implements CommandExecutor{

		CheckerBlock cb = new CheckerBlock();
		CheckerPlugins cp = new CheckerPlugins();
		
		public HashMap<UUID, Long> cdtime = new HashMap<UUID, Long>();
	
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
				if(cdtime.get(player.getUniqueId()) == null || (cdtime.get(player.getUniqueId())+ cooldownTP*1000) <= System.currentTimeMillis() || player.hasPermission("randomtp.bypass.cooldown"))
				{
					if(cdtime.get(player.getUniqueId()) != null)
					{
						cdtime.remove(player.getUniqueId());
					}
					
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
								Location toTeleport = cb.getSafeLocation(randomLocation);
								if(toTeleport != null) {
									if(!cp.isClaimed(toTeleport))
									{

										player.sendMessage(Main.tag + "Position trouve : X: " + toTeleport.getX() + " - Z: " + toTeleport.getZ() + " - Y: " + (toTeleport.getY() + 1));
										if(player.hasPermission("randomtp.bypass.timer"))
										{
								    		player.teleport(toTeleport.add(0.5, 1, 0.5));
								    		player.sendMessage(Main.tag + ChatColor.DARK_GREEN + "Téléportation réussis !");
								    		if(!player.hasPermission("randomtp.bypass.cooldown"))
								    		{
								    			cdtime.put(player.getUniqueId(), (long) (System.currentTimeMillis()));
								    		}
										}
										else
										{
											player.sendMessage(Main.tag + "Téléportation dans " + delayTP + " secondes !");
											Location l = player.getLocation();
											int lX = l.getBlockX();
											int lZ = l.getBlockZ();
											int lY = l.getBlockY();
											double h = player.getHealth();
											PlayerInventory inv = player.getInventory();
											
											Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin , new Runnable() {
											    public void run()
											    {
											    	if(lX == player.getLocation().getBlockX() && lZ == player.getLocation().getBlockZ() && lY == player.getLocation().getBlockY() && h == player.getHealth() && inv == player.getInventory())
											    	{
											    		player.teleport(toTeleport.add(0.5, 1, 0.5));
											    		player.sendMessage(Main.tag + ChatColor.DARK_GREEN + "Téléportation réussis !");
											    		if(!player.hasPermission("randomtp.bypass.cooldown"))
											    		{
											    			cdtime.put(player.getUniqueId(), (long) (System.currentTimeMillis()));
											    		}
											    	}
											    	else
											    	{
											    		player.sendMessage(Main.tag + ChatColor.DARK_RED + "Téléportation annulée !");
											    	}
											    }
											}, (long) (20*delayTP));
										}
										return true;
									}
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
				else
				{
					long time = (long) (((cdtime.get(player.getUniqueId())+ cooldownTP*1000) - System.currentTimeMillis()) / 1000);
					player.sendMessage(Main.tag + "Merci d'attendre encore " + time + " secondes !");
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