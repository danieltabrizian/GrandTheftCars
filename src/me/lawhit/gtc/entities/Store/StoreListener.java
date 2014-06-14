package me.lawhit.gtc.entities.Store;

import java.util.HashMap;
import java.util.Map;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.entities.cops.Cops;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitTask;

public class StoreListener implements Listener{
	
	public Map<Villager,Integer> cooldown = new HashMap<Villager,Integer>();
	BukkitTask cooldowntask;
	Main mian;

	
	Economy eco;
	public StoreListener(Economy vault, Main m){
		this.eco = vault;
		this.mian = m;

		cooldowntask = Bukkit.getScheduler().runTaskTimer(m, new Runnable(){

			@Override
			public void run() {
				for(Villager vill : cooldown.keySet()){
					if(cooldown.get(vill) <1){
						cooldown.remove(vill);
					}else{
						cooldown.put(vill, cooldown.get(vill) - 1);
					}
				}
				
			}
			
		}, 20l, 20l);
		
	}
	
	
	@EventHandler
	public void onentdamage(EntityDamageEvent e){
		if(e.getCause() == DamageCause.ENTITY_ATTACK){
			if(e.getEntity() instanceof Villager){
				Villager vill = (Villager) e.getEntity();
				if(vill.getKiller() instanceof Player){
					Player p = vill.getKiller();
					if(cooldown.containsKey(vill)){
						p.sendMessage("I dont have any money!");
						
					}else{
						p.sendMessage("Owkay i will give you my money! just dont shoot!");
						eco.depositPlayer(p, 4000);
						p.sendMessage("Here you go! i hope you die!");
						cooldown.put(vill, 1800);
						new Cops(p,3);
						
					}
					
				}
				
				
			}
		}
	}
}
