package fr.cartooncraft.essentials.plugin.events.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class ChatEvent implements Listener {
	
	CCEssentials plugin;
	
	public ChatEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent ev) {
		if(plugin.isChatEnabled()) {
			ev.setCancelled(true);
			Player p = ev.getPlayer();
			Bukkit.broadcastMessage(ChatColor.GRAY+"<"+ChatColor.RESET+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+"> "+ChatColor.RESET+ev.getMessage());
		}
	}
	

}
