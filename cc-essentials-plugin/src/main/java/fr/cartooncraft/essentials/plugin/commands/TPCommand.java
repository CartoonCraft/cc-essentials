package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class TPCommand {

	CCEssentials plugin;
	public TPCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(args.length == 1) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				Player p = CCEssentialsLibrary.getPlayer(sender);
				if(sender.isOp() || (plugin.isUsingPermissions() && (sender.hasPermission("cc-essentials.tpto") || sender.hasPermission("cc-essentials.tp")))) {
					if(CCEssentialsLibrary.getPlayer(args[0]) == null) {
						sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
					}
					else {
						Player p2 = CCEssentialsLibrary.getPlayer(args[0]);
						p.teleport(p2.getLocation());
						sender.sendMessage(ChatColor.GRAY+"You have been teleported to "+CCEssentialsLibrary.getPlayerName(p2)+ChatColor.GRAY+".");
					}
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.noPermission);
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.senderConsole);
			}
		}
		else if(args.length == 2) {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.tp"))) {
				Player p1 = null;
				Player p2 = null;
				if(CCEssentialsLibrary.getPlayer(args[0]) == null) {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
				else if(CCEssentialsLibrary.getPlayer(args[1]) == null) {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[1]));
				}
				else {
					p1 = CCEssentialsLibrary.getPlayer(args[0]);
					p2 = CCEssentialsLibrary.getPlayer(args[1]);
					p1.teleport(p2);
					sender.sendMessage(CCEssentialsLibrary.getPlayerName(p1)+ChatColor.GRAY+" has been teleported to "+CCEssentialsLibrary.getPlayerName(p2)+ChatColor.GRAY+".");
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.noPermission);
			}
		}
		else if(args.length == 3) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				Player p = CCEssentialsLibrary.getPlayer(sender);
				if(sender.isOp() || (plugin.isUsingPermissions() && (sender.hasPermission("cc-essentials.tp.loc") || sender.hasPermission("cc-essentials.tp")))) {
					float x = Float.parseFloat(args[0]);
					float y = Float.parseFloat(args[1]);
					float z = Float.parseFloat(args[2]);
					Location l = new Location(p.getWorld(), x, y, z);
					l.setPitch(p.getLocation().getPitch());
					l.setYaw(p.getLocation().getYaw());
					p.teleport(l);
					sender.sendMessage(ChatColor.GRAY+"You have been teleported to "+ChatColor.RED+"World: "+p.getWorld().getName()+ChatColor.GRAY+", "+ChatColor.RED+"X: "+x+ChatColor.GRAY+", "+ChatColor.RED+"Y: "+y+ChatColor.GRAY+", "+ChatColor.RED+"Z: "+z+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.noPermission);
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.senderConsole);
			}
		}
		else if(args.length == 4) {
			if(CCEssentialsLibrary.isPlayer(args[0])) {
				Player p = CCEssentialsLibrary.getPlayer(args[0]);
				if(sender.isOp() || (plugin.isUsingPermissions() && (sender.hasPermission("cc-essentials.tp") || sender.hasPermission("cc-essentials.tp.loc")))) {
					float x = Float.parseFloat(args[1]);
					float y = Float.parseFloat(args[2]);
					float z = Float.parseFloat(args[3]);
					Location l = new Location(p.getWorld(), x, y, z);
					l.setPitch(p.getLocation().getPitch());
					l.setYaw(p.getLocation().getYaw());
					p.teleport(l);
					sender.sendMessage(ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+" have been teleported to "+ChatColor.RED+"World: "+p.getWorld().getName()+ChatColor.GRAY+", "+ChatColor.RED+"X: "+x+ChatColor.GRAY+", "+ChatColor.RED+"Y: "+y+ChatColor.GRAY+", "+ChatColor.RED+"Z: "+z+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.noPermission);
				}
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
			}
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nope! Usage: /tp <player>");
		}
	}

}
