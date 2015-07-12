package fr.cartooncraft.essentials.plugin.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class LeaveEvent implements Listener {

	CCEssentials plugin;
	
	public LeaveEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		ConfigManager.save(plugin, CCEssentialsLibrary.getConfigFileName(e.getPlayer()));
		ConfigManager.configs.remove(CCEssentialsLibrary.getConfigFileName(e.getPlayer()));
	}
}