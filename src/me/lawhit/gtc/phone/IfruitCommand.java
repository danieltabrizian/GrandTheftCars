package me.lawhit.gtc.phone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IfruitCommand implements CommandExecutor{

	Ifruit ifruit;
	
	public IfruitCommand(Ifruit iff){
		ifruit = iff;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,String[] args) {
		if(cmd.getName().equalsIgnoreCase("createphone")){
			//carm.createcar((Player)sender, Double.parseDouble(args[0]), Double.parseDouble(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5]),args[6]);
			((Player)sender).getInventory().addItem(ifruit.getFruit());
			
		}
		return false;
	}

}
