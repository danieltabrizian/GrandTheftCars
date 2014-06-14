package me.lawhit.gtc.phone;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Ifruitlistener implements Listener{
	Ifruit fruit;
	MyConfigManager manager;
	MyConfig ifruit;
	Main main;
	public Ifruitlistener(Main m, Ifruit fruit){
		this.main = m;
		manager = new MyConfigManager(m);
		ifruit = manager.getNewConfig("ifruit.yml");
		ifruit.saveConfig();
		this.fruit = fruit;
	}
	
	
	
	@EventHandler
	public void ifruitinteract(PlayerInteractEvent e){
		if(e.getItem() instanceof ItemStack){
			if(fruit.getFruit().isSimilar(e.getItem())){
				e.getPlayer().openInventory(fruit.getInvfruit());
			}
		}
	}
	
	@EventHandler
	public void ifruitclick(InventoryClickEvent e){
		if(e.getInventory().getName().equals(ChatColor.GRAY + "Ifruit")){
			String fruitie = "" ;
			e.setCancelled(true);
			if(e.getCurrentItem() != null){
				if(e.getSlot() == 0){
					fruitie = (String) ifruit.getKeys().toArray()[0];
				}else if(e.getSlot() == 1){
					fruitie = (String) ifruit.getKeys().toArray()[1];
				}else if(e.getSlot() == 2){
					fruitie = (String) ifruit.getKeys().toArray()[2];
				}else if(e.getSlot() == 3){
					fruitie = (String) ifruit.getKeys().toArray()[3];
				}else if(e.getSlot() == 4){
					fruitie = (String) ifruit.getKeys().toArray()[4];
				}else if(e.getSlot() == 5){
					fruitie = (String) ifruit.getKeys().toArray()[5];
				}else if(e.getSlot() == 6){
					fruitie = (String) ifruit.getKeys().toArray()[6];
				}else if(e.getSlot() == 7){
					fruitie = (String) ifruit.getKeys().toArray()[7];
				}else if(e.getSlot() == 8){
					fruitie = (String) ifruit.getKeys().toArray()[8];
				}
				
				e.getWhoClicked().closeInventory();
				main.getServer().dispatchCommand((CommandSender) e.getWhoClicked(),ifruit.getString(fruitie+ ".command"));
			}
			
			
		}
	}
	
	
}
