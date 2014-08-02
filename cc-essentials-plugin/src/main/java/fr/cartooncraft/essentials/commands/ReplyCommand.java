package fr.cartooncraft.essentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class ReplyCommand extends CCCommand {

	CCEssentials plugin;
	
	public ReplyCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		String playerName = ConfigManager.get(sender.getName()+".yml").getString("lastCorrespondent", "CONSOLE");
		if(!playerName.equals("CONSOLE")) {
			List<String> argsList = new ArrayList<String>();
			argsList.add(playerName);
			for(String arg : args) {
				argsList.add(arg);
			}
			plugin = plugin2;
			new TellCommand(plugin, sender, args);
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nobody sent you a message! (or console)");
		}
	}

}
