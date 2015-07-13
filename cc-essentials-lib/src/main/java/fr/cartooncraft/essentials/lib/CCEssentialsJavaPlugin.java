package fr.cartooncraft.essentials.lib;

import org.bukkit.plugin.java.JavaPlugin;

public class CCEssentialsJavaPlugin extends JavaPlugin {
	
	private boolean usePermissions = false;
	protected CCEssentialsPermissionsManager permissionsManager = new CCEssentialsPermissionsManager();
	
	public boolean isUsingPermissions() {
		return usePermissions;
	}
	
	protected void setUsingPermissions(boolean usePermissions) {
		this.usePermissions = usePermissions;
	}
	
	public CCEssentialsPermissionsManager getPermissionsManager() {
		return permissionsManager;
	}
	
}
