package me.lawhit.gtc.entities.cops;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class Cops {

	public Map<Zombie,Integer> pz = new HashMap<Zombie,Integer>();
	public Zombie zombie;
	Random random = new Random();
	BukkitTask remover;
	public Cops(Player p, Integer stars){
	
	   
	    for(int i = 0 ; i < stars;i++){
	    	
	    	 zombie = (Zombie) p.getWorld().spawnEntity(p.getLocation().add(random.nextInt(7) -14, 0, random.nextInt(3)-3), EntityType.ZOMBIE);
	    	 /*
	    	zombie.setAnger(3);
	    	zombie.setAngry(true);
	    	*/
	    	
	    	zombie.setTarget(p);
	    	zombie.setCustomName(ChatColor.AQUA + "Cop");
	    	zombie.setCustomNameVisible(true);
	    	zombie.setRemoveWhenFarAway(true);
	    	zombie.setVillager(true);
	    	zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS,1));
	    	zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
	    	zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS,1));
	    	zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE,1));
	    	ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
	    	sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
	    	zombie.getEquipment().setItemInHand(sword);
	    	//zombie.
	    	zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 5), true);
	    	
	    	pz.put(zombie, 50);
	    }
	    
	    remover = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("GrandTheftCars"), new Runnable(){

			@Override
			public void run() {
				try{
					for(Zombie pg : pz.keySet()){
	
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
