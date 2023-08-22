package org.dldyou.main.commands;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dldyou.main.Stat;

public class ResetStat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			UUID uuid = player.getUniqueId();
//			Stat.setRemain(player, (Stat.lv.get(uuid) - 1) * 5 + 20);
//			Stat.setDmg(player, 0);
//			Stat.setSpd(player, 0);
//			Stat.setArm(player, 0);
//			Stat.setAs(player, 0);
			return true;
		}
		else {
			
		}
		return false;
	}
	
}