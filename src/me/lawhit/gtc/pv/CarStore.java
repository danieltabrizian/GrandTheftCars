package me.lawhit.gtc.pv;



import java.util.ArrayList;
import java.util.List;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CarStore implements Listener{

	Main m;
	MyConfigManager manager;
	MyConfig Carstore;

	Economy eco;
	CarManager carm;
	public CarStore(Main me,CarManager carma,Economy eco){
		this.m = me;
		manager = new MyConfigManager(m);
		Carstore = manager.getNewConfig("carsconfig.yml");
		this.carm = carma;
		this.eco = eco;
	}
	
	
	
	
	
	public Inventory getCars(){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GRAY + "Legendary MotorCraft");
		for(String s:Carstore.getKeys()){
			ItemStack car = new ItemStack(Material.MINECART, 1);
			ItemMeta carm = car.getItemMeta();
			carm.setDisplayName(Carstore.getString(s+".name"));
			List<String> itemlore = new ArrayList<String>();
			itemlore.add(ChatColor.GOLD + ":"+"PRICE:" + Carstore.getDouble(s+".price") + ":");
			itemlore.add(ChatColor.BLUE+ ":" +"speed:" + Carstore.getDouble(s+".maxspeed") + ":");
			itemlore.add(ChatColor.BLUE+ ":" +"acceleration:"+Carstore.getDouble(s+".acceleration") + ":");
			itemlore.add(ChatColor.BLUE+ ":" +"armore:"+Carstore.getInt(s+".armore") + ":");
			itemlore.add(ChatColor.BLUE+ ":" +"turbo:"+Carstore.getDouble(s+".start") + ":");
			itemlore.add(ChatColor.BLUE + ":"+"brake:"+Carstore.getDouble(s+".brake") + ":");
			itemlore.add(ChatColor.BLUE+ ":" +"weight:"+Carstore.getInt(s+".weight") + ":");
			carm.setLore(itemlore);
			car.setItemMeta(carm);
			
			inv.addItem(car);
		}
		return inv;
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getInventory().getName().equals(ChatColor.GRAY + "Legendary MotorCraft")){
			e.setCancelled(true);
			if(e.getCurrentItem() != null){
				ItemStack is = e.getCurrentItem();
				Player p = (Player) e.getWhoClicked();
				if(is.getType() == Material.MINECART){
					List<String> lore = is.getItemMeta().getLore();
					String loree = "";
					for(String s : lore){
						loree = loree+s;
					}
					String[] split = loree.split(":");
					double price = Double.parseDouble(split[2]);
					double maxspeed = Double.parseDouble(split[5]);
					double acceleration = Double.parseDouble(split[8]);
					int armore = Integer.parseInt(split[11]);
					double start = Double.parseDouble(split[14]);
					double brake = Double.parseDouble(split[17]);
					int weight = Integer.parseInt(split[20]);
					if(eco.has(p, price)){
						carm.createcar(p, maxspeed, acceleration, armore, start, brake, weight, is.getItemMeta().getDisplayName());
						eco.withdrawPlayer(p, price);
						p.getInventory().addItem(is);
						p.closeInventory();
					}
					
				}
				
				
			}
		}
	}
	
}
