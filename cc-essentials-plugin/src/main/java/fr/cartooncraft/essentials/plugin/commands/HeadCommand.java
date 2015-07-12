package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.plugin.CCEssentials;

public class HeadCommand {
	
	CCEssentials plugin;
	public HeadCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(CCEssentialsLibrary.isPlayer(sender)) {
			Player p = CCEssentialsLibrary.getPlayer(sender);
			if(args.length == 0) {
				if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.head.self"))) {
					String name = p.getName();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
					SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
					skullMeta.setOwner(name);
					skullMeta.setDisplayName(ChatColor.RESET + name + "'s head");
					skull.setItemMeta(skullMeta);
					p.getInventory().addItem(skull);
					sender.sendMessage(ChatColor.GRAY+"Gived the "+CCEssentialsLibrary.getPlayerName(name)+ChatColor.GRAY+"'s head.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.noPermission);
				}
			}
			else if(args.length > 0) {
				if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.head.other"))) {
					int i = 1;
					String name = args[0];
					while(args.length > i) {
						name = name+" "+args[i];
						i++;
					}
					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
					SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
					skullMeta.setOwner(name);
					skullMeta.setDisplayName(ChatColor.RESET + name + "'s head");
					skull.setItemMeta(skullMeta);
					p.getInventory().addItem(skull);
					sender.sendMessage(ChatColor.GRAY+"Gived the "+CCEssentialsLibrary.getPlayerName(name)+ChatColor.GRAY+"'s head.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.noPermission);
				}
			}
			else {
				sender.sendMessage(ChatColor.RED+"Nope! Usage: /heal <player>");
			}
		}
	}

}