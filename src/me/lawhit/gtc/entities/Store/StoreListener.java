package me.lawhit.gtc.entities.Store;

import java.util.HashMap;
import java.util.Map;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;
import me.lawhit.gtc.entities.cops.Cops;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

public class StoreListener implements Listener{
	
	public Map<Villager,Integer> cooldown = new HashMap<Villager,Integer>();
	public Map<Villager,Integer> civilian = new HashMap<Villager,Integer>();
	BukkitTask cooldowntask;
	Main mian;
	MyConfigManager manager;
	MyConfig Storecmd;
	
	Stores store;
	
	Economy eco;
	public StoreListener(Economy vault, Main m,Stores sto){
		this.eco = vault;
		this.mian = m;
		this.store = sto;

		
		manager = new MyConfigManager(m);
		Storecmd = manager.getNewConfig("StoreCommands.yml");
		Storecmd.saveConfig();
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
				for(Villager vill : civilian.keySet()){
					if(civilian.get(vill) < 1){
						vill.remove();
						
					}else{
						civilian.put(vill, civilian.get(vill) -1);
					}
				}
				
			}
			
		}, 20l, 20l);
		
	}
	//random npc spawns didnt trun out that well
	/*
	@EventHandler
	public void creaturespawn(CreatureSpawnEvent e){
		if(e.getSpawnReason() == SpawnReason.NATURAL){
			Random r = new Random();
			if(e.getEntity().getNearbyEntities(5, 5, 5).size() == 0){
				if(e.getEntity().getWorld().getTime() < 16000){
					Villager vill = (Villager) e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.VILLAGER);
					vill.setAdult();
					vill.setRemoveWhenFarAway(true);
					vill.setCustomName(ChatColor.RED + ""+ChatColor.BOLD + "Civilian!");
					vill.setBreed(false);
					e.getEntity().remove();
					civilian.put(vill, 30);
				}else{
					int rand = r.nextInt(20);
					if(rand == 1){
						Villager vill = (Villager) e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.VILLAGER);
						vill.setAdult();
						vill.setRemoveWhenFarAway(true);
						vill.setCustomName(ChatColor.RED + ""+ChatColor.BOLD + "Civilian!");
						vill.setBreed(false);
						civilian.put(vill, 30);
						
					}
					e.getEntity().remove();
				}
			}else{
				e.getEntity().remove();
			}		
		}
	}
	*/
	
	@EventHandler
	public void storeinteract(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Villager){
			Villager vill = (Villager) e.getRightClicked();
			if(store.villss.containsKey(vill)){
				if(Storecmd.contains(store.villss.get(vill))){
					e.setCancelled(true);
					if(Storecmd.getBoolean(store.villss.get(vill) + ".console")){
						mian.getServer().dispatchCommand(mian.getServer().getConsoleSender(), Storecmd.getString(store.villss.get(vill) + ".cmd"));
					}else{
						mian.getServer().dispatchCommand(e.getPlayer(), Storecmd.getString(store.villss.get(vill) + ".cmd"));
					}
				
				}
			}else if(vill.getCustomName().equals(ChatColor.RED + ""+ChatColor.BOLD + "Civilian!")){
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onplayermove(PlayerMoveEvent e){
		for(Villager vill : store.villss.keySet()){
			if(e.getTo().distanceSquared(vill.getEyeLocation()) < 1.5 || e.getTo().distanceSquared(vill.getLocation()) < 1.5){
				
					//e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply( - 1).setY(0));
					e.getPlayer().teleport(e.getFrom());
				
				//e.setCancelled(true);
			}
		}
		
	}
	
	
	
	@EventHandler
	public void onentdamage(EntityDamageByEntityEvent e){
		if(e.getCause() == DamageCause.ENTITY_ATTACK){
			
			if(e.getEntity() instanceof Villager){
				Villager vill = (Villager) e.getEntity();
				if(store.villss.containsKey(vill)){
					e.setCancelled(true);
					vill.damage(e.getDamage());
					
					if(e.getDamager() instanceof Player){
						Player p = (Player) e.getDamager();
						
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
	

	
	
}
