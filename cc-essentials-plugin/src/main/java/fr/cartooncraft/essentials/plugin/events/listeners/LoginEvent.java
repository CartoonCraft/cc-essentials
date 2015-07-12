package fr.cartooncraft.essentials.plugin.events.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class LoginEvent implements Listener {

	CCEssentials plugin;
	
	public LoginEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		plugin.getLogger().info("Creating file "+plugin.getServer().getWorldContainer().getAbsolutePath()+"/plugins/CC-Essentials/"+CCEssentialsLibrary.getConfigFileName(e.getPlayer())+"...");
		File file = new File(plugin.getServer().getWorldContainer().getAbsolutePath()+"/plugins/CC-Essentials/"+CCEssentialsLibrary.getConfigFileName(e.getPlayer()));
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ConfigManager.load(plugin, CCEssentialsLibrary.getConfigFileName(e.getPlayer()));
	}
}
