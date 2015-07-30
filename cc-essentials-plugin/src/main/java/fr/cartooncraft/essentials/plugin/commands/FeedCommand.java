package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class FeedCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.feed";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;

	public FeedCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		CCEssentialsPlayer p;
		if(args.length == 0)
			p = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
		else {
			String playerName = CCEssentialsLibrary.concatenateAllArgs(args);
			if(CCEssentialsLibrary.isPlayer(playerName))
				p = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(playerName));
			else {
				sender.sendMessage(getPlayerNotFoundSentence(playerName));
				return;
			}
		}
		
		p.feed();
		if(args.length == 0)
			sender.sendMessage(ChatColor.GRAY+"You have been fed.");
		else
			sender.sendMessage(p.getPlayerName()+ChatColor.GRAY+" has been fed.");
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
