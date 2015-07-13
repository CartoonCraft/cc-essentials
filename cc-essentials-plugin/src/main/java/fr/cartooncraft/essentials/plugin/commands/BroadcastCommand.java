package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class BroadcastCommand extends CCEssentialsCommand {

	private static String permission = "cc-essentials.broadcast";
	private static int neededArguments = 1;
	private static boolean canConsoleUse = true;

	public BroadcastCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label,
			String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		String message = ""+ChatColor.RED+ChatColor.BOLD+CCEssentialsLibrary.concatenateAllArgs(args);
		message = message.replace('&', ChatColor.COLOR_CHAR);
		Bukkit.broadcastMessage(message);
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