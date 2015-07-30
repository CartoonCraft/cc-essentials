package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class KillCommand extends CCEssentialsCommand {
	
	public KillCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}

	private static String permission = null;
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		if(args.length == 0) {
			new SuicideCommand(plugin, sender, cmd, label, args);
		}
		else {
			if(hasPermission(sender, "cc-essentials.kill")) {
				String playerName = CCEssentialsLibrary.concatenateAllArgs(args);
				if(CCEssentialsLibrary.isPlayer(playerName)) {
					CCEssentialsLibrary.getPlayer(playerName).setHealth(0);
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(playerName));
				}
			}
			else {
				sender.sendMessage(noPermission);
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
