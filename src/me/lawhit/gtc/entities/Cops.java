package me.lawhit.gtc.entities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class Cops {

	public Map<PigZombie,Integer> pz = new HashMap<PigZombie,Integer>();
	public PigZombie zombie;
	BukkitTask remover;
	@SuppressWarnings("deprecation")
	public Cops(Player p, Integer stars){
	
	   
	    for(int i = 0 ; i < stars;i++){
	    	 zombie = (PigZombie) p.getWorld().spawnEntity(p.getTargetBlock(null, 5).getLocation(), EntityType.PIG_ZOMBIE);
	    	zombie.setAnger(3);
	    	zombie.setAngry(true);
	    	zombie.setTarget(p);
	    	zombie.setRemoveWhenFarAway(true);
	    	zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS,1));
	    	zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
	    	zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS,1));
	    	zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE,1));
	    	zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD,1));
	    	pz.put(zombie, 20);
	    }
	    
	    remover = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("GrandTheftCars"), new Runnable(){

			@Override
			public void run() {
				try{
					for(PigZombie pg : pz.keySet()){
						if(pz.get(pg) < 1){
							pg.remove();pz.remove(pg);
						}else{
							pz.put(pg, pz.get(pg) - 1);
							
						}
					}
				}catch(Exception e){
					
				}
				
				
			}
	    	
	    }, 20l,20l);
		
	}
}
