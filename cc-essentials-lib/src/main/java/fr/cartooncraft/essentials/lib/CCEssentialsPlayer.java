package fr.cartooncraft.essentials.lib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CCEssentialsPlayer {
	
	private Player player;
	private CCEssentialsPermissionsManager permissionsManager;
	private CCEssentialsJavaPlugin plugin;
	
	public CCEssentialsPlayer(Player p, CCEssentialsJavaPlugin plugin) {
		this.player = p;
		this.plugin = plugin;
		this.permissionsManager = plugin.getPermissionsManager();
	}
	
	public CCEssentialsPlayer(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return player;
	}
	
	public boolean hasPermission(String permission) {
		if(player.isOp())
			return true; // Unfortunately, not everybody is OP .. XD Would be too simple :(
		
		if(plugin.isUsingPermissions())
			return player.hasPermission(permission);
		else
			return permissionsManager.getPermission(permission).getIfPermissionsDisabled();
	}
	
	public ChatColor getColor() {
		if(player.isOp())
			return ChatColor.RED;
		else
			return ChatColor.WHITE;
	}
	
	public String getPlayerName() {
		return this.getColor()+player.getName();
	}
	
	public void sendPrivateMessage(CommandSender from, String message) {
		String chatMessage = ChatColor.GOLD+"["+CCEssentialsLibrary.getPlayerName(from)+ChatColor.GOLD+"->"+getPlayerName()+ChatColor.GOLD+"] "+ChatColor.RESET+message;
		from.sendMessage(chatMessage);
		player.sendMessage(chatMessage);
	}
	
	public FileConfiguration getConfigFile() {
		return CCEssentialsLibrary.getConfigFile(player);
	}
	
	public boolean setLatestCorrespondant(CommandSender sender) {
		if(CCEssentialsLibrary.isPlayer(sender)) {
			getConfigFile().set("lastCorrespondent", sender.getName());
			return true;
		}
		else
			return false;
	}
	
	public String getLatestCorrespondant() {
		return getConfigFile().getString("lastCorrespondent", null);
	}
	
}
