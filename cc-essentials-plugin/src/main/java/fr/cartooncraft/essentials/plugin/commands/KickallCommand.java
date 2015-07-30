package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class KickallCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.kickall";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	public KickallCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label,
			String[] args) {
		super(plugin, sender, cmd, label, args);
	}

	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		String kickReason = CCEssentialsLibrary.concatenateAllArgs(args);
		boolean noKickMessage = false;
		
		if(kickReason == "") {
			noKickMessage = true;
			kickReason = "Kicked by "+sender.getName();
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!p.isOp())
				p.kickPlayer(kickReason);
		}
		String chatMessage = ChatColor.GRAY+"All the players (excepted the "+ChatColor.RED+"OPs"+ChatColor.GRAY+") have been kicked";
		if(noKickMessage)
			chatMessage += ".";
		else
			chatMessage += " for the following reason: "+'"'+ChatColor.WHITE+kickReason+ChatColor.GRAY+'"'+".";
		Bukkit.broadcastMessage(chatMessage);
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