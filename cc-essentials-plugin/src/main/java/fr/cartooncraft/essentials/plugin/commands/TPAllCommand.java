package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;

public class TPAllCommand extends CCEssentialsCommand {
	
	private static String permission = null;
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;

	public TPAllCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}

	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label,
			String[] args) {
		Location loc = null;
		
		if(args.length == 0) {
			if(hasPermission(sender, "cc-essentials.tpall.self")) {
				if(CCEssentialsLibrary.isPlayer(sender))
					loc = CCEssentialsLibrary.getPlayer(sender).getLocation();
				else
					sender.sendMessage(senderConsole);
			}
			else
				sender.sendMessage(noPermission);
		}
		else {
			if(args.length == 3) {
				if(hasPermission(sender, "cc-essentials.tpall.coords")) {
					try {
						areDouble(args);
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED+e.getMessage());
						return;
					}
					
					Player p = null;
					if(CCEssentialsLibrary.isPlayer(sender))
						p = CCEssentialsLibrary.getPlayer(sender);
					
					try {
						loc = TPCommand.getLocation(p, args[0], args[1], args[2]);
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED+e.getMessage());
					}
				}
				else {
					if(hasPermission(sender, "cc-essentials.tpall.other")) {
						String playerName = CCEssentialsLibrary.concatenateAllArgs(args);
						if(CCEssentialsLibrary.isPlayer(playerName))
							loc = CCEssentialsLibrary.getPlayer(playerName).getLocation();
						else
							sender.sendMessage(getPlayerNotFoundSentence(playerName));
					}
					else
						sender.sendMessage(noPermission);
				}
			}
		}
		
		if(loc != null) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.teleport(loc);
			}
		}
		return;
	}
	
	public static boolean isDouble(String d) {
		try {
			Double.parseDouble(d);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static boolean areDouble(String[] d) throws Exception {
		for(String d2 : d) {
			if(!isDouble(d2)) {
				throw new Exception(TPCommand.getNotValidNumberMessage(d2));
			}
		}
		return true;
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

/*public class TPAllCommand {

	CCEssentials plugin;
	public TPAllCommand(CCEssentials plugin2, CommandSender sender, String[] args) {
		plugin = plugin2;
		if(sender.isOp() || (plugin.isUsingPermissions() && sender.hasPermission("cc-essentials.tpall"))) {
			if(args.length == 0) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					Player p = CCEssentialsLibrary.getPlayer(sender);
					for(Player p2 : Bukkit.getOnlinePlayers()) {
						if(!CCEssentialsLibrary.areSamePlayers(p, p2))
							p2.teleport(p);
					}
					sender.sendMessage(ChatColor.GRAY+"All players have been teleported to you.");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.senderConsole);
				}
			}
			if(args.length == 1) {
				if(CCEssentialsLibrary.isPlayer(args[0])) {
					Player p = CCEssentialsLibrary.getPlayer(args[0]);
					Player p2 = CCEssentialsLibrary.getPlayer(args[0]);
					if(CCEssentialsLibrary.isPlayer(sender))
						p2 = CCEssentialsLibrary.getPlayer(sender);
					for(Player p3 : Bukkit.getOnlinePlayers()) {
						if(!CCEssentialsLibrary.areSamePlayers(p, p3))
							if(!CCEssentialsLibrary.areSamePlayers(p2, p3))
								p3.teleport(p);
					}
					sender.sendMessage(ChatColor.GRAY+"All players have been teleported to "+CCEssentialsLibrary.getPlayerName(p)+ChatColor.GRAY+".");
				}
				else {
					sender.sendMessage(CCEssentialsLibrary.getPlayerNotFoundSentence(args[0]));
				}
			}
			if(args.length == 3) {
				float x = Float.parseFloat(args[0]);
				float y = Float.parseFloat(args[1]);
				float z = Float.parseFloat(args[2]);
				World world = Bukkit.getWorlds().get(0);
				if(CCEssentialsLibrary.isPlayer(sender)) {
					world = CCEssentialsLibrary.getPlayer(sender).getWorld();
				}
				Location l = new Location(world, x, y, z);
				Player p = null;
				if(CCEssentialsLibrary.isPlayer(sender)) {
					p = CCEssentialsLibrary.getPlayer(sender);
				}
				for(Player p2 : Bukkit.getOnlinePlayers()) {
					if(!CCEssentialsLibrary.areSamePlayers(p2, p))
						p2.teleport(l);
				}
				sender.sendMessage(ChatColor.GRAY+"All players have been teleported to "+ChatColor.RED+"World: "+world.getName()+ChatColor.GRAY+", "+ChatColor.RED+"X: "+x+ChatColor.GRAY+", "+ChatColor.RED+"Y: "+y+ChatColor.GRAY+", "+ChatColor.RED+"Z: "+z+ChatColor.GRAY+".");
			}
		}
		else {
			sender.sendMessage(CCEssentialsLibrary.noPermission);
		}
	}

}*/
