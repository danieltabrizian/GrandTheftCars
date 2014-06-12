package me.lawhit.gtc.entities;

import java.util.HashSet;
import java.util.Set;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Stores {
	Main plugin;
	MyConfigManager manager;
	MyConfig stores;
	public Set<Villager> villss = new HashSet<Villager>();
	
	
	
	Economy eco;
	public Stores(Main m){
		this.plugin = m;
		manager = new MyConfigManager(m);
		stores = manager.getNewConfig("stores.yml");
		stores.saveConfig();
		for(String s : stores.getKeys()){
			Villager vill = (Villager) Bukkit.getWorld(stores.getString(s+".world")).spawnEntity(new Location (Bukkit.getWorld(stores.getString(s+".world")),stores.getDouble(s+".x"),stores.getDouble(s+".y"),stores.getDouble(s+".z")), EntityType.VILLAGER);
			vill.setCustomName(stores.getString(s + ".name"));
			vill.setCustomNameVisible(true);
			vill.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 10), false);
			vill.setProfession(Profession.PRIEST);
			villss.add(vill);
		}
		
	}
	
	
	
	
}
