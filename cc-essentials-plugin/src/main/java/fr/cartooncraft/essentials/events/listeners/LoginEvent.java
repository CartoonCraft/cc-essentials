package fr.cartooncraft.essentials.events.listeners;

import java.io.File;
import java.io.IOException;

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
		File file = new File("plugins/"+e.getPlayer().getName()+".yml");
		if(!file.isFile())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		ConfigManager.load(plugin, e.getPlayer().getName()+".yml");
	}
}
