package fr.cartooncraft.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.CCCommand;

public class IsInGodModeCommand extends CCCommand {

	public IsInGodModeCommand(CommandSender sender, String[] args) {
		if(sender.isOp()) {
			if(args.length == 0) {
				if(isPlayer(sender)) {
					Player p = getPlayer(sender);
					boolean godMode = ConfigManager.get(p.getName()+".yml").getBoolean("godmode", false);
					if(godMode) {
						p.sendMessage(ChatColor.GRAY+"You're in godmode.");
					}
					else {
						p.sendMessage(ChatColor.GRAY+"You're not in godmode.");
					}
				}
				else {
					sender.sendMessage(senderConsole);
				}
			}
			else if(args.length == 1) {
				if(isPlayer(args[0])) {
					Player p = getPlayer(args[0]);
					boolean godMode = ConfigManager.get(p.getName()+".yml").getBoolean("godmode", false);
					if(godMode) {
						sender.sendMessage(ChatColor.GRAY+getPlayerName(p.getName())+ChatColor.GRAY+" is in godmode.");
					}
					else {
						sender.sendMessage(ChatColor.GRAY+getPlayerName(p.getName())+ChatColor.GRAY+" isn't in godmode.");
					}
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));;
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /isingodmode [player]");
			}
		}
		else {
			sender.sendMessage(noPermission);
		}
	}
}
