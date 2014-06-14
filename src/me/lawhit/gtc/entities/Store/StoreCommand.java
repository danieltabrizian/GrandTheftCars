package me.lawhit.gtc.entities.Store;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StoreCommand implements CommandExecutor{

	MyConfigManager manager;
	MyConfig stores;
	Main m;
	Economy eco;
	public StoreCommand(Main m,Economy eco2){
		this.m = m;
		this.eco = eco2;
		manager = new MyConfigManager(m);
		stores = manager.getNewConfig("stores.yml");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if(cmd.getName().equalsIgnoreCase("createstore")){
			Player p = (Player) sender;
			stores.set(args[0] + ".world", p.getWorld().getName());
			stores.set(args[0] + ".x", p.getLocation().getX());
			stores.set(args[0] + ".y", p.getLocation().getY());
			stores.set(args[0] + ".z", p.getLocation().getZ());
			stores.set(args[0] + ".name", args[1]);
			stores.saveConfig();
			String s = args[0];
			Villager vill = (Villager) Bukkit.getWorld(stores.getString(s+".world")).spawnEntity(new Location (Bukkit.getWorld(stores.getString(s+".world")),stores.getDouble(s+".x"),stores.getDouble(s+".y"),stores.getDouble(s+".z")), EntityType.VILLAGER);
			vill.setCustomName(stores.getString(args[0] + ".name"));
			vill.setCustomNameVisible(true);
			vill.setRemoveWhenFarAway(false);
			vill.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 10), false);
			return true;
		}
		return false;
	}

}
