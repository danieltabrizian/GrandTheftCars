package me.lawhit.gtc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitTask;

public class Teleports implements Listener{
	public Map<Player,Integer> effecta = new HashMap<Player,Integer>();
	public Map<Player,Location> loceffect = new HashMap<Player,Location>();
	BukkitTask effector;
	public Teleports(){
		effector = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("GrandTheftCars"), new Runnable(){

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){
					
					if(effecta.containsKey(p)){
						if(effecta.get(p) < 1){
							
							p.teleport(loceffect.get(p));
							effecta.remove(p);
							loceffect.remove(p);
							p.setFlying(false);
							p.setFlySpeed(0.1f);
						}else{
							Location loc = p.getLocation();
							loc.setPitch(90);
							loc.setYaw(0);
							if(effecta.get(p) == 4){
								p.teleport(loc.add(0, 20, 0));
								
							}else if(effecta.get(p) ==3){
								p.teleport(loc.add(0, 40, 0));
							}else if(effecta.get(p) ==1){
								p.teleport(loc.add(0, 60, 0));
							}
							effecta.put(p, effecta.get(p)-1);
							p.setFlying(true);
							p.setFlySpeed(0f);
						}
					}
				}
				
			}
			
		}, 20l, 20l);
		
	}
	@EventHandler
	public void join(PlayerJoinEvent e){
		e.getPlayer().sendMessage(e.getPlayer().getLocation().getYaw() + " <><><> " +  e.getPlayer().getLocation().getPitch());
		effecta.put(e.getPlayer(), 4);
		loceffect.put(e.getPlayer(), e.getPlayer().getLocation());
	}
	
	@EventHandler
	public void kewltp(PlayerTeleportEvent e){
		if(e.getFrom().distance(e.getTo()) > 14){
			
			if(effecta.containsKey(e.getPlayer()) == false){
				effecta.put(e.getPlayer(), 4);
				loceffect.put(e.getPlayer(), e.getTo());
				e.setCancelled(true);
			}
		}
	}

}
