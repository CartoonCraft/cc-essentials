package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class LifeCommand extends CCCommand {
	
	CCEssentials plugin;
	
	public LifeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.life"))) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					sender.sendMessage(ChatColor.GRAY+"You have "+ChatColor.RED+p.getHealth()+ChatColor.GRAY+" HP.");
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			else if(args.length == 1) {
				if(isPlayer(args[0])) {
					Player p = getPlayer(args[0]);
					sender.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" has "+ChatColor.RED+p.getHealth()+ChatColor.GRAY+" HP.");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /life [player]");
			}
		}
	}

}