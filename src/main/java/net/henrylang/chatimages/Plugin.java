package net.henrylang.chatimages;

import org.bukkit.plugin.java.JavaPlugin;

import net.henrylang.chatimages.commands.CommandSendImage;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("sendimage").setExecutor(new CommandSendImage());
	}
	
	@Override
	public void onDisable() {
		
	}
}
