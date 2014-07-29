package fr.cartooncraft.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.commands.BroadcastCommand;
import fr.cartooncraft.essentials.commands.FeedAllCommand;
import fr.cartooncraft.essentials.commands.FeedCommand;
import fr.cartooncraft.essentials.commands.GamemodeCommand;
import fr.cartooncraft.essentials.commands.GodModeCommand;
import fr.cartooncraft.essentials.commands.HealAllCommand;
import fr.cartooncraft.essentials.commands.HealCommand;
import fr.cartooncraft.essentials.commands.IsInGodModeCommand;
import fr.cartooncraft.essentials.commands.KickCommand;
import fr.cartooncraft.essentials.commands.KickallCommand;
import fr.cartooncraft.essentials.commands.KickallOPCommand;
import fr.cartooncraft.essentials.commands.KillCommand;
import fr.cartooncraft.essentials.commands.ListCommand;
import fr.cartooncraft.essentials.commands.RollCommand;
import fr.cartooncraft.essentials.commands.SpawnCommand;
import fr.cartooncraft.essentials.commands.SuicideCommand;
import fr.cartooncraft.essentials.commands.TPAllCommand;
import fr.cartooncraft.essentials.commands.TPCommand;
import fr.cartooncraft.essentials.commands.TellCommand;
import fr.cartooncraft.essentials.events.listeners.ChatEvent;
import fr.cartooncraft.essentials.events.listeners.DamageEvent;
import fr.cartooncraft.essentials.events.listeners.InteractEvent;
import fr.cartooncraft.essentials.events.listeners.LeaveEvent;
import fr.cartooncraft.essentials.events.listeners.LoginEvent;
import fr.cartooncraft.essentials.events.listeners.SignPlacedEvent;

public class CCEssentials extends JavaPlugin {

	private boolean usePermissions;

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
		Bukkit.getPluginManager().registerEvents(new LoginEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new LeaveEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
		Bukkit.getPluginManager().registerEvents(new InteractEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new SignPlacedEvent(this), this);
		getLogger().info("CC-Essentials is loaded.");
		ConfigManager.load(this, "config.yml");
		usePermissions = ConfigManager.get("config.yml").getBoolean("usePermissions", false);
	}
	
	public void onDisable() {
		getLogger().info("CC-Essentials is unloaded.");
		
		// Save players configs
		for(Object fileName : ConfigManager.configs.keySet().toArray()) {
			ConfigManager.save(this, (String)fileName);
			ConfigManager.configs.remove(fileName);
		}
		ConfigManager.save(this, "config.yml");
		ConfigManager.configs.remove("config.yml");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Commands
		if(cmd.getName().equalsIgnoreCase("spawn"))
			new SpawnCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("list"))
			new ListCommand(this, sender);
		else if(cmd.getName().equalsIgnoreCase("tp") || cmd.getName().equalsIgnoreCase("teleport"))
			new TPCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("kill"))
			new KillCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("suicide"))
			new SuicideCommand(this, sender);
		else if(cmd.getName().equalsIgnoreCase("broadcast"))
			new BroadcastCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("kick"))
			new KickCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("heal"))
			new HealCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("feed"))
			new FeedCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("tpall"))
			new TPAllCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("kickall"))
			new KickallCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("healall"))
			new HealAllCommand(this, sender);
		else if(cmd.getName().equalsIgnoreCase("feedall"))
			new FeedAllCommand(this, sender);
		else if(cmd.getName().equalsIgnoreCase("kickallop"))
			new KickallOPCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm"))
			new GamemodeCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("isingod") || cmd.getName().equalsIgnoreCase("isingodmode"))
			new IsInGodModeCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("god") || cmd.getName().equalsIgnoreCase("godmode"))
			new GodModeCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("roll") || cmd.getName().equalsIgnoreCase("random") || cmd.getName().equalsIgnoreCase("rand"))
			new RollCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("tell") || cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("whisp") || cmd.getName().equalsIgnoreCase("t") || cmd.getName().equalsIgnoreCase("w") || cmd.getName().equalsIgnoreCase("pm") || cmd.getName().equalsIgnoreCase("mp"))
			new TellCommand(this, sender, args);
		else {
			return false;
		}
		return true;
	}

	public boolean isUsingPermissions() {
		return usePermissions;
	}
	
}