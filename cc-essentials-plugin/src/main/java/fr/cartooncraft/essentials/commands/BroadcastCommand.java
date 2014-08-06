package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class BroadcastCommand extends CCCommand {
	
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
			message.replace('&', ChatColor.COLOR_CHAR);
			Bukkit.broadcastMessage(message);
		}
		else {
			sender.sendMessage(noPermission);
		}
	}

}