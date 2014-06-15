package me.lawhit.gtc.entities.cops;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class Cops {

	public Map<Skeleton,Integer> pz = new HashMap<Skeleton,Integer>();
	public Skeleton Skeleton;
	Random random = new Random();
	BukkitTask remover;
	public Cops(Player p, Integer stars){
	
	   if(p.getGameMode() != GameMode.CREATIVE){
		   for(int i = 0 ; i < stars;i++){
		    	
		    	 Skeleton = (Skeleton) p.getWorld().spawnEntity(p.getLocation().add(random.nextInt(6)-random.nextInt(12), 0, random.nextInt(6)-random.nextInt(12)), EntityType.SKELETON);
		    	 /*
		    	Skeleton.setAnger(3);
		    	Skeleton.setAngry(true);
		    	*/
		    	
		    	Skeleton.setTarget(p);
		    	Skeleton.setMaxHealth(20.0);
		    	Skeleton.setCustomName(ChatColor.AQUA + "Cop");
		    	Skeleton.setCustomNameVisible(true);
		    	Skeleton.setRemoveWhenFarAway(true);
		    	//Skeleton.setSkeletonType(SkeletonType.WITHER);;
		    	Skeleton.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS,1));
		    	Skeleton.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET,1));
		    	Skeleton.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS,1));
		    	Skeleton.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE,1));
		    	ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
		    	sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		    	Skeleton.getEquipment().setItemInHand(sword);
		    	//Skeleton.
		    	Skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1), true);
		    	
		    	pz.put(Skeleton, 50);
		    }
		    
		    remover = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("GrandTheftCars"), new Runnable(){

				@Override
				public void run() {
					try{
						for(Skeleton pg : pz.keySet()){
		
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
	    
}
