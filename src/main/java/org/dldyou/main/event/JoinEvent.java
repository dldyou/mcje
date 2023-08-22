package org.dldyou.main.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.dldyou.main.Stat;

import net.md_5.bungee.api.ChatColor;

public class JoinEvent implements Listener {
	
	private void addGlowEffect(Player player) {
    	player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, PotionEffect.INFINITE_DURATION, 0, false, false, false));
	}
	
	private void addStatAdditionalAbility(Player player) {
		
	}
	
	@EventHandler
    public void Join(PlayerJoinEvent e) {
    	Player player = (Player) e.getPlayer();
    	String msg = "님이 접속하셨습니다.";
    	Stat.setStat(player);
    	e.setJoinMessage(player.getDisplayName() + msg);
    	
    	player.sendMessage(ChatColor.AQUA + "[rpg] 서버에 들어오신 것을 환영합니다!");
    	player.sendMessage(ChatColor.AQUA + "[rpg] /stat 명령어를 통해 스탯을 증가시킬 수 있습니다.");
    	player.sendMessage(ChatColor.AQUA + "[rpg] 패치노트 : https://github.com/dldyou/mcje");
    	addGlowEffect(player);
    }
	
	@EventHandler
	public void Respawn(PlayerRespawnEvent e) {
		Player player = (Player) e.getPlayer();
		addGlowEffect(player);
	}
}