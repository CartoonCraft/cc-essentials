package fr.cartooncraft.essentials.events.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.cartooncraft.essentials.CCCommand;
import fr.cartooncraft.essentials.CCEssentials;
import fr.cartooncraft.essentials.events.SignInteractEvent;

public class InteractEvent extends CCCommand implements Listener {
	
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
