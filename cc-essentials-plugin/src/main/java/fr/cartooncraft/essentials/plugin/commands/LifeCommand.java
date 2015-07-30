package fr.cartooncraft.essentials.plugin.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class LifeCommand extends CCEssentialsCommand {
	
	private static String permission = null;
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;

	public LifeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		if(args.length == 0) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				if(hasPermission(sender, "cc-essentials.life.get.self")) {
					sender.sendMessage(ChatColor.GRAY+"You have "+ChatColor.RED+CCEssentialsLibrary.getPlayer(sender).getHealth()+ChatColor.GRAY+" HP.");
				}
			}
			else {
				sender.sendMessage(senderConsole);
			}
		}
		else {
			String playerName;
			String action;
			double amount = 0;
			if(args[args.length - 1].equalsIgnoreCase("get")) {
				playerName = CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 0, args.length - 1));
				action = "get";
				
			}
			else if(args.length >= 2) {
				if(args[args.length - 2].equalsIgnoreCase("set") || args[args.length - 2].equalsIgnoreCase("add") || args[args.length - 2].equalsIgnoreCase("remove")) {
					playerName = CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 0, args.length - 2));
					action = args[args.length - 2];
					try {
						amount = Double.parseDouble(args[args.length - 1]);
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED+"Please insert a valid number!");
						return;
					}
				}
				else {
					playerName = CCEssentialsLibrary.concatenateAllArgs(args);
					action = "set";
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
			
			if(areSamePlayers)
				permission = "cc-essentials.life."+action+".self";
			else
				permission = "cc-essentials.life."+action+".other";
			
			if(hasPermission(sender, permission)) {
				if(action.equalsIgnoreCase("get"))
					updated = false;
				else if(action.equalsIgnoreCase("set"))
					p.getPlayer().setHealth(amount);
				else if(action.equalsIgnoreCase("add")) {
					if(!(p.getPlayer().getHealth()+amount > p.getPlayer().getMaxHealth()))
						p.getPlayer().setHealth(p.getPlayer().getHealth()+amount);
					else
						p.getPlayer().setHealth(p.getPlayer().getMaxHealth());
				}
				else if(action.equalsIgnoreCase("remove")) {
					if(!(p.getPlayer().getHealth()-amount < 0))
						p.getPlayer().setHealth(p.getPlayer().getHealth()-amount);
					else
						p.getPlayer().setHealth(0);
				}
				
				if(areSamePlayers)
					sender.sendMessage(ChatColor.GRAY+"You have "+(updated ? "now ": "")+ChatColor.RED+p.getPlayer().getHealth()+ChatColor.GRAY+" HP.");
				else
					sender.sendMessage(p.getPlayerName()+ChatColor.GRAY+" has "+(updated ? "now ": "")+ChatColor.RED+p.getPlayer().getHealth()+ChatColor.GRAY+" HP.");
			}
			else
				sender.sendMessage(noPermission);
		}
		
		// Donc là faut que je continue avec la prise en charge des pseudos à espaces. Je dois donc
		// prendre l'avant-dernier paramètre pour get/set/etc et non pas le 2e tout bêtement.
		// DONE
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
