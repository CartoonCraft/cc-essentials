package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class SuicideCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.suicide";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = false;

	public SuicideCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		sender.sendMessage(ChatColor.RED+"Goodbye, good world...");
		Bukkit.broadcastMessage(""+ChatColor.GRAY+ChatColor.BOLD+CCEssentialsLibrary.getPlayer(sender).getName()+" chose the way of suicide... Rest in peace!");
		CCEssentialsLibrary.getPlayer(sender).setHealth(0);
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
