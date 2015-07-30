package fr.cartooncraft.essentials.plugin.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class KickCommand extends CCEssentialsCommand {

	private static String permission = "cc-essentials.kick";
	private static int neededArguments = 1;
	private static boolean canConsoleUse = true;
	
	public KickCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}

	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		
		if(CCEssentialsLibrary.isPlayer(args[0])) {
			sender.sendMessage(getPlayerNotFoundSentence(args[0]));
		}
		
		Player p = CCEssentialsLibrary.getPlayer(args[0]);
		
		String message = CCEssentialsLibrary.concatenateAllArgs(Arrays.copyOfRange(args, 1, args.length));		
		boolean messageEmpty = false;
		
		if(message.isEmpty()) {
			messageEmpty = true;
			message = "Kicked by "+sender.getName();
		}
		Bukkit.getLogger().info(ChatColor.GOLD+sender.getName()+" has kicked "+p.getName()+".");
		String messageChat = CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" has been kicked by ";
		if(sender instanceof Player) {
			Player p2 = (Player)sender;
			messageChat += CCEssentialsLibrary.getPlayerName(p2);
		}
		else {
			messageChat += ChatColor.RED+"CONSOLE";
		}
		if(messageEmpty) {
			messageChat += ChatColor.GRAY+".";
		}
		else {
			messageChat += ChatColor.GRAY+" for the following reason: \""+ChatColor.RESET+message+ChatColor.GRAY+"\".";
		}
		p.kickPlayer(message);
		Bukkit.broadcastMessage(messageChat);
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
