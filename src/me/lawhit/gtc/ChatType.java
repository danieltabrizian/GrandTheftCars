package me.lawhit.gtc;

import org.bukkit.ChatColor;

public enum ChatType {
	good,error,normal,important;

public String getPrefix(){
	return ChatColor.DARK_AQUA + "[" + ChatColor.RED+  "GTC" + ChatColor.DARK_AQUA + "] ";
	
}
}
