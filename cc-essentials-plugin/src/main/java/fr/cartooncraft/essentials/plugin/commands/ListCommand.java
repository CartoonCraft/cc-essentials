package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;

public class ListCommand extends CCEssentialsCommand {

	private static String permission = "cc-essentials.list";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	public ListCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		sender.sendMessage(ChatColor.GRAY+"Online players: "+ChatColor.RED+Bukkit.getOnlinePlayers().size());
		String message = ""+ChatColor.GRAY;
		int i = 0;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(i != 0) {
				message += ChatColor.GRAY+", ";
			}
			if(p.isOp())
				message += ChatColor.RED;
			message += p.getName();
			i++;
		}
		sender.sendMessage(message);
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