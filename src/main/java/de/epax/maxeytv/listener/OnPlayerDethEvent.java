package de.epax.maxeytv.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDethEvent implements Listener {

    public void onPlayerDeth(PlayerDeathEvent e) {
        Player p = e.getPlayer();
        e.setDeathMessage("respawn");
        World w = Bukkit.getWorld("voidworld");
        Location loc = new Location(w,10,100,10);
        if ( e.getShowDeathMessages()==false ) {
            p.teleport(loc);
        }
    }
}
