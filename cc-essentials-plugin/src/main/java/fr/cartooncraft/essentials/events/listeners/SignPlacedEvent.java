package fr.cartooncraft.essentials.events.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;

public class SignPlacedEvent extends CCCommand implements Listener {

	CCEssentials plugin;
	
	public SignPlacedEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onSignPlaced(SignChangeEvent e) {
		Player p = e.getPlayer();
		if(e.getLine(0) == "[Free]") {
			if(p.isOp() || (plugin.isUsingPermissions() && p.hasPermission("cc-essentials.signs.free.place"))) {
				if(Material.matchMaterial(e.getLine(1)) != null) {
					// Setting metadata if not set
					try {
						Integer.parseInt(e.getLine(2));
					}
					catch(Exception ex) {
						e.setLine(2, "0");
					}
					
					// Creating the sign
					e.setLine(0, ChatColor.BLUE+"[Free]");
					e.setLine(1, Material.getMaterial(e.getLine(1)).toString().toUpperCase());
				}
				else {
					p.sendMessage("No matching item/block found.");
					e.setCancelled(true);
				}
			}
			else {
				p.sendMessage(noPermission);
				e.setCancelled(true);
			}
		}
	}
}
