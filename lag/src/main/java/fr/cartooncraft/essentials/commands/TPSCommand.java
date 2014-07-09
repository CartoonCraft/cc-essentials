package fr.cartooncraft.essentials.commands;

import main.java.com.webkonsept.minecraft.lagmeter.LagMeter;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TPSCommand {
	
	@SuppressWarnings("deprecation") // The deprecation is because the getTPS() will return a double instead of float in coming updates of LagMeter. nomatter for us.
	public TPSCommand(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD+"["+ChatColor.YELLOW+"TPS"+ChatColor.GOLD+"] "+ChatColor.GREEN+LagMeter.getInstance().getTPS());
	}

}
