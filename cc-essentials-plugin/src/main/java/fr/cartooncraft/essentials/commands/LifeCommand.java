package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class LifeCommand extends CCCommand {
	
	CCEssentials plugin;
	
	public LifeCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.life"))) {
			if(isPlayer(args[0])) {
				Player p = getPlayer(args[0]);
				sender.sendMessage(ChatColor.GRAY+getPlayerName(p)+" has "+ChatColor.RED+p.getHealth()+ChatColor.GRAY+" HP.");
			}
		}
	}

}