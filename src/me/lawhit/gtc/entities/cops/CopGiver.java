package me.lawhit.gtc.entities.cops;

import me.lawhit.gtc.Hologram;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.shampaggon.crackshot.CSUtility;
import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

public class CopGiver implements Listener{
	
	CSUtility csu;
	public CopGiver(CSUtility cs){
		csu = cs;
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void crackhit(WeaponDamageEntityEvent e){
		
		new Hologram(ChatColor.RED + ""+ e.getDamage() ).show(e.getPlayer().getTargetBlock(null, 2).getLocation().add(0, -0.5, 0),20);
		if(e.getVictim() instanceof Damageable){
			Damageable le = (Damageable) e.getVictim();
			
			if(le.getHealth() - e.getDamage() < 0.5){
				if(e.getVictim() instanceof Player){
					new Cops(e.getPlayer(),3);
				}else if (e.getVictim() instanceof Zombie){
					new Cops(e.getPlayer(),4);
				}else{
					new Cops(e.getPlayer(),2);
				}
				
			}
		}
		
		
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void bhit(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof LivingEntity){
			LivingEntity le = (LivingEntity) e.getDamager();
			Damageable dmg = le;
			new Hologram(ChatColor.RED + ""+ e.getDamage() ).show(le.getTargetBlock(null, 2).getLocation().add(0, -0.5, 0),20);
			if(e.getDamager() instanceof Player){
				if(dmg.getHealth() - e.getDamage() < 0.5){
					new Cops((Player) e.getDamager(), 1);
				}
			}
			
		}
	}
	
	
}
