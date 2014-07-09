package fr.cartooncraft.essentials.commands;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.CCCommand;

public class RollCommand extends CCCommand {
	
	public RollCommand(CommandSender sender, String[] args) {
		if(args.length == 0)
			Bukkit.broadcastMessage(""+ChatColor.GRAY+getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(1, 100)+ChatColor.GRAY+"!");
		else if(args.length == 1) {
			String range = args[0];
			Pattern p = Pattern.compile("^((0-9)+)-((0-9)+)$");
			Matcher m = p.matcher(range);
			if(m.matches()) {
				int x = Integer.getInteger(m.group(1));
				int y = Integer.getInteger(m.group(2));
				Bukkit.broadcastMessage(ChatColor.GRAY+getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(x, y)+ChatColor.GRAY+"!");
			}
			else
				sender.sendMessage(ChatColor.RED+args[0]+" isn't a valid range. Example: 1-10");
		}
		else if(args.length == 2) {
			int x = Integer.getInteger(args[0]);
			int y = Integer.getInteger(args[1]);
			Bukkit.broadcastMessage(ChatColor.GRAY+getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(x, y)+ChatColor.GRAY+"!");
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

}