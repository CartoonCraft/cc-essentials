package fr.cartooncraft.essentials.plugin.commands;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class RollCommand {
	
	CCEssentials plugin;
	
	public RollCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(plugin.isUsingPermissions()) {
			if(sender.hasPermission("cc-essentials.roll")) {
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
					int x, y = 0;
					x = Integer.parseInt(xs);
					y = Integer.parseInt(ys);
					Bukkit.broadcastMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(x, y)+ChatColor.GRAY+"! ("+ChatColor.RED+x+ChatColor.GRAY+"-"+ChatColor.RED+y+ChatColor.GRAY+")");
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.noPermission);
			}
		}
		else {
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
				int x, y = 0;
				x = Integer.parseInt(xs);
				y = Integer.parseInt(ys);
				Bukkit.broadcastMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(sender.getName())+ChatColor.GRAY+" has rolled "+ChatColor.RED+randInt(x, y)+ChatColor.GRAY+"! ("+ChatColor.RED+x+ChatColor.GRAY+"-"+ChatColor.RED+y+ChatColor.GRAY+")");
			}
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