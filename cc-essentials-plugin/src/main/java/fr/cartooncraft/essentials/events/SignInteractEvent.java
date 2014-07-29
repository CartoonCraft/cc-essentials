package fr.cartooncraft.essentials.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.cartooncraft.essentials.CCEssentials;

public class SignInteractEvent extends PlayerInteractEvent {
	
	CCEssentials plugin;

	public SignInteractEvent(Player who, Action action, ItemStack item,
			Block clickedBlock, BlockFace clickedFace, CCEssentials p) {
		super(who, action, item, clickedBlock, clickedFace);
		plugin = p;
		if(getSign().getLine(0).equals(ChatColor.BLUE+"[Free]")) {
			if(getPlayer().isOp() || (plugin.isUsingPermissions() && getPlayer().hasPermission("cc-essentials.signs.free.use"))) {
				Inventory inv;
				inv = Bukkit.getServer().createInventory(getPlayer(), 27, "Free items!");
				//inv.all(new ItemStack(Material.valueOf(getSign().getLine(1)), 64, Short.parseShort(getSign().getLine(2))));
				for(int i = 0; i < 27; i++) {
					inv.addItem(new ItemStack(Material.valueOf(getSign().getLine(1)), 64, Short.parseShort(getSign().getLine(2))));
				}
				getPlayer().openInventory(inv);
			}
			else {
				getPlayer().sendMessage(ChatColor.RED+"Sorry, you're not authorized to do this.");
			}
		}
		else if(getSign().getLine(0).equals(ChatColor.BLUE+"[Void]")) {
			if(getPlayer().isOp() || (plugin.isUsingPermissions() && getPlayer().hasPermission("cc-essentials.signs.void.use"))) {
				Inventory inv;
				inv = Bukkit.getServer().createInventory(getPlayer(), 27, "Void!");
				getPlayer().openInventory(inv);
			}
			else {
				getPlayer().sendMessage(ChatColor.RED+"Sorry, you're not authorized to do this.");
			}
		}
		else if(getSign().getLine(0).equals(ChatColor.BLUE+"[Heal]")) {
			if(getPlayer().isOp() || (plugin.isUsingPermissions() && getPlayer().hasPermission("cc-essentials.signs.heal.use"))) {
				Player player = getPlayer();
				player.setHealth(20);
				player.setFoodLevel(20);
				player.setExhaustion(5F);
				player.sendMessage(ChatColor.GRAY+"You have been healed.");
			}
			else {
				getPlayer().sendMessage(ChatColor.RED+"Sorry, you're not authorized to do this.");
			}
		}
	}
	
	public Sign getSign() {
		return (Sign)this.getClickedBlock().getState();
	}
	
}