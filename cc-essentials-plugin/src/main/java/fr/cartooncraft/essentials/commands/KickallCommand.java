package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class KickallCommand extends CCCommand {
	
	public KickallCommand(CommandSender sender, String[] args) {
		if(sender.isOp()) {
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
				if(!p.isOp())
					p.kickPlayer(kickReason);
			}
			String chatMessage = ChatColor.GRAY+"All the players (excepted the "+ChatColor.RED+"OPs"+ChatColor.GRAY+") have been kicked";
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