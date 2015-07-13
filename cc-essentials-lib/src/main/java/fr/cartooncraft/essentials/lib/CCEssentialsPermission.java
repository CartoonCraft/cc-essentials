package fr.cartooncraft.essentials.lib;

public class CCEssentialsPermission {
	
	private String name;
	private boolean ifPermissionsDisabled;
	
	public CCEssentialsPermission(String name, boolean ifPermissionsDisabled) {
		this.name = name;
		this.ifPermissionsDisabled = ifPermissionsDisabled;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getIfPermissionsDisabled() {
		return ifPermissionsDisabled;
	}
	
	public void setIfPermissionsDisabled(boolean ifPermissionsDisabled) {
		this.ifPermissionsDisabled = ifPermissionsDisabled;
	}
	
	@Override
	public String toString() {
		return "CCEssentialsPermission: name: "+name+", ifPermissionsDisabled: "+ifPermissionsDisabled;
	}

}
