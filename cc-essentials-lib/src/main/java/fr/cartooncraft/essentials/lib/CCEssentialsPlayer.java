package fr.cartooncraft.essentials.lib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
	
	public void feed() {
		player.setFoodLevel(20);
		player.setExhaustion(5F);
	}
	
	public void heal() {
		feed();
		player.setHealth(player.getMaxHealth());
	}
	
	public void give(ItemStack stack) {
		player.getInventory().addItem(stack);
	}
	
	public void spawn() {
		player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
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
	
	public void setGodmode(boolean b) {
		getConfigFile().set("godmode", b);
	}
	
	public void toggleGodmode() {
		setGodmode(!getGodmode());
	}
	
	public boolean getGodmode() {
		return getConfigFile().getBoolean("godmode", false);
	}
	
}
