package me.lawhit.gtc.entities;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class StoreListener implements Listener{

	
	Economy eco;
	public StoreListener(Economy vault){
		this.eco = vault;
	}
	
	
	@EventHandler
	public void onentdamage(EntityDamageEvent e){
		if(e.getCause() == DamageCause.ENTITY_ATTACK){
			if(e.getEntity() instanceof Villager){
				Villager vill = (Villager) e.getEntity();
				if(vill.getKiller() instanceof Player){
					Player p = vill.getKiller();
					p.sendMessage("Owkay i will give you my money! just dont shoot!");
					eco.depositPlayer(p, 4000);
					p.sendMessage("Here you go! i hope you die!");
					new Cops(p,3);
				}
				
				
			}
		}
	}
}
