package fr.k0bus.randomtp.plugins;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import me.angeschossen.lands.api.landsaddons.LandsAddon;

public class LandsAddonsPlugins {

    private final LandsAddon landsAddon;
    private final String key;

    public LandsAddonsPlugins(Plugin yourPlugin) {

        /*
        Initialize LandsAddon
        Set isPulic to false, if you want
        to prevent that other developers can
        access your addon.
         */
        landsAddon = new LandsAddon(yourPlugin, false);

        /*
        Initialize the addon.
        You need to key to disable your landsAddon, if you ever want to.
         */
        key = landsAddon.initialize();
    }
    public boolean isClaimed(Location location)
    {
    	return this.landsAddon.isClaimed(location);
    }

    /**
     * Disable your landsAddon.
     * For example, if plugin disables.
     */
    public void disableLandsAddon() {

        /*
        Disable landsAddon if you want.
        You need the received key.
         */
        landsAddon.disable(key);
    }
    
}
