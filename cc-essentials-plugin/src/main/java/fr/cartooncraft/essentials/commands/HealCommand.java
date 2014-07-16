package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class HealCommand extends CCCommand {

	CCEssentials plugin;
	
	public HealCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.heal"))) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					p.setHealth(20);
					p.setFoodLevel(20);
					p.setExhaustion(5F);
					sender.sendMessage(ChatColor.GRAY+"You have been healed.");
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			else if(args.length == 1) {
				if(Bukkit.getPlayer(args[0]) != null) {
					Player p = Bukkit.getPlayer(args[0]);
					p.setHealth(20);
					p.setFoodLevel(20);
					p.setExhaustion(5F);
					sender.sendMessage(getPlayerName(p)+ChatColor.GRAY+" has been healed.");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /heal [player]");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
	

}
