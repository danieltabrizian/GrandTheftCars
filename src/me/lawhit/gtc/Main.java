package me.lawhit.gtc;

import me.lawhit.gtc.pv.CarCommand;
import me.lawhit.gtc.pv.CarManager;
import me.lawhit.gtc.pv.CarStore;
import me.lawhit.gtc.pv.Cars;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	//cars
	CarManager carm;
	CarCommand carc;
	CarStore cs;
	//eco
	 public static Economy economy = null;
	
	public void onEnable(){
		setupEconomy();
		//cars
	
		this.carm = new CarManager(this);
		this.cs = new CarStore(this,carm,economy);
		this.carc = new CarCommand(carm,cs);
		Bukkit.getPluginManager().registerEvents(new Cars(this, carm), this);
		this.getCommand("createcar").setExecutor(carc);
		Bukkit.getPluginManager().registerEvents(cs, this);
		
		
		
		
		
		
	
	}
	
	public void onDisable(){
		carm.cars.saveConfig();
	}
	
	
	 private boolean setupEconomy()
	    {
	        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	        }

	        return (economy != null);
	    }
}
