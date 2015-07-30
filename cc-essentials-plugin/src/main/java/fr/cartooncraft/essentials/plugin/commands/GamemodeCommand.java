package fr.cartooncraft.essentials.plugin.commands;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class GamemodeCommand extends CCEssentialsCommand {
	
	private static String permission = null;
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	private HashMap<Integer, GameMode> gamemodes;
	
	public GamemodeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label,
			String[] args) {
		super(plugin, sender, cmd, label, args);
	}

	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		
		gamemodes = new HashMap<Integer, GameMode>();
		gamemodes.put(0, GameMode.SURVIVAL);
		gamemodes.put(1, GameMode.CREATIVE);
		gamemodes.put(2, GameMode.ADVENTURE);
		gamemodes.put(3, GameMode.SPECTATOR);
		
		if(args.length == 0) {
			if(hasPermission(sender, "cc-essentials.gamemode.self")) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					p.setGameMode(guessGameMode(p.getGameMode()));
					sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+p.getGameMode().toString()+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			else {
				sender.sendMessage(noPermission);
			}
		}
		else if(isGameMode(args[0])) {
			if(args.length == 1) {
				if(hasPermission(sender, "cc-essentials.gamemode.self")) {
					if(CCEssentialsLibrary.isPlayer(sender)) {
						GameMode gamemode = getGameMode(args[0]);					
						CCEssentialsLibrary.getPlayer(sender).setGameMode(gamemode);
						sender.sendMessage(ChatColor.GRAY+"You're now in gamemode "+ChatColor.RED+gamemode.toString()+ChatColor.GRAY+".");
					}
					else {
						sender.sendMessage(CCEssentialsLibrary.senderConsole);
					}
				}
			}
			else {
				sender.sendMessage(getUsage());
			}
		}
		else {
			String playerName;
			Bukkit.getLogger().info(args[args.length - 1]);
			if(isGameMode(args[args.length - 1])) {
				if(hasPermission(sender, "cc-essentials.gamemode.other")) {
					playerName = CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 0, args.length - 2));
					Bukkit.getLogger().info(playerName);
					if(CCEssentialsLibrary.isPlayer(playerName)) {
						GameMode gamemode = getGameMode(args[args.length-1]);
						Player p = CCEssentialsLibrary.getPlayer(playerName); 
						p.setGameMode(gamemode);
						sender.sendMessage(new CCEssentialsPlayer(p).getPlayerName()+ChatColor.GRAY+" is now in gamemode "+ChatColor.RED+p.getGameMode().toString()+ChatColor.GRAY+".");
					}
					else {
						sender.sendMessage(getPlayerNotFoundSentence(playerName));
						sender.sendMessage(getUsage());
					}
				}
			}
			else {
				if(hasPermission(sender, "cc-essentials.gamemode.other")) {
					playerName = CCEssentialsLibrary.concatenateAllArgs(args);
					if(CCEssentialsLibrary.isPlayer(playerName)) {
						Player p = CCEssentialsLibrary.getPlayer(playerName);
						p.setGameMode(guessGameMode(p.getGameMode()));
						sender.sendMessage(new CCEssentialsPlayer(p).getPlayerName()+ChatColor.GRAY+" is now in gamemode "+ChatColor.RED+p.getGameMode().toString()+ChatColor.GRAY+".");
					}
					else {
						sender.sendMessage(getPlayerNotFoundSentence(playerName));
						sender.sendMessage(getUsage());
					}
				}
			}
		}
	}
	
	public boolean isGameMode(Integer i) {
		Bukkit.getLogger().info("isGameMode(i)");
		if(getGameMode(i) != null)
			return true;
		return false;
	}
	
	public boolean isGameMode(String s) {
		try {
			return isGameMode(Integer.parseInt(s));
		}
		catch(NumberFormatException e) {
			if(getGameMode(s) != null)
				return true;
		}
		return false;
	}
	
	public GameMode getGameMode(Integer i) {
		return gamemodes.get(i);
	}
	
	public GameMode getGameMode(String s) {
		if(s.length() == 1) {
			try {
				Integer i = Integer.parseInt(s);
				return getGameMode(i);
			}
			catch(NumberFormatException e) {
				
			}
		}
		try {
			return GameMode.valueOf(s.toUpperCase());
		}
		catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	public GameMode guessGameMode(GameMode actualGameMode) {
		GameMode gamemode;
		if(actualGameMode != GameMode.CREATIVE) {
			gamemode = GameMode.CREATIVE;
		}
		else {
			gamemode = GameMode.SURVIVAL;
		}
		return gamemode;
	}
	
	@Override
	public String getPermission() {
		return permission;
	}


	@Override
	public int getNeededArguments() {
		return neededArguments;
	}


	@Override
	public boolean canConsoleUse() {
		return canConsoleUse;
	}
}
