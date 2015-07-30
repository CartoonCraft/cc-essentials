package fr.cartooncraft.essentials.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.goblom.bukkitlibs.config.ConfigManager;

import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsPermission;
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

public class CCEssentials extends CCEssentialsJavaPlugin {
	
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

		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.tell", true));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.list", true));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.broadcast", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.feed", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.feedall", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.heal", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.healall", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.head", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.top", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.suicide", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.kill", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.spawn", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.roll", true));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.get.self", true));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.get.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.set.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.set.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.add.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.add.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.remove.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.life.remove.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.get.self", true));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.get.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.set.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.set.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.add.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.add.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.remove.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.xp.remove.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.gamemode.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.gamemode.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.god.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.god.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.isingodmode.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.isingodmode.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.kick", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.kickall", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.kickall.op", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.teleport.self.player", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.teleport.self.coords", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.teleport.other.player", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.teleport.other.coords", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.tpall.self", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.tpall.other", false));
		permissionsManager.setPermission(new CCEssentialsPermission("cc-essentials.tpall.coords", false));
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
			new SpawnCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("list"))
			new ListCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("top"))
			new TopCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("tp"))
			new TPCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("kill"))
			new KillCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("xp"))
			new XPCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("head"))
			new HeadCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("suicide"))
			new SuicideCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("broadcast"))
			new BroadcastCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("kick"))
			new KickCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("heal"))
			new HealCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("feed"))
			new FeedCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("tpall"))
			new TPAllCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("kickall"))
			new KickallCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("healall"))
			new HealAllCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("feedall"))
			new FeedAllCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("kickallop"))
			new KickallOPCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("gamemode"))
			new GamemodeCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("godmode"))
			new GodModeCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("isingod"))
			new IsInGodModeCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("roll"))
			new RollCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("tell"))
			new TellCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("reply"))
			new ReplyCommand(this, sender, cmd, label, args);
		else if(cmd.getName().equalsIgnoreCase("life"))
			new LifeCommand(this, sender, cmd, label, args);
		else
			return false;
		return true;
	}

	public boolean isChatEnabled() {
		return enableChat;
	}

	public void setEnableChat(boolean enableChat) {
		this.enableChat = enableChat;
	}
	
}