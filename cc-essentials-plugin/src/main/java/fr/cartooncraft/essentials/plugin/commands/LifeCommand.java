package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class LifeCommand {

	CCEssentials plugin;
	
	public LifeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(args.length == 0) {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.life.get.self"))) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					sender.sendMessage(ChatColor.GRAY+"You have "+ChatColor.RED+p.getHealth()+ChatColor.GRAY+" HP.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
		}
		else {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.life.get"))) {
				if(args.length == 1) {
					if(CCEssentialsLibrary.isPlayer(args[0])) {
						Player p = CCEssentialsLibrary.getPlayer(args[0]);
						sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" has "+ChatColor.RED+p.getHealth()+ChatColor.GRAY+" HP.");
					}
					else {
						sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
					}
				}
				else if(args.length == 2) {
					if(!CCEssentialsLibrary.isPlayer(args[0])) {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /life [player] [get|set|add|remove] amount");
					}
					else if(args[1].equalsIgnoreCase("get")) {
						Player p = CCEssentialsLibrary.getPlayer(args[0]);
						sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" has "+ChatColor.RED+p.getHealth()+ChatColor.GRAY+" HP.");
					}
					else if(!(args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove"))) {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /life "+args[0]+" [get|set|add|remove] amount");
					}
					else {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /life "+args[0]+" "+args[1]+" amount");
					}
				}
				else if (args.length == 3) {
					if(!CCEssentialsLibrary.isPlayer(args[0])) {
						sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
					}
					else if(!(args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("get") || args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("add"))) {
						sender.sendMessage(ChatColor.RED+"Nope! Usage: /life "+args[0]+" [get|set|add|remove] amount");
					}
					else {
						Player p = CCEssentialsLibrary.getPlayer(args[0]);
						
						float HPAmount;
						HPAmount = Float.parseFloat(args[2]);
						if(args[1].equalsIgnoreCase("set")) {
							p.setHealth(HPAmount);
						}
						else if(args[1].equalsIgnoreCase("add")) {
							p.setHealth(p.getHealth()+HPAmount);
						}
						else if(args[1].equalsIgnoreCase("remove")) {
							p.setHealth(p.getHealth()-HPAmount);
						}
						else {
							sender.sendMessage(ChatColor.RED+"Nope! Usage: /life [player] [get|set|add|remove] amount");
						}
						String msg = ""+ChatColor.RED+HPAmount+ChatColor.GRAY+" HP has been given to ";
						if(CCEssentialsLibrary.getPlayer(sender) == p) {
							msg += "you.";
						}
						else {
							msg += CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+".";
						}
						sender.sendMessage(msg);
					}
				}
				else {
					sender.sendMessage(ChatColor.RED+"Nope! Usage: /life [player] [get|set|add|remove] amount");
				}
			}
		}
	}
	

}

/*package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class LifeCommand extends CCCommand {
	
	CCEssentials plugin;
	
	public LifeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.life.get.self"))) {
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
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /life <player> [get|set|add|remove] amount");
			}
		}
	}

}*/