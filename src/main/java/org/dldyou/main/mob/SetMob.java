package org.dldyou.main.mob;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class SetMob implements Listener {
    
    public void spawnEliteZombie(Location loc) {
		setEliteMobStat((LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE), "정예 좀비");
	}
	public void spawnEliteSkeleton(Location loc) {
		setEliteMobStat((LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.SKELETON), "정예 스켈레톤");
	}
	public void setEliteMobStat(LivingEntity entity, String name) {
		Random random = new Random();
    	entity.setCustomName(name);
    	entity.setCustomNameVisible(false);
    	entity.setMaxHealth(50.0 + random.nextDouble(50.0));
    	entity.setHealth(entity.getMaxHealth());
    	entity.getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
    	entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8.0);
    	entity.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(10.0);
    	entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.11);
    }
	@EventHandler
	public void getMobDead(EntityDeathEvent e) {
		e.setDroppedExp(50 + (int) e.getEntity().getMaxHealth());
		Entity entity = (Entity) e.getEntity();
		World w = entity.getWorld();
		if(e.getEntity().getCustomName() == "정예 좀비" || e.getEntity().getCustomName() == "정예 스켈레톤") {
			Random random = new Random();
			int r = random.nextInt(100);
			if(r < 3) {
				w.dropItem(entity.getLocation(), new ItemStack(Material.DIAMOND, random.nextInt(1)));
				w.dropItem(entity.getLocation(), new ItemStack(Material.GOLD_INGOT, random.nextInt(1)));
			}
			else if(r < 15) {
				w.dropItem(entity.getLocation(), new ItemStack(Material.GOLD_INGOT, random.nextInt(1)));
			}
			else if(r < 90){
				w.dropItem(entity.getLocation(), new ItemStack(Material.JACK_O_LANTERN, 1));
			}
			else {
				
			}
		}
	}
	
	@EventHandler
	public void getMobSpawn(EntitySpawnEvent e) {
		Random random = new Random();
		if (e.getEntityType().equals(EntityType.ZOMBIE)) {
			if (random.nextInt(1000) > 35) {
				spawnEliteZombie(e.getLocation());
			}
		}
		else if (e.getEntityType().equals(EntityType.SKELETON)) {
			if (random.nextInt(1000) > 35) {
				spawnEliteSkeleton(e.getLocation());
			}
		}
	}
}