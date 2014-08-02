package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class GamemodeCommand extends CCCommand {
	
	CCEssentials plugin;
	
	public GamemodeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.gamemode"))) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					if(p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
						p.setGameMode(GameMode.CREATIVE);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+GameMode.CREATIVE.toString()+ChatColor.GRAY+".");
					}
					else {
						p.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+GameMode.SURVIVAL.toString()+ChatColor.GRAY+".");
					}
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			else if(args.length == 1) {
				if(args[0].equals("0") || args[0].equals("1") || args[0].equals("2")) {
					if(isPlayer(sender)) {
						GameMode gamemode = GameMode.SURVIVAL;
						if(args[0].equals("1"))
							gamemode = GameMode.CREATIVE;
						else if(args[0].equals("2"))
							gamemode = GameMode.ADVENTURE;
						else
							gamemode = GameMode.SURVIVAL;
						getPlayer(sender).setGameMode(gamemode);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
					}
					else {
						sender.sendMessage(senderConsole);
					}
				}
				else if(isPlayer(args[0])) {
					Player p = getPlayer(args[0]);
					GameMode gamemode = GameMode.CREATIVE;
					if(p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
						p.setGameMode(GameMode.CREATIVE);
					}
					else {
						p.setGameMode(GameMode.SURVIVAL);
						gamemode = GameMode.SURVIVAL;
					}
					sender.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" is now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(ChatColor.RED+"Usage: /gamemode | /gamemode <0|1|2> | /gamemode <name> | /gamemode <name> <mode>");
				}
			}
			else if(args.length == 2) {
				if(isPlayer(args[0])) {
					GameMode gamemode = GameMode.SURVIVAL;
					if(args[1].equals("1"))
						gamemode = GameMode.CREATIVE;
					else if(args[1].equals("2"))
						gamemode = GameMode.ADVENTURE;
					else
						gamemode = GameMode.SURVIVAL;
					getPlayer(args[0]).setGameMode(gamemode);
					sender.sendMessage(ChatColor.GRAY+getPlayerName(args[0])+ChatColor.GRAY+" is now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Usage: /gamemode | /gamemode <0|1|2> | /gamemode <name> | /gamemode <name> <mode>");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
}
