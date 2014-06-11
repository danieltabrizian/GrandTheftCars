package me.lawhit.gtc;

import me.lawhit.gtc.pv.CarCommand;
import me.lawhit.gtc.pv.CarManager;
import me.lawhit.gtc.pv.CarMotion;
import me.lawhit.gtc.pv.CarStore;
import me.lawhit.gtc.pv.Cars;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class Main extends JavaPlugin{
	//cars
	CarManager carm;
	CarCommand carc;
	CarStore cs;
	Cars car;

	//eco
	 public static Economy economy = null;

	 
	 //protocol
		public Boolean protocolLib = false;
		public Object  protocolManager = null;
		public float forwards = (float) 0.0;
	public void onEnable(){
		setupEconomy();
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
