package fr.cartooncraft.essentials.forcespawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsForceSpawn extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info(getDescription().getName()+" v"+getDescription().getVersion()+" is loaded.");
	}
	
	public void onDisable() {
		getLogger().info(getDescription().getName()+" v"+getDescription().getVersion()+" is unloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		getLogger().info("Teleported to spawn the player "+e.getPlayer().getName());
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
	}
	
}