package fr.cartooncraft.essentials.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.CCEssentials;

public class LeaveEvent implements Listener {

	CCEssentials plugin;
	
	public LeaveEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		ConfigManager.save(plugin, e.getPlayer().getName()+".yml");
		ConfigManager.configs.remove(e.getPlayer().getName()+".yml");
	}
}