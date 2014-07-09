package fr.cartooncraft.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.CCCommand;

public class FeedCommand extends CCCommand {

	public FeedCommand(CommandSender sender, String[] args) {
		if(sender.isOp()) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					p.setFoodLevel(20);
					p.setExhaustion(5F);
					sender.sendMessage(ChatColor.GRAY+"You have been fed.");
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			if(args.length == 1) {
				if(Bukkit.getPlayer(args[0]) != null) {
					Player p = Bukkit.getPlayer(args[0]);
					p.setFoodLevel(20);
					p.setExhaustion(5F);
					sender.sendMessage(getPlayerName(p)+ChatColor.GRAY+" has been fed.");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /heal [player]");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
	

}
