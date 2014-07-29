package fr.cartooncraft.essentials.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.CCEssentials;

public class LoginEvent implements Listener {

	CCEssentials plugin;
	
	public LoginEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		ConfigManager.load(plugin, e.getPlayer().getName()+".yml");
	}
}
