package fr.cartooncraft.essentials.plugin.events.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class SignPlacedEvent implements Listener {

	CCEssentials plugin;
	
	public SignPlacedEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onSignPlaced(SignChangeEvent e) {
		Player p = e.getPlayer();
		if(e.getLine(0).equalsIgnoreCase("[Free]")) {
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
					p.sendMessage(ChatColor.BLUE+"[Free]"+ChatColor.GRAY+" sign successfully created.");
				}
				else {
					p.sendMessage(ChatColor.RED+"No matching item/block found.");
					e.setCancelled(true);
				}
			}
			else {
				p.sendMessage(CCEssentialsLibrary.noPermission);
				e.setCancelled(true);
			}
		}
		

		else if(e.getLine(0).equalsIgnoreCase("[Void]")) {
			if(p.isOp() || (plugin.isUsingPermissions() && p.hasPermission("cc-essentials.signs.void.place"))) {				
				// Creating the sign
				e.setLine(0, ChatColor.BLUE+"[Void]");
				p.sendMessage(ChatColor.BLUE+"[Void]"+ChatColor.GRAY+" sign successfully created.");
			}
			else {
				p.sendMessage(CCEssentialsLibrary.noPermission);
				e.setCancelled(true);
			}
		}

		
		else if(e.getLine(0).equalsIgnoreCase("[Heal]")) {
			if(p.isOp() || (plugin.isUsingPermissions() && p.hasPermission("cc-essentials.signs.heal.place"))) {				
				// Creating the sign
				e.setLine(0, ChatColor.BLUE+"[Heal]");
				p.sendMessage(ChatColor.BLUE+"[Heal]"+ChatColor.GRAY+" sign successfully created.");
			}
			else {
				p.sendMessage(CCEssentialsLibrary.noPermission);
				e.setCancelled(true);
			}
		}

		
		else if(e.getLine(0).equalsIgnoreCase("[Feed]")) {
			if(p.isOp() || (plugin.isUsingPermissions() && p.hasPermission("cc-essentials.signs.feed.place"))) {				
				// Creating the sign
				e.setLine(0, ChatColor.BLUE+"[Feed]");
				p.sendMessage(ChatColor.BLUE+"[Feed]"+ChatColor.GRAY+" sign successfully created.");
			}
			else {
				p.sendMessage(CCEssentialsLibrary.noPermission);
				e.setCancelled(true);
			}
		}

		
		else if(e.getLine(0).equalsIgnoreCase("[Spawn]")) {
			if(p.isOp() || (plugin.isUsingPermissions() && p.hasPermission("cc-essentials.signs.spawn.place"))) {				
				// Creating the sign
				e.setLine(0, ChatColor.BLUE+"[Spawn]");
				p.sendMessage(ChatColor.BLUE+"[Spawn]"+ChatColor.GRAY+" sign successfully created.");
			}
			else {
				p.sendMessage(CCEssentialsLibrary.noPermission);
				e.setCancelled(true);
			}
		}
	}
}
