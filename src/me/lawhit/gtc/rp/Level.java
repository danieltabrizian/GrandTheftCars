package me.lawhit.gtc.rp;

import org.bukkit.entity.Player;

public class Level {

	public void level(){
		
	}
	
	
	public void LevelAdd(Player p, Integer rp){
		
	}
	
	public double calclevels(double already, int lvl){
		double totalrp = 0;
		if(lvl > 0){
			totalrp = 4000 - already;
		}
		if(lvl > 10){
			totalrp = 10000 - already;
		}
		if(lvl > 20){
			totalrp = 24000 - already;
		}
		if(lvl > 30){
			totalrp = 31000 - already;
		}
		if(lvl > 40){
			totalrp = 43000 - already;
		}
		if(lvl > 50){
			totalrp = 56000 - already;
		}
		if(lvl > 70){
			totalrp = 85000 - already;
		}
		if(lvl > 80){
			totalrp = 98000 - already;
		}
		if(lvl > 100){
			totalrp = 140000 - already;
		}
		if(lvl > 120){
			totalrp = 193200 - already;
		}
	return totalrp;
	}
	
	
	
	
	
}
