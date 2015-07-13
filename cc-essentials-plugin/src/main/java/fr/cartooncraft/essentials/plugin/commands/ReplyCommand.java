package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class ReplyCommand {

	private static int neededArguments = 1;
	
	public ReplyCommand(CCEssentials plugin, CommandSender sender, String[] args, Command cmd) {
		if(hasPermission(sender, plugin)) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				CCEssentialsPlayer player = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
				if(args.length >= neededArguments) {
					if(CCEssentialsLibrary.isPlayer(player.getLatestCorrespondant())) {
						CCEssentialsPlayer p2 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(player.getLatestCorrespondant()));
						p2.sendPrivateMessage(sender, CCEssentialsLibrary.concatenateAllArgs(args));
						p2.setLatestCorrespondant(sender);
					}
					else {
						sender.sendMessage(ChatColor.RED+"Your last correspondant ("+player.getLatestCorrespondant()+") is offline! :(");
					}
				}
				else {
					sender.sendMessage(ChatColor.RED+"Nope! Usage: "+cmd.getUsage());
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.senderConsole);
			}
		}
	}
	
	public static boolean hasPermission(CommandSender sender, CCEssentials plugin) {
		return TellCommand.hasPermission(sender, plugin);
	}

}
