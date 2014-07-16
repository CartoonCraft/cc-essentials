package fr.cartooncraft.essentials.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.goblom.bukkitlibs.config.ConfigManager;

public class DamageEvent implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent ev) {
		if(ev.getEntity() instanceof Player) {
			Player p = (Player)ev.getEntity();
			boolean godmode = ConfigManager.get(p.getName()+".yml").getBoolean("godmode", false);
			if(godmode)
				ev.setCancelled(true);
		}
	}
}
