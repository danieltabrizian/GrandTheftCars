package me.lawhit.gtc.entities.cops;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CopsEvent implements Listener{
	
	Economy eco;
	public CopsEvent(Economy ec){
		this.eco = ec;
	}
	
	
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e){
		if(e.getDeathMessage().contains("Cop")){
			e.setDeathMessage(ChatColor.BOLD+e.getEntity().getName() + ChatColor.AQUA + ""+ChatColor.BOLD+" Is Busted!");
			e.setKeepLevel(false);
			e.setNewLevel(0);
			e.setNewExp(0);
			if(eco.has(e.getEntity(), 2000)){
				eco.withdrawPlayer(e.getEntity(), 2000);
			}
			
			for(Entity ent: e.getEntity().getNearbyEntities(30, 30, 30)){	
				if(ent instanceof Skeleton){
					Skeleton skel = (Skeleton) ent;
					skel.remove();
				}
			}
		}
	}
	@EventHandler
	public void entdeath(EntityDeathEvent e){
		e.getDrops().removeAll(e.getDrops());
		e.setDroppedExp(0);
	}
	
}
