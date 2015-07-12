package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class IsInGodModeCommand {

	CCEssentials plugin;
	
	public IsInGodModeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.isingod"))) {
			if(args.length == 0) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					boolean godMode = CCEssentialsLibrary.getConfigFile(p).getBoolean("godmode", false);
					if(godMode) {
						p.sendMessage(ChatColor.GRAY+"You're in godmode.");
					}
					else {
						p.sendMessage(ChatColor.GRAY+"You're not in godmode.");
					}
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			else if(args.length == 1) {
				if(CCEssentialsLibrary.isPlayer(args[0])) {
					Player p = CCEssentialsLibrary.getPlayer(args[0]);
					boolean godMode = CCEssentialsLibrary.getConfigFile(p).getBoolean("godmode", false);
					if(godMode) {
						sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p.getName())+ChatColor.GRAY+" is in godmode.");
					}
					else {
						sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p.getName())+ChatColor.GRAY+" isn't in godmode.");
					}
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));;
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /isingodmode [player]");
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}
}
