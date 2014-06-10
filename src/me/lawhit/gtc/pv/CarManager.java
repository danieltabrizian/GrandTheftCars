package me.lawhit.gtc.pv;

import me.lawhit.gtc.Main;
import me.lawhit.gtc.MyConfig;
import me.lawhit.gtc.MyConfigManager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CarManager {

	
	MyConfigManager manager ;
	public MyConfig cars;
	Main m;
	public CarManager(Main plugin){
		manager = new MyConfigManager(plugin);
		 cars = manager.getNewConfig("cars.yml");
		 this.m = plugin;
	}
	 //TODO maxspeed
	//TODO acceleration
	//TODO bodyarmore
	//TODO startvalue
	//TODO brakes
	
	public boolean HasPV(Player p){
		if(cars.contains(p.getUniqueId().toString())){
			return true;
		}else{
			return false;
		}
	}
	
	
	public double getMaxSpeed(Player p){
		
		return cars.getDouble(p.getUniqueId().toString()  + ".maxspeed");
		
	}
	public double getAcceleration(Player p){
		
		return cars.getDouble(p.getUniqueId().toString()  + ".acceleration");
		
	}
	public Integer getBodyArmore(Player p){
		
		return cars.getInt(p.getUniqueId().toString()  + ".armore");
		
	}
	
	public double getStart(Player p){
		
		return cars.getDouble(p.getUniqueId().toString()  + ".start");
		
	}
	
	public double getBrake(Player p){
		
		return cars.getDouble(p.getUniqueId().toString()  + ".brake");
		
	}
	
	public int getWeight(Player p){
		return cars.getInt(p.getUniqueId().toString()  + ".weight");
	} 
	
	public String getName(Player p){
		return cars.getString(p.getUniqueId().toString()  + ".name");
	}
	
	public void createcar(Player p,double maxspeed,double accel,int armore,double start,double brake,int Weight,String name){
		cars.set(p.getUniqueId().toString() + ".maxspeed", maxspeed);
		cars.set(p.getUniqueId().toString() + ".acceleration", accel);
		cars.set(p.getUniqueId().toString() + ".armore", armore);
		cars.set(p.getUniqueId().toString() + ".start", start);
		cars.set(p.getUniqueId().toString() + ".brake", brake);
		cars.set(p.getUniqueId().toString()  + ".weight", Weight);

	}
	
	public void setLastItem(Player p,ItemStack is){
		cars.set(p.getUniqueId().toString() + ".lastitem", is);
	}
	
	public ItemStack getLastItem(Player p){
		return (ItemStack) cars.get(p.getUniqueId().toString() + ".lastitem");
		
	}
}
