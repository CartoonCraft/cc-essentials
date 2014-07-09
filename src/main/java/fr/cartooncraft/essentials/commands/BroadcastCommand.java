package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.CCCommand;

public class BroadcastCommand extends CCCommand {
	
	public BroadcastCommand(CommandSender sender, String[] args) {
		if(sender.isOp()) {
			String message = ""+ChatColor.RED+ChatColor.BOLD;
			for(String arg : args) {
				message += arg+" ";
			}
			
			// Remove last space - copy-paste from Stackoverflow :D
			
			if (message.length() > 0 && message.charAt(message.length()-1)==' ') {
				message = message.substring(0, message.length()-1);
			}
			
			Bukkit.broadcastMessage(message);
		}
		else {
			sender.sendMessage(noPermission);
		}
	}

}