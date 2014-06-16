package me.lawhit.gtc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
	public Message(Player p,String msg,ChatType type){
		String composed = type.getPrefix();
		if(type == ChatType.good){
			composed  = composed + ChatColor.GREEN + msg;
		}else if(type == ChatType.normal){
			composed  = composed + ChatColor.GOLD + msg;
		}else if(type == ChatType.error){
			composed  = composed + ChatColor.RED + msg;
		}else if(type == ChatType.important){
			composed  = composed + ChatColor.AQUA + "" + ChatColor.BOLD + msg;
		}
	}
	
}
