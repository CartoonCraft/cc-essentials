package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class KillCommand {

	CCEssentials plugin;
	
	public KillCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(args.length == 1) {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.kill"))) {
				Player p = Bukkit.getPlayer(args[0]);
				if(p != null) {
					p.setHealth(0);
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.noPermission);
			}
		}
		else if(args.length == 0) {
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
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nope! Usage: /kill [player]");
		}
	}
	

}
