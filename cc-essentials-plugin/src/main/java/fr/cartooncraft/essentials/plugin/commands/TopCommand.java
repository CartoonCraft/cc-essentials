package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class TopCommand {
	
	CCEssentials plugin;
	public TopCommand(CCEssentials plugin2, CommandSender sender) {
		plugin = plugin2;
		if(CCEssentialsLibrary.isPlayer(sender)) {
			if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.top"))) {
				Player p = CCEssentialsLibrary.getPlayer(sender);
				p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation()), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
			}
			else {
				sender.sendMessage(CCEssentialsLibrary.noPermission);
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.senderConsole);
		}
	}

}