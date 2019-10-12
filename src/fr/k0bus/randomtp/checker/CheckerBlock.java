package fr.k0bus.randomtp.checker;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class CheckerBlock {
	public Location getSafeLocation(Location location)
	{
		Block b = location.getWorld().getHighestBlockAt(location);
		if(!b.isEmpty() && !b.isLiquid())
		{
				return b.getLocation();
		}
		return null;
	}
}
