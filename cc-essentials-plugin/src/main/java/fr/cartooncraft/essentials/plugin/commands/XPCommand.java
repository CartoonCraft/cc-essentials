package fr.cartooncraft.essentials.plugin.commands;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class XPCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.feedall";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	public XPCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		if(args.length == 0) {
			if(hasPermission(sender, "cc-essentials.xp.get.self")) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					args = new String[2];
					args[0] = sender.getName();
					args[1] = "get";
				}
				else {
					sender.sendMessage(senderConsole);
					return;
				}
			}
			else {
				sender.sendMessage(noPermission);
				return;
			}
		}
		
		String playerName;
		String action;
		int amount = 0;
		boolean isLevel = false;
		if(args[args.length - 1].equalsIgnoreCase("get")) {
			playerName = CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 0, args.length - 1));
			action = "get";
		}
		else if(args.length >= 2) {
			if(args[args.length - 2].equalsIgnoreCase("set") || args[args.length - 2].equalsIgnoreCase("add") || args[args.length - 2].equalsIgnoreCase("remove")) {
				playerName = CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 0, args.length - 2));
				action = args[args.length - 2];
				isLevel = args[args.length - 1].toUpperCase().endsWith("L");
				try {
					if(isLevel)
						amount = Integer.parseInt(args[args.length - 1].substring(0, args[args.length - 1].length() - 1));
					else
						amount = Integer.parseInt(args[args.length - 1]);
				}
				catch(Exception e) {
					sender.sendMessage(ChatColor.RED+"Please insert a valid number!");
					return;
				}
			}
			else {
				playerName = CCEssentialsLibrary.concatenateAllArgs(args);
				action = "get";
			}
		}
		else {
			playerName = CCEssentialsLibrary.concatenateAllArgs(args);
			action = "get";
		}
		
		if(!CCEssentialsLibrary.isPlayer(playerName)) {
			sender.sendMessage(getPlayerNotFoundSentence(playerName));
			return;
		}
		
		CCEssentialsPlayer p = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(playerName));
		boolean areSamePlayers;
		String permission;
		boolean updated = true;
		
		if(!CCEssentialsLibrary.isPlayer(sender))
			areSamePlayers = false;
		else if(CCEssentialsLibrary.areSamePlayers(CCEssentialsLibrary.getPlayer(sender), p.getPlayer()))
			areSamePlayers = true;
		else
			areSamePlayers = false;
		permission = "cc-essentials.xp."+action+"."+(areSamePlayers ? "self" : "other");
		
		if(hasPermission(sender, permission)) {
			if(action.equalsIgnoreCase("get"))
				updated = false;
			else if(action.equalsIgnoreCase("set")) {
				p.getPlayer().setExp(0);
				p.getPlayer().setLevel(0);
				if(isLevel) {
					p.getPlayer().giveExpLevels(amount);
				}
				else {
					p.getPlayer().giveExp(amount);
				}
			}
			else if(action.equalsIgnoreCase("add")) {
				if(isLevel)
					p.getPlayer().giveExpLevels(amount);
				else
					p.getPlayer().giveExp(amount);
			}
			else if(action.equalsIgnoreCase("remove")) {
				if(isLevel)
					p.getPlayer().giveExpLevels(-amount);
				else
					p.getPlayer().giveExp(-amount);
			}

			double xp = p.getPlayer().getExp()*p.getPlayer().getExpToLevel();
			DecimalFormat df = new DecimalFormat("#");
			String xpString = df.format(xp);
			
			if(areSamePlayers)
				sender.sendMessage(ChatColor.GRAY+"You have "+(updated ? "now " : "")+ChatColor.RED+xpString+ChatColor.GRAY+" XP, "+ChatColor.RED+p.getPlayer().getLevel()+ChatColor.GRAY+" level"+(p.getPlayer().getLevel() >= 2 ? "s": "")+", and need "+ChatColor.RED+df.format(p.getPlayer().getExpToLevel()-xp)+ChatColor.GRAY+" more XP to reach level "+ChatColor.RED+(p.getPlayer().getLevel()+1)+ChatColor.GRAY+".");
			else
				sender.sendMessage(p.getPlayerName()+" has "+(updated ? "now " : "")+ChatColor.RED+xpString+ChatColor.GRAY+" XP, "+ChatColor.RED+p.getPlayer().getLevel()+ChatColor.GRAY+" level"+(p.getPlayer().getLevel() >= 2 ? "s": "")+", and needs "+ChatColor.RED+df.format(p.getPlayer().getExpToLevel()-xp)+ChatColor.GRAY+" more XP to reach level "+ChatColor.RED+(p.getPlayer().getLevel()+1)+ChatColor.GRAY+".");
		}
		else
			sender.sendMessage(noPermission);
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
