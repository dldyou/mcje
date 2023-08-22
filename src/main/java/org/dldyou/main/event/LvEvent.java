package org.dldyou.main.event;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerHideEntityEvent;
import org.dldyou.main.Stat;

public class LvEvent implements Listener {
	
	@EventHandler
    public void lv(PlayerExpChangeEvent e) {
    	Player player = (Player) e.getPlayer();
    	UUID uuid = player.getUniqueId();
    	Integer xp = e.getAmount();
    	
    	if (xp > 0) {
    		Stat.addXp(player, xp);
    	}
    }
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Material blockType = e.getBlock().getType();
		Player player = e.getPlayer();
		
		if (e.getExpToDrop() == 0) return;
		int additionalExp = 0;
		switch (blockType) {
		case COAL_ORE:
		case COPPER_ORE:
		case DEEPSLATE_COAL_ORE:
		case DEEPSLATE_COPPER_ORE:
		case NETHER_GOLD_ORE:
			additionalExp = 3;
			break;
		case IRON_ORE:
		case GOLD_ORE:
		case DEEPSLATE_IRON_ORE:
		case DEEPSLATE_GOLD_ORE:
		case NETHER_QUARTZ_ORE:
			additionalExp = 5;
			break;
		case LAPIS_ORE:
		case REDSTONE_ORE:
		case DEEPSLATE_LAPIS_ORE:
		case DEEPSLATE_REDSTONE_ORE:
			additionalExp = 10;
			break;
		case EMERALD_ORE:
		case DEEPSLATE_EMERALD_ORE:
			additionalExp = 50;
			break;
		case DIAMOND_ORE:
		case DEEPSLATE_DIAMOND_ORE:
			additionalExp = 100;
			break;
		case ANCIENT_DEBRIS:
			additionalExp = 1000;
			break;
		}
		if(additionalExp != 0)
			Stat.addXp(player, additionalExp);
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player player = (Player) e.getEntity().getKiller();
			EntityType entityType = e.getEntityType();
			
			int additionalExp = 0;
			switch (entityType) {
			case SLIME:
			case MAGMA_CUBE:
			case ENDERMITE:
			case SILVERFISH:
				additionalExp = 2;
				break;
			case ZOMBIE:
			case ZOMBIE_VILLAGER:
			case HUSK:
			case DROWNED:
			case SKELETON:
			case ZOMBIFIED_PIGLIN:
			case CREEPER:
			case SPIDER:
			case IRON_GOLEM:
			case BLAZE:
			case STRAY:
				additionalExp = 10;
				break;
			case CAVE_SPIDER:
			case PIGLIN:
			case PHANTOM:
			case WITCH:
			case SHULKER:
			case GUARDIAN:
			case VINDICATOR:
			case EVOKER:
			case VEX:
			case PILLAGER:
				additionalExp = 20;
				break;
			case ENDERMAN:
			case WITHER_SKELETON:
			case GHAST:
			case HOGLIN:
			case ZOGLIN:
				additionalExp = 30;
				break;
			case ELDER_GUARDIAN:
			case RAVAGER:
				additionalExp = 50;
				break;
			case ENDER_DRAGON:
			case WITHER:
				additionalExp = 5000;
				break;
			case WARDEN:
				additionalExp = 10000;
				break;
			}
			if(additionalExp != 0)
				Stat.addXp(player, additionalExp);
		}
	}
}