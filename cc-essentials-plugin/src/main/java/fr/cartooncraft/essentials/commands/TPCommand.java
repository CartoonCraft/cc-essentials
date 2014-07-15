package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class TPCommand extends CCCommand {

	public TPCommand(CommandSender sender, String[] args) {
		if(args.length == 1) {
			if(isPlayer(sender)) {
				Player p = getPlayer(sender);
				if(p.isOp()) {
					if(Bukkit.getPlayer(args[0]) == null) {
						sender.sendMessage(getPlayerNotFoundSentence(args[0]));
					}
					else {
						Player p2 = Bukkit.getPlayer(args[0]);
						p.teleport(p2.getLocation());
						sender.sendMessage(ChatColor.GRAY+"You have been teleported to "+getPlayerName(p2)+ChatColor.GRAY+".");
					}
				}
				else {
					sender.sendMessage(noPermission);
				}
			}
			else {
				sender.sendMessage(senderConsole);
			}
		}
		else if(args.length == 2) {
			if(sender.isOp()) {
				Player p1 = null;
				Player p2 = null;
				if(Bukkit.getPlayer(args[0]) == null) {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
				else if(Bukkit.getPlayer(args[1]) == null) {
					sender.sendMessage(getPlayerNotFoundSentence(args[1]));
				}
				else {
					p1 = Bukkit.getPlayer(args[0]);
					p2 = Bukkit.getPlayer(args[1]);
					p1.teleport(p2);
					sender.sendMessage(getPlayerName(p1)+ChatColor.GRAY+" has been teleported to "+getPlayerName(p2)+ChatColor.GRAY+".");
				}
			}
			else {
				sender.sendMessage(noPermission);
			}
		}
		else if(args.length == 3) {
			if(isPlayer(sender)) {
				Player p = getPlayer(sender);
				if(p.isOp()) {
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
					sender.sendMessage(noPermission);
				}
			}
			else {
				sender.sendMessage(senderConsole);
			}
		}
		else if(args.length == 4) {
			if(Bukkit.getPlayer(args[0]) != null) {
				Player p = getPlayer(sender);
				if(p.isOp()) {
					float x = Float.parseFloat(args[1]);
					float y = Float.parseFloat(args[2]);
					float z = Float.parseFloat(args[3]);
					Location l = new Location(p.getWorld(), x, y, z);
					l.setPitch(p.getLocation().getPitch());
					l.setYaw(p.getLocation().getYaw());
					p.teleport(l);
					sender.sendMessage(ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" have been teleported to "+ChatColor.RED+"World: "+p.getWorld().getName()+ChatColor.GRAY+", "+ChatColor.RED+"X: "+x+ChatColor.GRAY+", "+ChatColor.RED+"Y: "+y+ChatColor.GRAY+", "+ChatColor.RED+"Z: "+z+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(noPermission);
				}
			}
			else {
				sender.sendMessage(getPlayerNotFoundSentence(args[0]));
			}
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nope! Usage: /tp <player>");
		}
	}

}
