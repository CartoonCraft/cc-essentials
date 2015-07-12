package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class GodModeCommand {
	
	CCEssentials plugin;
	
	public GodModeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.god"))) {
			if(args.length == 0) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					boolean godMode = CCEssentialsLibrary.getConfigFile(p).getBoolean("godmode", false);
					if(godMode) {
						CCEssentialsLibrary.getConfigFile(p).set("godmode", false);
						godMode = false;
						p.sendMessage(ChatColor.GRAY+"You're not in godmode anymore.");
					}
					else {
						CCEssentialsLibrary.getConfigFile(p).set("godmode", true);
						godMode = true;
						p.sendMessage(ChatColor.GRAY+"You're now in godmode.");
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
						CCEssentialsLibrary.getConfigFile(p).set("godmode", false);
						godMode = false;
						p.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" is not in godmode anymore.");
					}
					else {
						CCEssentialsLibrary.getConfigFile(p).set("godmode", true);
						godMode = true;
						p.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" is now in godmode.");
					}
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /god [player]");
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}

}
