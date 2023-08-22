package org.dldyou.main;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Stat {
	ConsoleCommandSender consol = Bukkit.getConsoleSender();
	public static HashMap<UUID, Integer> dmg = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> spd = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> arm = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> as = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> remain = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> lv = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> xp = new HashMap<UUID, Integer>();
	public static double baseDmg = 1.0;
	public static double baseSpd = 0.100015;
	public static double baseArm = 0.0;
	public static double baseAs = 4.0;
	
	public static double coDmg = 0.05;
	public static double coSpd = 0.0005;
	public static double coArm = 0.05;
	public static double coAs = 0.025;
	
	public static void setRemain(Player player) {
		UUID uuid = player.getUniqueId();
		if(remain.containsKey(uuid)) 
			remain.put(uuid, remain.get(uuid));
		else remain.put(uuid, 20);
	}
	
	public static void setRemain(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
		remain.put(uuid, val);
	}
	
	public static void setDmg(Player player) {
		UUID uuid = player.getUniqueId();
    	if(dmg.containsKey(uuid)) 
    		dmg.put(uuid,  dmg.get(uuid));
    	else dmg.put(uuid, 0);
    	player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDmg + coDmg * dmg.get(uuid));
	}
	
	public static void setDmg(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
		dmg.put(uuid, val);
		player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDmg + coDmg * dmg.get(uuid));
	}
	
	public static void setSpd(Player player) {
		UUID uuid = player.getUniqueId();
    	if(spd.containsKey(uuid)) 
    		spd.put(uuid,  spd.get(uuid));
    	else spd.put(uuid, 0);
		player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(baseSpd + coSpd * spd.get(uuid));
	}

	public static void setSpd(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
		spd.put(uuid, val);
		player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(baseSpd + coSpd * spd.get(uuid));
	}
	
	public static void setArm(Player player) {
		UUID uuid = player.getUniqueId();
    	if(arm.containsKey(uuid)) 
    		arm.put(uuid,  arm.get(uuid));
    	else arm.put(uuid, 0);
		player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(baseArm + coArm * arm.get(uuid));
	}

	public static void setArm(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
		arm.put(uuid, val);
		player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(baseArm + coArm * arm.get(uuid));
	}
	
	public static void setAs(Player player) {
		UUID uuid = player.getUniqueId();
    	if(as.containsKey(uuid)) 
    		as.put(uuid,  as.get(uuid));
    	else as.put(uuid, 0);
		player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(baseAs + coAs * as.get(uuid));
	}
	
	public static void setAs(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
    	as.put(uuid, val);
		player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(baseAs + coAs * as.get(uuid));
	}
	
	public static void setStat(Player player) {
		setDmg(player);
		setSpd(player);
		setArm(player);
		setAs(player);
		setRemain(player);
		setLv(player);
		setXp(player);
	}
	
	public static void setLv(Player player) {
		UUID uuid = player.getUniqueId();
    	if(lv.containsKey(uuid)) 
    		lv.put(uuid,  lv.get(uuid));
    	else lv.put(uuid, 1);
	}
	
	public static void setLv(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
    	lv.put(uuid, val);
	}
	
	public static void setXp(Player player) {
		UUID uuid = player.getUniqueId();
		if(xp.containsKey(uuid)) 
			xp.put(uuid, xp.get(uuid));
		else xp.put(uuid, 0);
	}
	
	private static int getNextXp(Integer LV) {
		return (int) (LV * 5 + Math.floor(LV / 10) * 50 + Math.floor(LV / 100) * 500);
	}
	
	public static void addXp(Player player, Integer val) {
		UUID uuid = player.getUniqueId();
		Integer LV = lv.get(uuid);
		Integer XP = xp.get(uuid) + val, NXP = getNextXp(LV);
		
		while (XP >= NXP) {
			LV += 1; 
			XP -= NXP;
			NXP = getNextXp(LV);
			
			player.sendMessage(ChatColor.AQUA + "레벨이 증가합니다! " + ChatColor.RED + LV.toString() + "LV");
			setRemain(player, remain.get(uuid) + 5);
		}
		lv.put(uuid, LV);
		xp.put(uuid, XP);
		String msg = "+" + val.toString() + "[" + XP.toString() + "/" + NXP.toString() + "]";
		player.setOp(true);
		player.performCommand("title @s actionbar " + '"' + msg + '"');
		player.setOp(false);
	}
}