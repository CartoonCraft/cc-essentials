package fr.cartooncraft.essentials.lib;

import java.util.HashMap;

public class CCEssentialsPermissionsManager {
	
	private HashMap<String, CCEssentialsPermission> permissions = new HashMap<String, CCEssentialsPermission>();
	
	public CCEssentialsPermission getPermission(String permissionName) {
		return permissions.get(permissionName);
	}
	
	public void setPermission(CCEssentialsPermission permission) {
		permissions.put(permission.getName(), permission);
	}
	
	public HashMap<String, CCEssentialsPermission> getPermissions() {
		return permissions;
	}
	
	@Override
	public String toString() {
		String r = "permissions: ";
		for(Object perm : permissions.keySet().toArray()) {
			r += permissions.get(perm).toString()+", ";
			r = r.substring(0, r.length() - 2);
		}
		return r;
	}

}
