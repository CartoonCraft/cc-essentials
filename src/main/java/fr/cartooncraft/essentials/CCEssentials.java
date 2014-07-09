package fr.cartooncraft.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import fr.cartooncraft.essentials.commands.BroadcastCommand;
import fr.cartooncraft.essentials.commands.FeedCommand;
import fr.cartooncraft.essentials.commands.HealCommand;
import fr.cartooncraft.essentials.commands.KickCommand;
import fr.cartooncraft.essentials.commands.KickallCommand;
import fr.cartooncraft.essentials.commands.KickallOPCommand;
import fr.cartooncraft.essentials.commands.KillCommand;
import fr.cartooncraft.essentials.commands.ListCommand;
import fr.cartooncraft.essentials.commands.SpawnCommand;
import fr.cartooncraft.essentials.commands.TPAllCommand;
import fr.cartooncraft.essentials.commands.TPCommand;
import fr.cartooncraft.essentials.commands.TellCommand;
import fr.cartooncraft.essentials.events.ChatEvent;

public class CCEssentials extends JavaPlugin {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
		getLogger().info("CC-Essentials is loaded.");
	}
	
	public void onDisable() {
		getLogger().info("CC-Essentials is unloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Commands
		if(cmd.getName().equalsIgnoreCase("spawn"))
			new SpawnCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("list"))
			new ListCommand(sender);
		else if(cmd.getName().equalsIgnoreCase("tp") || cmd.getName().equalsIgnoreCase("teleport"))
			new TPCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("kill"))
			new KillCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("broadcast"))
			new BroadcastCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("kick"))
			new KickCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("heal"))
			new HealCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("feed"))
			new FeedCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("tpall"))
			new TPAllCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("kickall"))
			new KickallCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("kickallop"))
			new KickallOPCommand(sender, args);
		else if(cmd.getName().equalsIgnoreCase("tell") || cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("whisp") || cmd.getName().equalsIgnoreCase("t") || cmd.getName().equalsIgnoreCase("w") || cmd.getName().equalsIgnoreCase("pm") || cmd.getName().equalsIgnoreCase("mp"))
			new TellCommand(sender, args);
		else {
			return false;
		}
		return true;
	}
	
}