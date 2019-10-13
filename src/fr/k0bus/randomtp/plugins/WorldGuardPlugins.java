package fr.k0bus.randomtp.plugins;

import java.util.List;

import org.bukkit.Location;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;

public class WorldGuardPlugins {

	public boolean isClaimed(Location location)
	{
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager manager = container.get(BukkitAdapter.adapt(location.getWorld()));
		BlockVector3 vector = BlockVector3.at(location.getX(), location.getY(), location.getZ());
		List<String> regions = manager.getApplicableRegionsIDs(vector);
		return !regions.isEmpty();
		
	}
	
	
}
