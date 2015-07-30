package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class HealAllCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.healall";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;

	public HealAllCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		for(Player p : Bukkit.getOnlinePlayers()) {
			new CCEssentialsPlayer(p).heal();
		}
		sender.sendMessage(ChatColor.GRAY+"All players have been healed.");
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
