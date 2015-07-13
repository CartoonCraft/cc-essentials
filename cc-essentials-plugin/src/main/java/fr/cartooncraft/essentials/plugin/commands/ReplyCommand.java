package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class ReplyCommand extends CCEssentialsCommand {

	private static String permission = "cc-essentials.tell";
	private static int neededArguments = 1;
	private static boolean canConsoleUse = false;
	
	public ReplyCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		CCEssentialsPlayer player = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
		if(CCEssentialsLibrary.isPlayer(player.getLatestCorrespondant())) {
			CCEssentialsPlayer p2 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(player.getLatestCorrespondant()));
			p2.sendPrivateMessage(sender, CCEssentialsLibrary.concatenateAllArgs(args));
			p2.setLatestCorrespondant(sender);
		}
		else {
			sender.sendMessage(ChatColor.RED+"Your last correspondant ("+player.getLatestCorrespondant()+") is offline! :(");
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