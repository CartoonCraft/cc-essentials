package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.plugin.CCEssentials;

public class ListCommand {
	
	CCEssentials plugin;
	public ListCommand(CCEssentials plugin2, CommandSender sender) {
		plugin = plugin2;
		if(!plugin.isUsingPermissions() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.list"))) {
			sender.sendMessage(ChatColor.GRAY+"Online players: "+ChatColor.RED+Bukkit.getOnlinePlayers().size());
			String message = ""+ChatColor.GRAY;
			int i = 0;
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(i != 0) {
					message += ChatColor.GRAY+", ";
				}
				if(p.isOp())
					message += ChatColor.RED;
				message += p.getName();
				i++;
			}
			sender.sendMessage(message);
		}
	}

}