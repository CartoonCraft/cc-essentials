package fr.cartooncraft.essentials.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.plugin.commands.BroadcastCommand;
import fr.cartooncraft.essentials.plugin.commands.FeedAllCommand;
import fr.cartooncraft.essentials.plugin.commands.FeedCommand;
import fr.cartooncraft.essentials.plugin.commands.GamemodeCommand;
import fr.cartooncraft.essentials.plugin.commands.GodModeCommand;
import fr.cartooncraft.essentials.plugin.commands.HeadCommand;
import fr.cartooncraft.essentials.plugin.commands.HealAllCommand;
import fr.cartooncraft.essentials.plugin.commands.HealCommand;
import fr.cartooncraft.essentials.plugin.commands.IsInGodModeCommand;
import fr.cartooncraft.essentials.plugin.commands.KickCommand;
import fr.cartooncraft.essentials.plugin.commands.KickallCommand;
import fr.cartooncraft.essentials.plugin.commands.KickallOPCommand;
import fr.cartooncraft.essentials.plugin.commands.KillCommand;
import fr.cartooncraft.essentials.plugin.commands.LifeCommand;
import fr.cartooncraft.essentials.plugin.commands.ListCommand;
import fr.cartooncraft.essentials.plugin.commands.ReplyCommand;
import fr.cartooncraft.essentials.plugin.commands.RollCommand;
import fr.cartooncraft.essentials.plugin.commands.SpawnCommand;
import fr.cartooncraft.essentials.plugin.commands.SuicideCommand;
import fr.cartooncraft.essentials.plugin.commands.TPAllCommand;
import fr.cartooncraft.essentials.plugin.commands.TPCommand;
import fr.cartooncraft.essentials.plugin.commands.TellCommand;
import fr.cartooncraft.essentials.plugin.commands.TopCommand;
import fr.cartooncraft.essentials.plugin.commands.XPCommand;
import fr.cartooncraft.essentials.plugin.events.listeners.ChatEvent;
import fr.cartooncraft.essentials.plugin.events.listeners.DamageEvent;
import fr.cartooncraft.essentials.plugin.events.listeners.InteractEvent;
import fr.cartooncraft.essentials.plugin.events.listeners.LeaveEvent;
import fr.cartooncraft.essentials.plugin.events.listeners.LoginEvent;
import fr.cartooncraft.essentials.plugin.events.listeners.SignPlacedEvent;

public class CCEssentials extends JavaPlugin {

	private boolean usePermissions;
	private boolean enableChat;

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new ChatEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new LoginEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new LeaveEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
		Bukkit.getPluginManager().registerEvents(new InteractEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new SignPlacedEvent(this), this);
		getLogger().info(getDescription().getName()+" v"+getDescription().getVersion()+" is loaded.");
		
		// Config file
		
		ConfigManager.load(this, "config.yml");
		setUsingPermissions(ConfigManager.get("config.yml").getBoolean("usePermissions", false));
		setEnableChat(ConfigManager.get("config.yml").getBoolean("enableChat", true));
	}
	
	public void onDisable() {
		getLogger().info(getDescription().getName()+" v"+getDescription().getVersion()+" is unloaded.");
		
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
		else if(cmd.getName().equalsIgnoreCase("top"))
			new TopCommand(this, sender);
		else if(cmd.getName().equalsIgnoreCase("tp"))
			new TPCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("kill"))
			new KillCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("xp"))
			new XPCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("head"))
			new HeadCommand(this, sender, args);
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
		else if(cmd.getName().equalsIgnoreCase("gamemode"))
			new GamemodeCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("isingod"))
			new IsInGodModeCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("god"))
			new GodModeCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("roll"))
			new RollCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("tell"))
			new TellCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("r"))
			new ReplyCommand(this, sender, args);
		else if(cmd.getName().equalsIgnoreCase("life"))
			new LifeCommand(this, sender, args);
		else
			return false;
		return true;
	}

	public boolean isUsingPermissions() {
		return usePermissions;
	}

	public boolean isChatEnabled() {
		return enableChat;
	}

	public void setUsingPermissions(boolean usingPermissions) {
		this.usePermissions = usingPermissions;
	}

	public void setEnableChat(boolean enableChat) {
		this.enableChat = enableChat;
	}
	
}