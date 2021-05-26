package net.henrylang.chatimages;

import org.bukkit.plugin.java.JavaPlugin;

import net.henrylang.chatimages.commands.CommandSetImage;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("sendimage").setExecutor(new CommandSetImage());
	}
	
	@Override
	public void onDisable() {
		
	}
}
