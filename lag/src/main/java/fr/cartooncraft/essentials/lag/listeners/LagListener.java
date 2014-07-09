package fr.cartooncraft.essentials.lag.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import main.java.com.webkonsept.minecraft.lagmeter.events.HighLagEvent;
import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.lag.EssentialsLag;

public class LagListener extends CCCommand implements main.java.com.webkonsept.minecraft.lagmeter.listeners.LagListener {
	
	EssentialsLag plugin;
	
	public LagListener(EssentialsLag plugin2) {
		plugin = plugin2;
	}

	@Override
	public void onHighLagEvent(HighLagEvent e) {
		Bukkit.broadcastMessage(""+ChatColor.BOLD+ChatColor.RED+"Hmmm... lag! "+e.getCurrentTPS()+" TPS");		
	}
	
}