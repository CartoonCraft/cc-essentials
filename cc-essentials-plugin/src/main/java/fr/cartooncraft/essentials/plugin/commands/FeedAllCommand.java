package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class FeedAllCommand {

	CCEssentials plugin;
	
	public FeedAllCommand(CCEssentials plugin2, CommandSender sender) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.feedall"))) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.setFoodLevel(20);
				p.setExhaustion(5F);
			}
			sender.sendMessage(ChatColor.GRAY+"All players have been fed.");
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}
	

}
