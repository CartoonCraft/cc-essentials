package fr.cartooncraft.essentials.plugin.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class TellCommand extends CCEssentialsCommand {

	private static String permission = "cc-essentials.tell";
	private static int neededArguments = 2;
	private static boolean canConsoleUse = true;

	public TellCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		if(CCEssentialsLibrary.isPlayer(args[0])) {
			CCEssentialsPlayer p2 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(args[0]));
			p2.sendPrivateMessage(sender, CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 1, args.length)));
			p2.setLatestCorrespondant(sender);
			if(CCEssentialsLibrary.isPlayer(sender)) {
				new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender)).setLatestCorrespondant((CommandSender)p2.getPlayer());
			}
		}
		else {
			sender.sendMessage(getPlayerNotFoundSentence(args[0]));
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