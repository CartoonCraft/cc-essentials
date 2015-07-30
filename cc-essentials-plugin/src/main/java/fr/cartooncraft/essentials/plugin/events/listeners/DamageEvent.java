package fr.cartooncraft.essentials.plugin.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class DamageEvent implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent ev) {
		if(ev.getEntity() instanceof Player) {
			if(new CCEssentialsPlayer((Player)ev.getEntity()).getGodmode())
				ev.setCancelled(true);
		}
	}
}
