package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class GamemodeCommand {
	
	CCEssentials plugin;
	
	public GamemodeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.gamemode"))) {
			if(args.length == 0) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					if(p.getGameMode() != GameMode.CREATIVE) {
						p.setGameMode(GameMode.CREATIVE);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+GameMode.CREATIVE.toString()+ChatColor.GRAY+".");
					}
					else {
						p.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+GameMode.SURVIVAL.toString()+ChatColor.GRAY+".");
					}
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			else if(args.length == 1) {
				if(args[0].equals("0") || args[0].equals("1") || args[0].equals("2") || args[0].equals("3")) {
					if(CCEssentialsLibrary.isPlayer(sender)) {
						GameMode gamemode = GameMode.SURVIVAL;
						if(args[0].equals("1"))
							gamemode = GameMode.CREATIVE;
						else if(args[0].equals("2"))
							gamemode = GameMode.ADVENTURE;
						else if(args[0].equals("3"))
							gamemode = GameMode.SPECTATOR;
						else
							gamemode = GameMode.SURVIVAL;
						CCEssentialsLibrary.getPlayer(sender).setGameMode(gamemode);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
					}
					else {
						sender.sendMessage(CCEssentialsLibrary.senderConsole);
					}
				}
				else if(CCEssentialsLibrary.isPlayer(args[0])) {
					Player p = CCEssentialsLibrary.getPlayer(args[0]);
					GameMode gamemode = GameMode.CREATIVE;
					if(p.getGameMode() != GameMode.CREATIVE) {
						p.setGameMode(GameMode.CREATIVE);
					}
					else {
						p.setGameMode(GameMode.SURVIVAL);
						gamemode = GameMode.SURVIVAL;
					}
					sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" is now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(ChatColor.RED+"Usage: /gamemode | /gamemode <0|1|2|3> | /gamemode <name> | /gamemode <name> <mode>");
				}
			}
			else if(args.length == 2) {
				if(CCEssentialsLibrary.isPlayer(args[0])) {
					GameMode gamemode = GameMode.SURVIVAL;
					if(args[1].equals("1"))
						gamemode = GameMode.CREATIVE;
					else if(args[1].equals("2"))
						gamemode = GameMode.ADVENTURE;
					else if(args[1].equals("3"))
						gamemode = GameMode.SPECTATOR;
					else
						gamemode = GameMode.SURVIVAL;
					CCEssentialsLibrary.getPlayer(args[0]).setGameMode(gamemode);
					sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(args[0])+ChatColor.GRAY+" is now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Usage: /gamemode | /gamemode <0|1|2> | /gamemode <name> | /gamemode <name> <mode>");
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}
}
