package me.lawhit.gtc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class Nolagg implements Listener{
	public Map<Item,Integer> itemremoval = new HashMap<Item,Integer>();
	
	public Nolagg(Main m){
		Bukkit.getScheduler().runTaskTimer(m, new Runnable(){

			@Override
			public void run() {
				for(Item item : itemremoval.keySet()){
					if(item.isValid()){
						if(itemremoval.get(item) < 1){
							item.remove();
							itemremoval.remove(item);
						}else{
							itemremoval.put(item, itemremoval.get(item) - 1);
						}
					}else{
						itemremoval.remove(item);
					}
					
				}
				
			}
			
		}, 20l, 20l);
	}
	
	@EventHandler
	public void Playerdropitem(ItemSpawnEvent e){
		itemremoval.put(e.getEntity(), 15);
		
	}
	

	
	
}
