package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class TPAllCommand extends CCCommand {

	public TPAllCommand(CommandSender sender, String[] args) {
		if(sender.isOp()) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					for(Player p2 : Bukkit.getOnlinePlayers()) {
						if(!areSamePlayers(p, p2))
							p2.teleport(p);
					}
					sender.sendMessage(ChatColor.GRAY+"All players have been teleported to you.");
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			if(args.length == 1) {
				if(isPlayer(args[0])) {
					Player p = getPlayer(args[0]);
					Player p2 = getPlayer(args[0]);
					if(isPlayer(sender))
						p2 = getPlayer(sender);
					for(Player p3 : Bukkit.getOnlinePlayers()) {
						if(!areSamePlayers(p, p3))
							if(!areSamePlayers(p2, p3))
								p3.teleport(p);
					}
					sender.sendMessage(ChatColor.GRAY+"All players have been teleported to "+getPlayerName(p)+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			if(args.length == 3) {
				float x = Float.parseFloat(args[0]);
				float y = Float.parseFloat(args[1]);
				float z = Float.parseFloat(args[2]);
				World world = Bukkit.getWorlds().get(0);
				if(isPlayer(sender)) {
					world = getPlayer(sender).getWorld();
				}
				Location l = new Location(world, x, y, z);
				Player p = null;
				if(isPlayer(sender)) {
					p = getPlayer(sender);
				}
				for(Player p2 : Bukkit.getOnlinePlayers()) {
					if(!areSamePlayers(p2, p))
						p2.teleport(l);
				}
				sender.sendMessage(ChatColor.GRAY+"All players have been teleported to "+ChatColor.RED+"World: "+world.getName()+ChatColor.GRAY+", "+ChatColor.RED+"X: "+x+ChatColor.GRAY+", "+ChatColor.RED+"Y: "+y+ChatColor.GRAY+", "+ChatColor.RED+"Z: "+z+ChatColor.GRAY+".");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}

}
