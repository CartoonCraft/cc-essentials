package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class IsInGodModeCommand extends CCEssentialsCommand {
	
	private static String permission = null;
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;

	public IsInGodModeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label,
			String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		if(args.length == 0) {
			if(hasPermission(sender, "cc-essentials.isingodmode.self")) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					CCEssentialsPlayer p = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
					if(p.getGodmode())
						sender.sendMessage(ChatColor.GRAY+"You're in godmode.");
					else
						sender.sendMessage(ChatColor.GRAY+"You're not in godmode.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			else
				sender.sendMessage(noPermission);
		}
		else {
			if(hasPermission(sender, "cc-essentials.isingodmode.other")) {
				String playerName = CCEssentialsLibrary.concatenateAllArgs(args);
				if(CCEssentialsLibrary.isPlayer(playerName)) {
					CCEssentialsPlayer p = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(playerName));
					if(p.getGodmode())
						sender.sendMessage(p.getPlayerName()+ChatColor.GRAY+" is in godmode.");
					else
						sender.sendMessage(p.getPlayerName()+ChatColor.GRAY+" isn't in godmode.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(playerName));
				}
			}
			else {
				sender.sendMessage(noPermission);
				sender.sendMessage(getUsage());
			}
		}
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
