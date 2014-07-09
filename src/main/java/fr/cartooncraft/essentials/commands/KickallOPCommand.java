package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class KickallOPCommand extends CCCommand {
	
	public KickallOPCommand(CommandSender sender, String[] args) {
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
				if(isPlayer(sender))
					if(!areSamePlayers((Player)sender, p))
						p.kickPlayer(kickReason);
			}
			String chatMessage = ChatColor.GRAY+"Tous les joueurs ont été kickés";
			if(noKickMessage)
				chatMessage += ".";
			else
				chatMessage += " pour la raison suivante : "+'"'+ChatColor.WHITE+kickReason+ChatColor.GRAY+'"'+".";
			Bukkit.broadcastMessage(chatMessage);
		}
		else {
			sender.sendMessage(noPermission);
		}
	}

}