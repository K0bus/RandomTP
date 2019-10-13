package fr.k0bus.randomtp.checker;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

public class CheckerPlugins {
	Plugin worldguard;
	Plugin village_land;
	Plugin griefprevention;
	Plugin essentials;
	Plugin essentialsProtect;
	Plugin essentialsAntiBuild;
	
	public void CheckerPlugin()
	{
		this.worldguard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard"); // https://dev.bukkit.org/projects/worldguard
		this.village_land = Bukkit.getServer().getPluginManager().getPlugin("Villages"); // https://www.spigotmc.org/resources/villages-a-unique-land-claim-plugin-reborn.67871/
		this.griefprevention = Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention"); // https://dev.bukkit.org/projects/grief-prevention
		this.essentials = Bukkit.getServer().getPluginManager().getPlugin("Essentials"); // https://www.spigotmc.org/resources/essentialsx.9089/
		this.essentialsProtect = Bukkit.getServer().getPluginManager().getPlugin("EssentialsAntiBuild"); // https://www.spigotmc.org/resources/essentialsx.9089/
		this.essentialsAntiBuild = Bukkit.getServer().getPluginManager().getPlugin("EssentialsProtect"); // https://www.spigotmc.org/resources/essentialsx.9089/
	}
	public boolean isProtected(Block b)
	{
		boolean p = false;
		if(this.worldguard != null && p == false){
			p = this.checkWorldGuard(b);
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
		if(this.essentialsProtect != null && p == false){
			p = this.checkEssentialsProtect();
		}
		if(this.essentialsAntiBuild != null && p == false){
			p = this.checkEssentialsAntiBuild();
		}
		return true;	
	}
	private boolean checkWorldGuard(Block b)
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
	private boolean checkEssentialsProtect()
	{
		return true;
	}
	private boolean checkEssentialsAntiBuild()
	{
		return true;
	}
}
