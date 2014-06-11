package me.lawhit.gtc.pv;

import java.util.HashMap;
import java.util.Map;

import me.lawhit.gtc.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;



public class Cars implements Listener{
	public Map<String, Double> multiplier = new HashMap<String,Double>();
	
	Main m;

	public Float forward;
	
	CarManager car;
	public Cars(Main plugin,CarManager _car){
		this.m = plugin;
		this.car = _car;
		
		 @SuppressWarnings("unused")
		BukkitTask drive;
		 drive = Bukkit.getScheduler().runTaskTimer(m, new Runnable(){

			@Override
			public void run() {
			}
		 }, 1l, 1l);
		 
	}
	

	
	
	@EventHandler
	public void colentity(VehicleEntityCollisionEvent e){
		if(e.getVehicle() instanceof Minecart){
			if(e.getVehicle().getPassenger() instanceof Player){
				if(e.getEntity()instanceof LivingEntity){
					Player p = (Player) e.getVehicle().getPassenger();
					Double calcdamage = multiplier.get(p.getUniqueId().toString()) *car.getWeight(p);
					((LivingEntity) e.getEntity()).damage(calcdamage);
					multiplier.put(p.getUniqueId().toString(), multiplier.get(p.getUniqueId().toString()) - 0.3);
					e.getEntity().setVelocity(new Vector(0,calcdamage/4,0));
				}
				
			}
		}
	}
	
	
	@EventHandler
	public void colblock(VehicleBlockCollisionEvent e){
		if(e.getVehicle() instanceof Minecart){
			if(e.getVehicle().getPassenger() instanceof Player){
				Player p = (Player) e.getVehicle().getPassenger();
					if(e.getBlock().getLocation().add(0, 1, 0).getBlock().getType() == Material.AIR){
						
							e.getVehicle().setVelocity(new Vector(0,0.5,0));
						
						
						//multiplier.put(p.getUniqueId().toString(), multiplier.get(p.getUniqueId().toString()) -0.4);
							
					}else{
						if(multiplier.get(p.getUniqueId().toString()) >= car.getBodyArmore(p)){
							
							TNTPrimed tnt = (TNTPrimed) e.getVehicle().getWorld().spawnEntity(e.getVehicle().getLocation(), EntityType.PRIMED_TNT);
							tnt.setFuseTicks(0);
							e.getVehicle().remove();
							multiplier.put(p.getUniqueId().toString(), 0.0);
						}else{
							multiplier.put(p.getUniqueId().toString(), 0.0);
						}
						
					}
			
				
					
					
					
				
				
			}
		}
	}
	
	
	@EventHandler
	public void enterveh(VehicleEnterEvent e){
		if(e.getEntered() instanceof Player){
			Player p = (Player) e.getEntered();
			if(e.getVehicle() instanceof Minecart){
				//Minecart mc = (Minecart) e.getVehicle();
				multiplier.put(p.getUniqueId().toString(), car.getStart(p));
				
			
			}
		}
	}
	
	@EventHandler 
	public void leaveveh(VehicleExitEvent e){
		if(e.getVehicle() instanceof Minecart){
			if(e.getExited() instanceof Player){
				Player p = (Player) e.getExited();
				if(car.HasPV(p)){
					
					
				}
			}
		}
	}
	
	@EventHandler 
	public void quitveh(VehicleDestroyEvent e){
		if(e.getVehicle() instanceof Minecart){
			if(e.getVehicle().getPassenger() instanceof Player){
				Player p = (Player) e.getVehicle().getPassenger();
				if(car.HasPV(p)){
				
					
				}
			}
		}
	}
	
	
	@EventHandler
	public void vehiclemove(VehicleMoveEvent e){
		if(e.getVehicle() instanceof Minecart){
			Minecart mc = (Minecart) e.getVehicle();
			if(e.getVehicle().getPassenger() instanceof Player ){
				Player p = (Player) e.getVehicle().getPassenger();
				if(!(m.forwards > 0)){
					if( multiplier.get(p.getUniqueId().toString())> car.getStart(p)){
						multiplier.put(p.getUniqueId().toString(), multiplier.get(p.getUniqueId().toString()) - car.getBrake(p));
						double Velocitycircle = multiplier.get(p.getUniqueId().toString());
						
						mc.setDerailedVelocityMod(p.getLocation().getDirection().multiply(Velocitycircle));
						p.setLevel(Math.round(Math.round(Math.floor(multiplier.get(p.getUniqueId().toString())))) );
						mc.getLocation().setYaw(p.getLocation().getYaw());
					}
				}
				
				if(mc.getLocation().subtract(0,1, 0).getBlock().getType() == Material.AIR){
					mc.setVelocity(mc.getVelocity().subtract(new Vector(0,0.1,0)));
					p.setFallDistance(0);
					mc.setFallDistance(0);
				}

			}
		}
	}
	

	
	
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void spawnpv(PlayerInteractEvent e){
		if(e.getItem() != null){
			if(e.getItem().getType() == Material.MINECART){
				e.getPlayer().getWorld().spawnEntity(e.getPlayer().getTargetBlock(null, 3).getLocation().add(0, 1.5, 0), EntityType.MINECART);
				
				e.getPlayer().getInventory().setItemInHand(new ItemStack(e.getItem().getType(),e.getItem().getAmount()-1));
				
			}
		}
	}

}
