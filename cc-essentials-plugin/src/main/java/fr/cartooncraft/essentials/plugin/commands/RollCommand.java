package fr.cartooncraft.essentials.plugin.commands;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class RollCommand extends CCEssentialsCommand {

	private static String permission = "cc-essentials.roll";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	public RollCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		if(args.length == 0)
			Bukkit.broadcastMessage(""+ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(1, 100)+ChatColor.GRAY+"! ("+ChatColor.RED+1+ChatColor.GRAY+"-"+ChatColor.RED+100+ChatColor.GRAY+")");
		else if(args.length == 1) {
			String range = args[0];
			Pattern p = Pattern.compile("^([0-9]+)-([0-9]+)$");
			Matcher m = p.matcher(range);
			if(m.matches()) {
				int x, y = 0;
				x = Integer.parseInt(m.group(1));
				y = Integer.parseInt(m.group(2));
				Bukkit.broadcastMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(x, y)+ChatColor.GRAY+"! ("+ChatColor.RED+x+ChatColor.GRAY+"-"+ChatColor.RED+y+ChatColor.GRAY+")");
			}
			else
				sender.sendMessage(ChatColor.RED+args[0]+" isn't a valid range. Example: 1-10");
		}
		else if(args.length == 2) {
			String xs = args[0];
			String ys = args[1];
			int x = 0;
			int y = 0;
			try {
				x = Integer.parseInt(xs);
				y = Integer.parseInt(ys);
			}
			catch(NumberFormatException e) {
				sender.sendMessage(ChatColor.RED+"Please enter numbers.");
			}
			Bukkit.broadcastMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(x, y)+ChatColor.GRAY+"! ("+ChatColor.RED+x+ChatColor.GRAY+"-"+ChatColor.RED+y+ChatColor.GRAY+")");
		}
		else {
			sender.sendMessage(getUsage());
		}
	}
	
	public static int randInt(int min, int max) {
		// Usually this should be a field rather than a method variable so
		// that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
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