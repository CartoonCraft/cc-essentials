package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class BroadcastCommand {
	
	CCEssentials plugin;
	
	
	public BroadcastCommand(CCEssentials p, CommandSender sender, String[] args) {
		plugin = p;
		if(sender.isOp() || (sender.hasPermission("cc-essentials.broadcast") && plugin.isUsingPermissions())) {
			String message = ""+ChatColor.RED+ChatColor.BOLD;
			for(String arg : args) {
				message += arg+" ";
			}
			
			// Remove last space - copy-paste from Stackoverflow :D
			
			if (message.length() > 0 && message.charAt(message.length()-1)==' ') {
				message = message.substring(0, message.length()-1);
			}
			message = message.replace('&', ChatColor.COLOR_CHAR);
			Bukkit.broadcastMessage(message);
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}

}