package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class SuicideCommand extends CCCommand {

	CCEssentials plugin;
	
	public SuicideCommand(CCEssentials plugin2, CommandSender sender) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.suicide"))) {
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
