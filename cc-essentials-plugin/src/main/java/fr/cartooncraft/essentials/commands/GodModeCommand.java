package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class GodModeCommand extends CCCommand {
	
	CCEssentials plugin;
	
	public GodModeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.god"))) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					boolean godMode = ConfigManager.get(p.getName()+".yml").getBoolean("godmode", false);
					if(godMode) {
						ConfigManager.get(p.getName()+".yml").set("godmode", false);
						godMode = false;
						p.sendMessage(ChatColor.GRAY+"You're not in godmode anymore.");
					}
					else {
						ConfigManager.get(p.getName()+".yml").set("godmode", true);
						godMode = true;
						p.sendMessage(ChatColor.GRAY+"You're now in godmode.");
					}
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			else if(args.length == 1) {
				if(isPlayer(args[0])) {
					Player p = getPlayer(args[0]);
					boolean godMode = ConfigManager.get(p.getName()+".yml").getBoolean("godmode", false);
					if(godMode) {
						ConfigManager.get(p.getName()+".yml").set("godmode", false);
						godMode = false;
						p.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" is not in godmode anymore.");
					}
					else {
						ConfigManager.get(p.getName()+".yml").set("godmode", true);
						godMode = true;
						p.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" is now in godmode.");
					}
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /god [player]");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}

}
