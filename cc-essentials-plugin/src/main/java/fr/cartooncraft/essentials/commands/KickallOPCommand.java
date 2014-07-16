package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class KickallOPCommand extends CCCommand {
	
	CCEssentials plugin;
	
	public KickallOPCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.kickallop"))) {
			Player[] players = Bukkit.getOnlinePlayers();
			
			// Getting reason
			
			String kickReason = "";
			boolean noKickMessage = false;
			for(String arg : args)
				kickReason += arg+" ";
			
			// Remove last space - copy-paste from Stackoverflow :D
			if (kickReason.length() > 0 && kickReason.charAt(kickReason.length()-1)==' ') {
				kickReason = kickReason.substring(0, kickReason.length()-1);
			}
			
			// ##############
			
			if(kickReason == "")
				noKickMessage = true;
				kickReason = "Kicked by "+sender.getName();
			for(Player p : players) {
				if(isPlayer(sender))
					if(!areSamePlayers((Player)sender, p))
						p.kickPlayer(kickReason);
			}
			String chatMessage = ChatColor.GRAY+"All the players have been kicked";
			if(noKickMessage)
				chatMessage += ".";
			else
				chatMessage += " for the following reason: "+'"'+ChatColor.WHITE+kickReason+ChatColor.GRAY+'"'+".";
			Bukkit.broadcastMessage(chatMessage);
		}
		else {
			sender.sendMessage(noPermission);
		}
	}

}