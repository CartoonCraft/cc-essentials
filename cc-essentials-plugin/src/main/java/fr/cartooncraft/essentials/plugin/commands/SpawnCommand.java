package fr.cartooncraft.essentials.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.cartooncraft.essentials.lib.CCEssentialsCommand;
import fr.cartooncraft.essentials.lib.CCEssentialsJavaPlugin;
import fr.cartooncraft.essentials.lib.CCEssentialsLibrary;
import fr.cartooncraft.essentials.lib.CCEssentialsPlayer;

public class SpawnCommand extends CCEssentialsCommand {
	
	private static String permission = "cc-essentials.spawn";
	private static int neededArguments = 0;
	private static boolean canConsoleUse = true;
	
	private CCEssentialsJavaPlugin plugin;

	public SpawnCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) {
		super(plugin, sender, cmd, label, args);
	}
	
	@Override
	public void executeCommand(CCEssentialsJavaPlugin plugin, CommandSender sender, Command cmd, String label, String[] args) { // To override
		this.plugin = plugin;
		if(args.length == 0) {
			if(CCEssentialsLibrary.isPlayer(sender)) {
				new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender)).spawn();
				sender.sendMessage(ChatColor.GRAY+"You have been teleported to the spawn!");
			}
			else {
				sender.sendMessage(senderConsole);
			}
		}
		else {
			if(canSpawnOther(sender)) {
				String playerName = CCEssentialsLibrary.concatenateAllArgs(args);
				if(CCEssentialsLibrary.isPlayer(playerName)) {
					new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(playerName)).spawn();
					sender.sendMessage(""+ChatColor.GRAY+CCEssentialsLibrary.getPlayerName(playerName)+ChatColor.GRAY+" has been teleported to the spawn!");
				}
				else {
					sender.sendMessage(getPlayerNotFoundSentence(playerName));
				}				
			}
			else {
				sender.sendMessage(noPermission);
			}
		}
	}
	
	public boolean canSpawnOther(CommandSender sender) {
		if(sender.isOp())
			return true;
		
		if(CCEssentialsLibrary.isPlayer(sender)) {
			CCEssentialsPlayer ccPlayer = new CCEssentialsPlayer(CCEssentialsLibrary.getPlayer(sender), plugin);
			return ccPlayer.hasPermission("cc-essentials.spawnother");
		}
		else if(sender.hasPermission("cc-essentials.spawnother"))
			return true;
		else
			return false;
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
