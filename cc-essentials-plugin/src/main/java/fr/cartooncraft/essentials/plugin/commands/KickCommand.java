package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class KickCommand {
	
	CCEssentials plugin;
	
	public KickCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(args.length < 1) {
			sender.sendMessage(ChatColor.RED+"Nope! Usage: /kick <player> <reason>");
		}
		else {
			if(sender.isOp() || (plugin.isUsingPermissions() && (sender.hasPermission("cc-essentials.kickall") || sender.hasPermission("cc-essentials.kick")))) {
				Player p = Bukkit.getPlayer(args[0]);
				if(p == null) {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
				else {
					boolean firstArg = true;
					String message = "";
					for(String arg : args) {
						if(!firstArg)
							message += arg+" ";
						firstArg = false;
					}
					// Remove last space - copy-paste from Stackoverflow :D
					if (message.length() > 0 && message.charAt(message.length()-1)==' ') {
						message = message.substring(0, message.length()-1);
					}
					boolean messageEmpty = false;
					if(message.isEmpty()) {
						messageEmpty = true;
						message = "Kicked by "+sender.getName();
					}
					Bukkit.getLogger().info(ChatColor.GOLD+sender.getName()+" has kicked "+p.getName()+".");
					p.kickPlayer(message);
					String messageChat = CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" has been kicked by ";
					if(sender instanceof Player) {
						Player p2 = (Player)sender;
						messageChat += CCEssentialsLibrary.getPlayerName(p2);
					}
					else {
						messageChat += ChatColor.RED+"CONSOLE";
					}
					if(messageEmpty) {
						messageChat += ChatColor.GRAY+".";
					}
					else {
						messageChat += ChatColor.GRAY+" for the following reason: \""+ChatColor.RESET+message+ChatColor.GRAY+"\".";
					}
					Bukkit.broadcastMessage(messageChat);
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.noPermission);
			}
		}
	}

}
