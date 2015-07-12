package fr.cartooncraft.essentials.plugin.events.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.cartooncraft.essentials.plugin.CCEssentials;
import fr.cartooncraft.essentials.plugin.events.SignInteractEvent;

public class InteractEvent implements Listener {
	
	CCEssentials plugin;
	
	public InteractEvent(CCEssentials p) {
		plugin = p;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getState().getType().equals(Material.WALL_SIGN) || e.getClickedBlock().getState().getType().equals(Material.SIGN_POST)) {
				new SignInteractEvent(e.getPlayer(), e.getAction(), e.getItem(), e.getClickedBlock(), e.getBlockFace(), plugin);
			}
		}
	}
}
