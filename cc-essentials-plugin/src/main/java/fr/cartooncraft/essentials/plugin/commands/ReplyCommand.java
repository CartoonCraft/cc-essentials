package fr.cartooncraft.essentials.plugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
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
			String[] tellArgs = new String[argsList.size()];
			int i = 0;
			for(Object arg : argsList.toArray()) {
				tellArgs[i] = (String)arg;
				i++;
			}
			new TellCommand(plugin, sender, tellArgs);
		}
		else {
			sender.sendMessage(ChatColor.RED+"Nobody sent you a message! (or console)");
		}
	}

}
