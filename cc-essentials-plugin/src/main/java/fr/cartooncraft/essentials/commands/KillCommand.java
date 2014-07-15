package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class KillCommand extends CCCommand {

	public KillCommand(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(sender.isOp()) {
				Player p = Bukkit.getPlayer(args[0]);
				if(p != null) {
					p.setHealth(0);
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(noPermission);
			}
		}
		else if(args.length == 0) {
			if(isPlayer(sender)) {
				sender.sendMessage(ChatColor.RED+"Goodbye, good world...");
				Bukkit.broadcastMessage(""+ChatColor.GRAY+ChatColor.BOLD+getPlayer(sender).getName()+" chose the way of suicide... Rest in peace!");
				getPlayer(sender).setHealth(0);
			}
			else {
				sender.sendMessage(senderConsole);
			}
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nope! Usage: /kill [player]");
		}
	}
	

}
