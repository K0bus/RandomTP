package fr.k0bus.randomtp.checker;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

public class CheckerPlugins {
	Plugin worldguard;
	Plugin village_land;
	Plugin griefprevention;
	Plugin essentials;
	public void CheckerPlugin()
	{
		this.worldguard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		this.village_land = Bukkit.getServer().getPluginManager().getPlugin("Village");
		this.griefprevention = Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention");
		this.essentials = Bukkit.getServer().getPluginManager().getPlugin("Essentials");
	}
	public boolean isProtected(Block b)
	{
		boolean p = false;
		if(this.worldguard != null && p == false){
			p = this.checkWorldGuard();
		}
		if(this.village_land != null && p == false){
			p = this.checkVillageLand();
		}
		if(this.griefprevention != null && p == false){
			p = this.checkGriefPrevention();
		}
		if(this.essentials != null && p == false){
			p = this.checkEssentials();
		}
		return true;	
	}
	private boolean checkWorldGuard()
	{
		return true;
	}
	private boolean checkVillageLand()
	{
		return true;
	}
	private boolean checkGriefPrevention()
	{
		return true;
	}
	private boolean checkEssentials()
	{
		return true;
	}
}
