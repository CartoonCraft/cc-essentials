package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class SpawnCommand extends CCCommand {
	
	public SpawnCommand(CommandSender sender, String[] args) {
		Location spawn = new Location(Bukkit.getWorld("world"), -685.5, 64, 327.5, 90, 0);
		if(sender.isOp()) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					p.teleport(spawn);
					p.sendMessage(ChatColor.GRAY+"You have been teleported to the spawn!");
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			else if(args.length == 1) {
				if(getPlayer(args[0]) != null) {
					Player p = getPlayer(args[0]);
					p.teleport(spawn);
					sender.sendMessage(""+ChatColor.GRAY+getPlayerName(p)+ChatColor.GRAY+" has been teleported to the spawn!");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /spawn [player]");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
	
}
