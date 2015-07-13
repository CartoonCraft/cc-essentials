package fr.cartooncraft.essentials.lib;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CCEssentialsCommand {
	
	protected static String noPermission = ""+ChatColor.RESET+ChatColor.RED+"Sorry, you're not allowed to do this.";
	protected static String senderConsole = ""+ChatColor.RESET+ChatColor.RED+"Sorry, you're a console, you can't do this!";
	protected static String getPlayerNotFoundSentence(String name) {
		return ChatColor.RED+"Can't find "+name+". Is he offline?";
	}
	protected static String getUsage(Command cmd) {
		return ChatColor.RED+"Nope! Usage: "+cmd.getUsage();
	}
	
	
	public CCEssentialsCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		if(!hasPermission(sender, plugin)) {
			sender.sendMessage(noPermission);
			return;
		}
		if(!canConsoleUse() && !CCEssentialsLibrary.isPlayer(sender)) {
			sender.sendMessage(senderConsole);
			return;
		}
		if(args.length < getNeededArguments()) {
			sender.sendMessage(getUsage(cmd));
			return;
		}
		
		executeCommand(plugin, sender, cmd, label, args);
	}
	
	public abstract void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args);
	
	public boolean hasPermission(CommandSender sender, CCEssentialsJavaPlugin plugin) {
		if(sender.isOp())
			return true;
		
		if(CCEssentialsLibrary.isPlayer(sender)) {
			CCEssentialsPlayer ccPlayer = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender), plugin);
			return ccPlayer.hasPermission(getPermission());
		}
		else if(sender.hasPermission(getPermission()))
			return true;
		else
			return false;
	}
	
	public abstract String getPermission();
	
	public abstract int getNeededArguments();
	
	public abstract boolean canConsoleUse();
}
