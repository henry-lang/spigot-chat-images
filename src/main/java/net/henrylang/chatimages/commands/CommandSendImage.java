package net.henrylang.chatimages.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class CommandSendImage implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (args.length != 1) return false;
		
		try {
			BufferedImage img = fetchImage(new URL(args[0]));
			String formatted = formatChatImage(img);
			sender.sendMessage(formatted);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static BufferedImage fetchImage(URL url) throws IOException {
		String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
		
        URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", USER_AGENT);

        InputStream inputStream = con.getInputStream();
        BufferedImage imageData = ImageIO.read(inputStream);
        
		inputStream.close();
        
        return imageData;
	}
	
	public static String formatChatImage(BufferedImage img) {
		Raster rawData = img.getRaster();
		StringBuilder builder = new StringBuilder();
		for(int x = 0; x < 100; x++) {
			for(int y = 0; y < 100; y++) {
				int[] samples = new int[4];
				samples = rawData.getPixel(x, y, samples);
				builder.append(ChatColor.of(new Color(samples[0], samples[1], samples[2], samples[3])));
				builder.append('⬛');
			}
			builder.append('\n');
		}
		
		return builder.toString();
	}
}
