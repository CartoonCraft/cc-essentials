package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class HealAllCommand extends CCCommand {

	public HealAllCommand(CommandSender sender) {
		if(sender.isOp()) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.setHealth(20);
				p.setFoodLevel(20);
				p.setExhaustion(5F);
			}
			sender.sendMessage(ChatColor.GRAY+"All players have been healed.");
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
	

}
