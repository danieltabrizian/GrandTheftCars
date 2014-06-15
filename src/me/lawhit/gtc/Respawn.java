package me.lawhit.gtc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.shampaggon.crackshot.CSUtility;

public class Respawn implements Listener{
	Main main;
	CSUtility csu;
	public Respawn(Main ma,CSUtility csu){
		this.csu = csu;
		main = ma;
	}
	@EventHandler
	public void playerdeath(PlayerDeathEvent e){
		e.setDroppedExp(0);
		e.getDrops().removeAll(e.getDrops());
		
		
	}
	
	
	
	@EventHandler
	public void playerrespawn(final PlayerRespawnEvent e){
	
		Bukkit.getScheduler().runTaskLaterAsynchronously(main, new Runnable(){

			@Override
			public void run() {
				
				int stacker = 0;
				
				for(ItemStack is : e.getPlayer().getInventory().getContents()){
					if(is != null){
						e.getPlayer().getInventory().remove(is);
						if(csu.getWeaponTitle(is) != null){
							
							e.getPlayer().getInventory().setItem(stacker,csu.generateWeapon(csu.getWeaponTitle(is)));
							e.getPlayer().sendMessage(ChatColor.YELLOW + "Reloaded: " +csu.getWeaponTitle(is) );
						}else{
							e.getPlayer().getInventory().setItem(stacker,is);
						}
					}
					stacker++;
				}
				
				stacker = 0;
			}
			
		}, 40l);
	}
	
}
