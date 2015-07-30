package fr.cartooncraft.essentials.lib;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CCEssentialsCommand {
	
	private Command cmd;
	private CCEssentialsJavaPlugin plugin;
	
	protected static String noPermission = ""+ChatColor.RESET+ChatColor.RED+"Sorry, you're not allowed to do this.";
	protected static String senderConsole = ""+ChatColor.RESET+ChatColor.RED+"Sorry, you're a console, you can't do this!";
	protected static String getPlayerNotFoundSentence(String name) {
		return ChatColor.RED+"Can't find "+name+". Is he offline?";
	}
	protected String getUsage() {
		return ChatColor.RED+"Nope! Usage: "+cmd.getUsage();
	}
	
	public CCEssentialsCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		this.cmd = cmd;
		this.plugin = plugin;
		
		if(!hasPermission(sender)) {
			sender.sendMessage(noPermission);
			return;
		}
		if(!canConsoleUse() && !CCEssentialsLibrary.isPlayer(sender)) {
			sender.sendMessage(senderConsole);
			return;
		}
		if(args.length < getNeededArguments()) {
			sender.sendMessage(getUsage());
			return;
		}
		
		executeCommand(plugin, sender, cmd, label, args);
	}
	
	public abstract void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args);
	
	public boolean hasPermission(CommandSender sender) {
		if(getPermission() == null)
			return true;
		
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
	
	public boolean hasPermission(CommandSender sender, String permission) {
		if(sender.isOp())
			return true;
		
		if(CCEssentialsLibrary.isPlayer(sender)) {
			CCEssentialsPlayer ccPlayer = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender), plugin);
			return ccPlayer.hasPermission(permission);
		}
		else if(sender.hasPermission(permission))
			return true;
		else
			return false;
	}
	
	public abstract String getPermission();
	
	public abstract int getNeededArguments();
	
	public abstract boolean canConsoleUse();
}
