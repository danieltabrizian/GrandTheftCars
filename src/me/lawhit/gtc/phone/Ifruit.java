package me.lawhit.gtc.phone;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Ifruit {
	MyConfigManager manager;
	MyConfig ifruit;
	public Ifruit(Main m){
		manager = new MyConfigManager(m);
		ifruit = manager.getNewConfig("ifruit.yml");
		ifruit.saveConfig();
	}
	
	
	public ItemStack getFruit(){
		ItemStack fruit = new ItemStack(Material.NETHER_BRICK_ITEM, 1);
		fruit.addUnsafeEnchantment(Enchantment.DURABILITY, 2000);
		ItemMeta mfruit = fruit.getItemMeta();
		mfruit.setDisplayName(ChatColor.GREEN + "IFruit");
		fruit.setItemMeta(mfruit);
		
		return fruit;
	}
	
	public Inventory getInvfruit(){
		Inventory inv = Bukkit.createInventory(null, InventoryType.DISPENSER, ChatColor.GRAY + "Ifruit");
		
		for(String s : ifruit.getKeys()){
			ItemStack is = (ItemStack) ifruit.get(s+".item");
			inv.addItem(is);
		}
		
		return inv;
		
	}
}
