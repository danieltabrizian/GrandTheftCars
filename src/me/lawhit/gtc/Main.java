package me.lawhit.gtc;

import me.lawhit.gtc.entities.Store.StoreCommand;
import me.lawhit.gtc.entities.Store.StoreListener;
import me.lawhit.gtc.entities.Store.Stores;
import me.lawhit.gtc.entities.cops.CopGiver;
import me.lawhit.gtc.entities.cops.CopsEvent;
import me.lawhit.gtc.phone.Ifruit;
import me.lawhit.gtc.phone.IfruitCommand;
import me.lawhit.gtc.phone.Ifruitlistener;
import me.lawhit.gtc.pv.CarCommand;
import me.lawhit.gtc.pv.CarManager;
import me.lawhit.gtc.pv.CarMotion;
import me.lawhit.gtc.pv.CarStore;
import me.lawhit.gtc.pv.Cars;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.shampaggon.crackshot.CSUtility;

public class Main extends JavaPlugin{
	//cars
	CarManager carm;
	CarCommand carc;
	CarStore cs;
	Cars car;
	//stores
	Stores store;
	StoreCommand sc;
	StoreListener sl;
	//ifruit
	Ifruit ifruit;
	IfruitCommand ifruitcommand;
	Ifruitlistener ifruitlistener;
	//eco
	 public static Economy economy = null;
	 //cops
	 CopsEvent cops;
	 CopGiver copg;
	 //crackshot
	 CSUtility csmanager;
	 
	 //protocol
		public Boolean protocolLib = false;
		public Object  protocolManager = null;
		public float forwards = (float) 0.0;
		//respawn
		Respawn respawn;
		//join
		Teleports tps;
		
		public void onEnable(){
		csmanager = new CSUtility();
		if(setupEconomy() == false){
			getLogger().info("failed to log Economy");
		}
		
		if(setupProtocol() ==false){
			getLogger().info("protofail");
		}

		//cars
	
		this.carm = new CarManager(this);
		cs = new CarStore(this,carm,economy);
		this.carc = new CarCommand(carm,cs);
		car = new Cars(this, carm);
		Bukkit.getPluginManager().registerEvents(car, this);
		this.getCommand("createcar").setExecutor(carc);
		
		Bukkit.getPluginManager().registerEvents(cs, this);
		
		//store
		store = new Stores(this);
		sc = new StoreCommand(this,economy);
		this.getCommand("createstore").setExecutor(sc);
		sl = new StoreListener(economy,this,store);
		
		Bukkit.getPluginManager().registerEvents(sl, this);
		//ifruit
		ifruit = new Ifruit(this);
		ifruitcommand  = new IfruitCommand(ifruit);
		ifruitlistener = new Ifruitlistener(this,ifruit);
		Bukkit.getPluginManager().registerEvents(ifruitlistener, this);
		this.getCommand("createphone").setExecutor(ifruitcommand);
		//cops
		cops = new CopsEvent(economy);
		copg = new CopGiver(csmanager);
		Bukkit.getPluginManager().registerEvents(copg, this);
		Bukkit.getPluginManager().registerEvents(cops, this);
		//respawn
		respawn = new Respawn(this,csmanager);
		Bukkit.getPluginManager().registerEvents(respawn, this);
		//teleports
		tps = new Teleports();
		Bukkit.getPluginManager().registerEvents(tps, this);
		//nolagg
		Bukkit.getPluginManager().registerEvents( new Nolagg(this), this);
	}
	
	public void onDisable(){
		carm.cars.saveConfig();
		for(Villager vill : store.villss.keySet()){
			vill.remove();
		}
	}
	
	
	 private boolean setupEconomy()
	    {
	        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	        }

	        return (economy != null);
	    }
	 

	private boolean setupProtocol(){
		 
		 try {
				this.protocolLib = true;
				this.protocolManager = ProtocolLibrary.getProtocolManager();
				((ProtocolManager)this.protocolManager).addPacketListener(new PacketAdapter(this,ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE) {
				
				    @Override
				    public void onPacketReceiving(PacketEvent event) {
				    	if(event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE){
				            PacketContainer packet = event.getPacket();	
				            float sideways = packet.getFloat().read(0);
				           forwards= packet.getFloat().read(1);  
				            
				            new CarMotion(event.getPlayer(),forwards,sideways,car);
				            
				    	}

				    }
				   
				    
				});
			} catch (Exception e) {
				return false;
			}
	    	return true;
	    }
	 
	 
	
}
