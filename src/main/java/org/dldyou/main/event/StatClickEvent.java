package org.dldyou.main.event;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.dldyou.main.ItemManager;
import org.dldyou.main.Stat;

import net.md_5.bungee.api.ChatColor;

public class StatClickEvent implements Listener {

	public void update(Player player, InventoryView inv) {
		UUID uuid = player.getUniqueId();
		inv.setItem(0, ItemManager.buildItem(Material.BOW, 1, ChatColor.AQUA + "STR", "공격력이 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.dmg.get(uuid) + "]"));
		inv.setItem(2, ItemManager.buildItem(Material.FEATHER, 1, ChatColor.AQUA + "DEX", "이동속도가 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.spd.get(uuid) + "]"));
		inv.setItem(4, ItemManager.buildItem(Material.IRON_INGOT, 1, ChatColor.AQUA + "ARM", "방어력이 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.arm.get(uuid) + "]"));
		inv.setItem(6, ItemManager.buildItem(Material.BLAZE_POWDER, 1, ChatColor.AQUA + "AS", "공격속도가 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.as.get(uuid) + "]"));
		inv.setItem(8, ItemManager.buildItem(Material.DIAMOND, 1, ChatColor.AQUA + "POINT", "잔여 스탯 포인트입니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.remain.get(uuid) + "]"));
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
    	UUID uuid = player.getUniqueId();
		if (e.getClickedInventory() == null) {
			return;
		}
		if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("스탯")){
            e.setCancelled(true);
            if(e.getCurrentItem() == null)
                return;
            else if(e.getCurrentItem().isSimilar(ItemManager.buildItem(Material.BOW, 1, ChatColor.AQUA + "STR", "공격력이 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.dmg.get(uuid) + "]"))) {
                if (Stat.remain.get(uuid) > 0) {
                	Stat.setDmg(player, Stat.dmg.get(uuid) + 1);
                	Stat.setRemain(player, Stat.remain.get(uuid) - 1);
                }
                else player.sendMessage("잔여 스탯이 부족합니다.");
            }
            else if(e.getCurrentItem().isSimilar(ItemManager.buildItem(Material.FEATHER, 1, ChatColor.AQUA + "DEX", "이동속도가 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.spd.get(uuid) + "]"))) {
                if (Stat.remain.get(uuid) > 0) {
                	Stat.setSpd(player, Stat.spd.get(uuid) + 1);
                	Stat.setRemain(player, Stat.remain.get(uuid) - 1);
                }
                else player.sendMessage("잔여 스탯이 부족합니다.");
            }
            else if(e.getCurrentItem().isSimilar(ItemManager.buildItem(Material.IRON_INGOT, 1, ChatColor.AQUA + "ARM", "방어력이 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.arm.get(uuid) + "]"))) {
            	if (Stat.remain.get(uuid) > 0) {
                	Stat.setArm(player, Stat.arm.get(uuid) + 1);
                	Stat.setRemain(player, Stat.remain.get(uuid) - 1);
                }
                else player.sendMessage("잔여 스탯이 부족합니다.");
            }
            else if(e.getCurrentItem().isSimilar(ItemManager.buildItem(Material.BLAZE_POWDER, 1, ChatColor.AQUA + "AS", "공격속도가 증가합니다.", ChatColor.LIGHT_PURPLE + "[" + Stat.as.get(uuid) + "]"))) {
            	if (Stat.remain.get(uuid) > 0) {
                	Stat.setAs(player, Stat.as.get(uuid) + 1);
                	Stat.setRemain(player, Stat.remain.get(uuid) - 1);
                }
                else player.sendMessage("잔여 스탯이 부족합니다.");
            }
            InventoryView inv = player.getOpenInventory();
            update(player, inv);
        }
	}
}