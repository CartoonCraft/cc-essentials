package fr.cartooncraft.essentials.forcespawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsSpawn extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("CC-Essentials-ForceSpawn is loaded.");
	}
	
	public void onDisable() {
		getLogger().info("CC-Essentials-ForceSpawn is unloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
	}
	
}