package me.lawhit.gtc.pv;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CarMotion {
	
	public CarMotion(Player p, float f,float s,Cars cars){
		
		CarManager car = cars.car;
		boolean forward = true;
		
		//int sideways = 0;
		 if(f == 0){
			 return;
		 }
		 if(f < 0) forward = false;else forward = true;
			if(p.getVehicle() instanceof Minecart){
				Minecart mc = (Minecart) p.getVehicle();
				
				
				mc.getLocation().setYaw(p.getLocation().getYaw());
				mc.getLocation().setPitch(p.getLocation().getPitch());

				
					if(forward){
						
						Double X = p.getLocation().getDirection().multiply(3).getX();
						Double Y = 0.0;
						Double Z = p.getLocation().getDirection().multiply(3).getZ();
						Vector finalvec = new Vector(X,Y,Z);
						mc.setVelocity(finalvec);
						Double Velocitycircle = cars.multiplier.get(p.getUniqueId().toString());
						mc.setDerailedVelocityMod(new Vector(Velocitycircle,Velocitycircle,Velocitycircle));
						//mc.setDerailedVelocityMod(p.getLocation().getDirection().multiply(Velocitycircle));
						
						if(Velocitycircle < car.getMaxSpeed(p)){
							cars.multiplier.put(p.getUniqueId().toString(), cars.multiplier.get(p.getUniqueId().toString()) +(car.getAcceleration(p)/10));
						}

						p.setLevel(Math.round(Math.round(Math.floor(cars.multiplier.get(p.getUniqueId().toString())))) );
					
					}else {
						Double Velocitycircle = cars.multiplier.get(p.getUniqueId().toString());

						mc.setDerailedVelocityMod(new Vector(Velocitycircle,Velocitycircle,Velocitycircle));							
						p.setLevel(Math.round(Math.round(Math.floor(cars.multiplier.get(p.getUniqueId().toString())))) );
					}
				

			
		}
		
	
	}
 
}
