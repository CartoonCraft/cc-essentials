package fr.cartooncraft.essentials.lib;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.goblom.bukkitlibs.config.ConfigManager;

public class CCEssentialsLibrary extends JavaPlugin {

	public static String noPermission = ""+ChatColor.RESET+ChatColor.RED+"Sorry, you're not allowed to do this.";
	public static String senderConsole = ""+ChatColor.RESET+ChatColor.RED+"Sorry, you're a console, you can't do this!";
	
	public static boolean isPlayer(CommandSender sender) {
		return(sender instanceof Player);
	}
	
	public static boolean isPlayer(String p) {
		return(Bukkit.getPlayer(p) != null);
	}
	
	public static boolean isPlayer(UUID u) {
		return(Bukkit.getPlayer(u) != null);
	}
	
	public static boolean isPlayer(Player p) {
		return(p != null);
	}
	
	public static Player getPlayer(CommandSender sender) {
		if(isPlayer(sender)) {
			return (Player)sender;
		}
		return null;
	}
	
	public static Player getPlayer(String sender) {
		if(Bukkit.getPlayer(sender) != null) {
			return Bukkit.getPlayer(sender);
		}
		else {
			return null;
		}
	}
	
	public static String getConfigFileName(Player p) {
		return p.getUniqueId()+".yml";
	}
	
	public static FileConfiguration getConfigFile(CommandSender s) {
		if(isPlayer(s))
			return getConfigFile(getPlayer(s));
		else
			return getConfigFile("CONSOLE.yml");
	}
	
	public static FileConfiguration getConfigFile(Player p) {
		return ConfigManager.get(getConfigFileName(p));
	}
	
	public static FileConfiguration getConfigFile(String s) {
		return ConfigManager.get(s);
	}
	
	public static boolean areSamePlayers(Player p1, Player p2) {
		return(p1.getUniqueId() == p2.getUniqueId());
	}
	
	public static String getPlayerNotFoundSentence(String name) {
		return ChatColor.RED+"Can't find "+name+". Is he offline?";
	}
	
	public static String getPlayerName(Player p) {
		String name = "";
		if(p.isOp())
			name += ChatColor.RED;
		name += p.getName();
		return name;
	}
	
	public static String getPlayerName(String playerName) {
		
		if(isPlayer(playerName)) {
			return getPlayerName(getPlayer(playerName));
		}
		else {
			if(playerName == "CONSOLE") {
				return ChatColor.RED+"CONSOLE";
			}
			else {
				return ChatColor.WHITE+playerName;
			}
		}
	}
	
	// Bukkit plugin stuff
	
	public void onEnable() {
		getLogger().info(getDescription().getName()+" version "+getDescription().getVersion()+" is loaded.");
	}
	
	public void onDisable() {
		getLogger().info(getDescription().getName()+" version "+getDescription().getVersion()+" is unloaded.");
	}
	
	
	
}
