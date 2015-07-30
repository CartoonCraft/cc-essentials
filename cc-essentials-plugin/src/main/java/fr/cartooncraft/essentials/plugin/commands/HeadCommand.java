package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class HeadCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.head";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = false;

	public HeadCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		CCEssentialsPlayer p = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
		if(args.length == 0) {
			p.give(getHead(p.getPlayer().getName()));
			sender.sendMessage(ChatColor.GRAY+"Gave you your head.");
		}
		else {
			p.give(getHead(CCEssentialsLibrary.concatenateAllArgs(args)));
			sender.sendMessage(ChatColor.GRAY+"Gave you "+CCEssentialsLibrary.concatenateAllArgs(args)+"'s head.");
		}
	}
	
	public static ItemStack getHead(String playerName) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setOwner(playerName);
		skullMeta.setDisplayName(ChatColor.RESET + playerName + "'s head");
		skull.setItemMeta(skullMeta);
		return skull;
	}
	
	@Override
	public String getPermission() {
		return permission;
	}


	@Override
	public int getNeededArguments() {
		return neededArguments;
	}


	@Override
	public boolean canConsoleUse() {
		return canConsoleUse;
	}

}
