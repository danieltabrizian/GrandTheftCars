package me.lawhit.gtc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

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
						PotionEffect ef = new PotionEffect(PotionEffectType.BLINDNESS, 10, 200);
						if(effecta.get(p) < 1){
							p.addPotionEffect(ef,false);
							p.teleport(loceffect.get(p).add(0, -60, 0));
							effecta.remove(p);
							loceffect.remove(p);
							p.setAllowFlight(false);
							p.setFlying(false);
							p.setFlySpeed(0.1f);
							p.setFallDistance(0);
						}else{
							Location loc = p.getLocation();
							loc.setPitch(90);
							loc.setYaw(0);
							Location loc2 = loceffect.get(p);
							loc2.setPitch(90);
							loc2.setYaw(0);
							
							
							
							if(effecta.get(p) == 12){
								p.teleport(loc.add(0, 20, 0));
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.addPotionEffect(ef,false);
							}else if(effecta.get(p) ==11){
								p.teleport(loc.add(0, 40, 0));
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.addPotionEffect(ef,false);
							}else if(effecta.get(p) ==9){
								p.teleport(loc.add(0, 60, 0));
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.addPotionEffect(ef,false);
							}else if(effecta.get(p) == 7){
								p.addPotionEffect(ef,false);
								p.setVelocity(new Vector(-10,0,0));
							}else if(effecta.get(p) == 6){
								p.setVelocity(new Vector(-10,0,0));
							}else if(effecta.get(p) == 5){
								p.addPotionEffect(ef,false);
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.teleport(loc.add(0, -20, 0));
							}else if(effecta.get(p) == 4){
								p.addPotionEffect(ef,false);
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.teleport(loc2.add(0, 60, 0));
							}else if(effecta.get(p) == 3){
								p.addPotionEffect(ef,false);
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.teleport(loc.add(0, -40, 0));
							}else if(effecta.get(p) == 1){
								p.addPotionEffect(ef,false);
								p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2.0F, 0.0F);
								p.teleport(loc.add(0, -20, 0));
							}
							effecta.put(p, effecta.get(p)-1);
							p.setAllowFlight(true);
							p.setFlying(true);
							p.setFlySpeed(0f);
							p.setFallDistance(0);
						}
					}
				}
				
			}
			
		}, 20l, 20l);
		
	}
	@EventHandler
	public void join(PlayerJoinEvent e){
		effecta.put(e.getPlayer(), 5);
		loceffect.put(e.getPlayer(), e.getPlayer().getLocation());
	}
	
	@EventHandler
	public void kewltp(PlayerTeleportEvent e){
		if(e.getFrom().distance(e.getTo()) > 14){
			
			if(effecta.containsKey(e.getPlayer()) == false){
				effecta.put(e.getPlayer(), 12);
				loceffect.put(e.getPlayer(), e.getTo());
				e.setCancelled(true);
			}
		}
	}

}
