package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class SuicideCommand {

	CCEssentials plugin;
	
	public SuicideCommand(CCEssentials plugin2, CommandSender sender) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.suicide"))) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				sender.sendMessage(ChatColor.RED+"Goodbye, good world...");
				Bukkit.broadcastMessage(""+ChatColor.GRAY+ChatColor.BOLD+CCEssentialsLibrary.getPlayer(sender).getName()+" chose the way of suicide... Rest in peace!");
				CCEssentialsLibrary.getPlayer(sender).setHealth(0);
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.senderConsole);
			}
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nope! Usage: /kill [player]");
		}
	}
	

}
