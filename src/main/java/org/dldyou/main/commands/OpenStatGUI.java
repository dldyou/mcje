package org.dldyou.main.commands;

import org.dldyou.main.gui.StatGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenStatGUI implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			StatGUI inv = new StatGUI(player);
			inv.open(player);
			return true;
		}
		else {
			
		}
		return false;
	}
	
}