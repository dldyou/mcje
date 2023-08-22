package org.dldyou.main.gui;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.dldyou.main.ItemManager;
import org.dldyou.main.Stat;

import net.md_5.bungee.api.ChatColor;

public class StatGUI implements Listener {
	private final Inventory inv;
	private void initItemSetting(Player player) {
		UUID uuid = player.getUniqueId();
		inv.setItem(0, ItemManager.buildItem(Material.BOW, 1, ChatColor.AQUA + "STR", "공격력이 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.dmg.get(uuid) + "]"));
		inv.setItem(2, ItemManager.buildItem(Material.FEATHER, 1, ChatColor.AQUA + "SPD", "이동속도가 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.spd.get(uuid) + "]"));
		inv.setItem(4, ItemManager.buildItem(Material.IRON_INGOT, 1, ChatColor.AQUA + "ARM", "방어력이 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.arm.get(uuid) + "]"));
		inv.setItem(6, ItemManager.buildItem(Material.BLAZE_POWDER, 1, ChatColor.AQUA + "AS", "공격속도가 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.as.get(uuid) + "]"));
		inv.setItem(8, ItemManager.buildItem(Material.DIAMOND, 1, ChatColor.AQUA + "POINT", "잔여 스탯 포인트입니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.remain.get(uuid) + "]"));
	}
	
	public StatGUI(Player player) {
		this.inv = Bukkit.createInventory(null, 9, "§l스탯");
		initItemSetting(player);
	}
	
	public void open(Player player) {
		player.openInventory(inv);
	}
}