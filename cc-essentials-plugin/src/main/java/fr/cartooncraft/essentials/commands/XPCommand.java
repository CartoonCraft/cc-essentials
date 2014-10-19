package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class XPCommand extends CCCommand {

	CCEssentials plugin;
	
	public XPCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(args.length == 0) {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.xp"))) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					sender.sendMessage(ChatColor.GRAY+"You have "+ChatColor.RED+p.getExp()+ChatColor.GRAY+", "+ChatColor.RED+p.getExpToLevel()+ChatColor.GRAY+" levels.");
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
		}
		else {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.xp.get"))) {
				if(args.length == 1) {
					if(isPlayer(args[0])) {
						Player p = getPlayer(args[0]);
						sender.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" has "+ChatColor.RED+p.getExp()+ChatColor.GRAY+", "+ChatColor.RED+p.getExpToLevel()+ChatColor.GRAY+" levels.");
					}
					else {
						sender.sendMessage(getPlayerNotFoundSentence(args[0]));
					}
				}
				else if(args.length == 2) {
					if(!isPlayer(args[0])) {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /xp [player] [get|set|add|remove] amount<L>");
					}
					else if(args[1].equalsIgnoreCase("get")) {
						Player p = getPlayer(args[0]);
						sender.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" has "+ChatColor.RED+p.getExp()+ChatColor.GRAY+", "+ChatColor.RED+p.getExpToLevel()+ChatColor.GRAY+" levels.");
					}
					else if(!(args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove"))) {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /xp "+args[0]+" [get|set|add|remove] amount<L>");
					}
					else {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /xp "+args[0]+" "+args[1]+" amount<L>");
					}
				}
				else if (args.length == 3) {
					if(!isPlayer(args[0])) {
						sender.sendMessage(getPlayerNotFoundSentence(args[0]));
					}
					else if(!(args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("get") || args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("add"))) {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /xp "+args[0]+" [get|set|add|remove] amount<L>");
					}
					else {
						Player p = getPlayer(args[1]);
						
						int XPAmount;
						boolean isLevel = args[2].endsWith("l") || args[2].endsWith("L");
						if(isLevel) {
							XPAmount = Integer.getInteger(args[2].substring(0, args[2].length() - 1));
						}
						else {
							XPAmount = Integer.getInteger(args[2]);
						}
						if(args[1] == "set") {
							if(isLevel) {
								p.setExp(0);
								p.giveExpLevels(XPAmount);
							}
							else {
								p.setExp(XPAmount);
							}
						}
						else if(args[1] == "add") {
							if(isLevel) {
								p.giveExpLevels(XPAmount);
							}
							else {
								p.giveExp(XPAmount);
							}
						}
						else if(args[1] == "remove") {
							if(isLevel) {
								p.giveExpLevels(-XPAmount);
							}
							else {
								p.giveExp(-XPAmount);
							}
						}
						String msg = ""+ChatColor.RED+XPAmount+ChatColor.GRAY+" ";
						if(isLevel) {
							msg += "levels ";
						}
						msg += "has been given to ";
						if(getPlayer(sender) == p) {
							msg += "you.";
						}
						else {
							msg += getPlayerName(p)+".";
						}
						sender.sendMessage(msg);
					}
				}
				else {
					sender.sendMessage(ChatColor.RED+"Nope! Usage: /xp [player] [get|set|add|remove] amount<L>");
				}
			}
		}
	}
	

}
