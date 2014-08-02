package fr.cartooncraft.essentials.events.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class ChatEvent extends CCCommand implements Listener {
	
	CCEssentials plugin;
	
	public ChatEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent ev) {
		if(plugin.isChatEnabled()) {
			ev.setCancelled(true);
			Player p = ev.getPlayer();
			Bukkit.broadcastMessage(ChatColor.GRAY+"<"+ChatColor.RESET+getPlayerName(p)+ChatColor.GRAY+"> "+ChatColor.RESET+ev.getMessage());
		}
	}
	

}
