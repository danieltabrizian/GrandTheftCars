package me.lawhit.gtc.entities.cops;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CopsEvent implements Listener{
	
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e){
		if(e.getDeathMessage().contains("Cop")){
			e.setDeathMessage(ChatColor.BOLD+e.getEntity().getName() + ChatColor.AQUA + ""+ChatColor.BOLD+" Is Busted!");
			for(Entity ent: e.getEntity().getNearbyEntities(10, 10, 10)){	
				if(ent instanceof Zombie){
					Zombie zomb = (Zombie) ent;
					zomb.remove();
				}
			}
		}
	}
	
	
}
