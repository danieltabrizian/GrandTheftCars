package me.lawhit.gtc.pv;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CarCommand implements CommandExecutor{

	CarManager carm;
	CarStore css;
	public CarCommand(CarManager carma,CarStore cs){
		this.carm = carma;
		this.css = cs;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,String[] args) {
		if(cmd.getName().equalsIgnoreCase("createcar")){
			carm.createcar((Player)sender, Double.parseDouble(args[0]), Double.parseDouble(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5]),args[6]);
			((Player)sender).openInventory(css.getCars());
			
		}
		return false;
	}

}
