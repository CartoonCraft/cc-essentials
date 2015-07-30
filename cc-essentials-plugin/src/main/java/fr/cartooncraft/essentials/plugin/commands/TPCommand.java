package fr.cartooncraft.essentials.plugin.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class TPCommand extends CCEssentialsCommand {
	
	private static String permission = null;
	private static int neededArguments = 1;
	private static boolean canConsoleUse = true;

	public TPCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}

	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label,
			String[] args) {
		
		CCEssentialsPlayer p1 = null;
		Location loc = null;
		String action = null;
		
		// Determine action, and return if impossible (can't find player, console ..)
		
		if(args.length == 1) { // TP sender to args[0]
			if(CCEssentialsLibrary.isPlayer(sender)) {
				if(CCEssentialsLibrary.isPlayer(args[0])) {
					p1 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender), plugin);
					loc = CCEssentialsLibrary.getPlayer(args[0]).getLocation();
					action = "self.player";
				}
				else
					sender.sendMessage(getPlayerNotFoundSentence(args[0]));
			}
			else
				sender.sendMessage(senderConsole);
		}
		else if(args.length == 2) {
			if(CCEssentialsLibrary.isPlayer(args[0])) {
				if(CCEssentialsLibrary.isPlayer(args[1])) {
					p1 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(args[0]));
					loc = CCEssentialsLibrary.getPlayer(args[1]).getLocation();
					action = "other.player";
				}
				else
					sender.sendMessage(getPlayerNotFoundSentence(args[1]));
			}
			else
				sender.sendMessage(getPlayerNotFoundSentence(args[0]));
		}
		else if(args.length == 3) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				p1 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
				try {
					loc = getLocation(p1.getPlayer(), args[0], args[1], args[2]);
					action = "self.coords";
				}
				catch(Exception e) {
					sender.sendMessage(ChatColor.RED+e.getMessage());
					return;
				}
			}
			else
				sender.sendMessage(senderConsole);
		}
		else if(args.length == 4) {
			if(isWorld(args[3])) {
				if(CCEssentialsLibrary.isPlayer(sender)) {
					p1 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender));
					try {
						loc = getLocation(p1.getPlayer(), args[0], args[1], args[2], args[3]);
						action = "self.coords";
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED+e.getMessage());
						return;
					}
				}
				else
					sender.sendMessage(senderConsole);
			}
			else if(CCEssentialsLibrary.isPlayer(args[0])) {
				p1 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(args[0]));
				try {
					loc = getLocation(p1.getPlayer(), args[1], args[2], args[3]);
					action = "other.coords";
				}
				catch(Exception e) {
					sender.sendMessage(ChatColor.RED+e.getMessage());
					return;
				}
			}
			else
				sender.sendMessage(getPlayerNotFoundSentence(args[0])+"... or the world "+args[args.length - 1]+" doesn't exist.");
		}
		else if(args.length == 5) {
			if(CCEssentialsLibrary.isPlayer(args[0])) {
				if(isWorld(args[4])) {
					p1 = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(args[0]));
					try {
						loc = getLocation(p1.getPlayer(), args[1], args[2], args[3], args[4]);
						action = "other.coords";
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED+e.getMessage());
					}
				}
				else
					sender.sendMessage("The world "+args[4]+" doesn't exist.");
			}
			else
				sender.sendMessage(getPlayerNotFoundSentence(args[0]));
		}
		else {
			sender.sendMessage(getUsage());
		}
		
		// Now, we check if conditions to execute the command are met. If they are, we do the tp.
		
		if(action != null && loc != null) {
			if(hasPermission(sender, "cc-essentials.teleport."+action)) {
				p1.getPlayer().teleport(loc);
			}
			else
				sender.sendMessage(noPermission);
		}
		return;
	}
	
	public static Location getLocation(Player p, String x, String y, String z, String w) throws Exception {
		HashMap<String, String> args = new HashMap<String, String>();
		args.put("x", x);
		args.put("y", y);
		args.put("z", z);
		args.put("w", w);
		HashMap<String, Double> coords = new HashMap<String, Double>();
		HashMap<String, Double> playerLocation = new HashMap<String, Double>();
		playerLocation.put("x", p.getLocation().getX());
		playerLocation.put("y", p.getLocation().getY());
		playerLocation.put("z", p.getLocation().getZ());
		
		for(String arg : args.keySet().toArray(new String[0])) {
			if(!arg.equals("w")) {
				if(arg.startsWith("~")) {
					try {
						coords.put(arg, playerLocation.get(arg)+Double.parseDouble(args.get(arg).substring(1)));
					}
					catch(NumberFormatException e) {
						throw new Exception(getNotValidNumberMessage(args.get(arg)));
					}
				}
				else {
					try {
						coords.put(arg, Double.parseDouble(args.get(arg)));
					}
					catch(NumberFormatException e) {
						throw new Exception(getNotValidNumberMessage(args.get(arg)));
					}
				}
			}
		}
		if(!isWorld(w))
			throw new Exception("The world "+w+" doesn't exist.");
		
		return new Location(Bukkit.getWorld(args.get("w")), coords.get("x"), coords.get("y"), coords.get("z"));
	}
	
	public static Location getLocation(Player p, String x, String y, String z) throws Exception {
		try {
			if(p == null)
				return getLocation(p, x, y, z, Bukkit.getWorlds().get(0).getName());
			else
				return getLocation(p, x, y, z, p.getWorld().getName());
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static boolean isWorld(String world) {
		return (Bukkit.getWorld(world) != null);
	}
	
	public static World getWorld(String world) {
		return Bukkit.getWorld(world);
	}
	
	public static String getNotValidNumberMessage() {
		return "The numbers you entered are not valid.";
	}
	
	public static String getNotValidNumberMessage(String number) {
		return number+" is not a valid number.";
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