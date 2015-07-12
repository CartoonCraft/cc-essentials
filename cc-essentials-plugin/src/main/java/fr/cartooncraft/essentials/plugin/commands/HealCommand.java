package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class HealCommand {

	CCEssentials plugin;
	
	public HealCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.heal"))) {
			if(args.length == 0) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					p.setHealth(20);
					p.setFoodLevel(20);
					p.setExhaustion(5F);
					sender.sendMessage(ChatColor.GRAY+"You have been healed.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			else if(args.length == 1) {
				if(Bukkit.getPlayer(args[0]) != null) {
					Player p = Bukkit.getPlayer(args[0]);
					p.setHealth(20);
					p.setFoodLevel(20);
					p.setExhaustion(5F);
					sender.sendMessage(CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" has been healed.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /heal [player]");
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}
	

}
