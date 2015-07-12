package fr.cartooncraft.essentials.plugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class ReplyCommand {

	CCEssentials plugin;
	
	public ReplyCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		String playerName = CCEssentialsLibrary.getConfigFile(sender).getString("lastCorrespondent", "CONSOLE");
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
