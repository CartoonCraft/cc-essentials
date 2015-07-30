package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class TopCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.top";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = false;

	public TopCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		Player p = CCEssentialsLibrary.getPlayer(sender);
		p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation()), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
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
