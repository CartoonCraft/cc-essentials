package fr.cartooncraft.essentials.lag;

import main.java.com.webkonsept.minecraft.lagmeter.LagMeter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import fr.cartooncraft.essentials.lag.commands.TPSCommand;
import fr.cartooncraft.essentials.lag.listeners.LagListener;

public class EssentialsLag extends JavaPlugin {
	
	private int lagListenerID;
	
	public void onEnable() {
		this.lagListenerID = LagMeter.getInstance().registerSyncLagListener(new LagListener(this));
		getLogger().info("CC-Essentials-Lag is loaded.");
	}
	
	public void onDisable() {
		LagMeter.getInstance().cancelSyncLagListener(this.lagListenerID);
		getLogger().info("CC-Essentials-Lag is unloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Commands
		if(cmd.getName().equalsIgnoreCase("tps"))
			new TPSCommand(sender);
		else {
			return false;
		}
		return true;
	}
	
}