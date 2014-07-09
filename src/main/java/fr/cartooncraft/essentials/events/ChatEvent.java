package fr.cartooncraft.essentials.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.cartooncraft.essentials.CCCommand;

public class ChatEvent extends CCCommand implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent ev) {
		ev.setCancelled(true);
		Player p = ev.getPlayer();
		Bukkit.broadcastMessage(ChatColor.GRAY+"<"+ChatColor.RESET+getPlayerName(p)+ChatColor.GRAY+"> "+ChatColor.RESET+ev.getMessage());
	}
	

}
