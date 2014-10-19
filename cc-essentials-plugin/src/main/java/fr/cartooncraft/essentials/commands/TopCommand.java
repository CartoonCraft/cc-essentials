package fr.cartooncraft.essentials.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class TopCommand extends CCCommand {
	
	CCEssentials plugin;
	public TopCommand(CCEssentials plugin2, CommandSender sender) {
		plugin = plugin2;
		if(isPlayer(sender)) {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.top"))) {
				Player p = getPlayer(sender);
				p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation())+1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
			}
			else {
				sender.sendMessage(noPermission);
			}
		}
		else {
			sender.sendMessage(senderConsole);
		}
	}

}