package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class ListCommand extends CCCommand {
	
	public ListCommand(CommandSender sender) {
		Player[] players = Bukkit.getOnlinePlayers();
		sender.sendMessage(ChatColor.GRAY+"Online players: "+ChatColor.RED+players.length);
		String message = ""+ChatColor.GRAY;
		int i = 0;
		for(Player p : players) {
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