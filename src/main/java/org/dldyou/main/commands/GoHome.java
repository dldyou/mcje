package org.dldyou.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GoHome implements CommandExecutor {
	public Plugin p;
	public GoHome(Plugin plugin) {
		p = plugin;
	}
	
	public void SetTimer(Player player, int x, Location loc) {
		if (x == 0) {
			if (player.getBedSpawnLocation() == null) {
				player.sendMessage("[귀환] 스폰 지점이 파괴되어 귀환이 불가능합니다.");
			}
			else {
				player.teleport(player.getBedSpawnLocation());
				player.sendMessage("[귀환] 귀환이 완료되었습니다.");
			}
			return;
		}
		else {
			player.sendMessage("[귀환] " + Integer.toString(x) + "초 후에 스폰포인트로 이동합니다. [움직일 시 귀환이 취소됩니다.]");
			Bukkit.getScheduler().runTaskLater(p, new Runnable() {
				
				@Override
				public void run() {
					Location ploc = player.getLocation();
					if (ploc.getBlockX() != loc.getBlockX() ||
							ploc.getBlockY() != loc.getBlockY() || 
							ploc.getBlockZ() != loc.getBlockZ()) {
						player.sendMessage("[귀환] 귀환이 취소되었습니다.");
					}
					else {
						SetTimer(player, x - 1, loc);
					}
				}
			}, 20);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			SetTimer(player, 5, player.getLocation());
			return true;
		}
		else {
			
		}
		return false;
	}
	
}