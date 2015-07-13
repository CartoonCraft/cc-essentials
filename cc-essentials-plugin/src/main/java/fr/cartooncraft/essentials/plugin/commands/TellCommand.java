package fr.cartooncraft.essentials.plugin.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class TellCommand {

	private static String permission = "cc-essentials.tell";
	private static int neededArguments = 2;
	
	public TellCommand(CCEssentials plugin, CommandSender sender, String[] args, Command cmd) {
		if(hasPermission(sender, plugin)) {
			if(args.length >= neededArguments) {
				if(CCEssentialsLibrary.isPlayer(args[0])) {
					CCEssentialsPlayer p2 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(args[0]));
					p2.sendPrivateMessage(sender, CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 1, args.length)));
					p2.setLatestCorrespondant(sender);
					if(CCEssentialsLibrary.isPlayer(sender)) {
						new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender)).setLatestCorrespondant((CommandSender)p2.getPlayer());
					}
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: "+cmd.getUsage());
			}
		}
	}
	
	public static boolean hasPermission(CommandSender sender, CCEssentials plugin) {
		if(sender.isOp())
			return true;
		
		if(CCEssentialsLibrary.isPlayer(sender)) {
			CCEssentialsPlayer ccPlayer = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender), plugin);
			return ccPlayer.hasPermission(permission);
		}
		else if(sender.hasPermission(permission))
			return true;
		else
			return false;
	}
}