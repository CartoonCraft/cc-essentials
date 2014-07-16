package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class FeedAllCommand extends CCCommand {

	public FeedAllCommand(CommandSender sender) {
		if(sender.isOp()) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.setFoodLevel(20);
				p.setExhaustion(5F);
			}
			sender.sendMessage(ChatColor.GRAY+"All players have been fed.");
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
	

}
