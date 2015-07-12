package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class SpawnCommand {
	
	CCEssentials plugin;
	
	public SpawnCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.spawn"))) {
			if(args.length == 0) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
					p.sendMessage(ChatColor.GRAY+"You have been teleported to the spawn!");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			else if(args.length == 1) {
				if(CCEssentialsLibrary.getPlayer(args[0]) != null) {
					Player p = CCEssentialsLibrary.getPlayer(args[0]);
					p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
					sender.sendMessage(""+ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" has been teleported to the spawn!");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /spawn [player]");
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}
	
}
