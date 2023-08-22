package org.dldyou.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.dldyou.main.commands.GoHome;
import org.dldyou.main.commands.OpenStatGUI;
import org.dldyou.main.commands.ResetStat;
import org.dldyou.main.event.*;
import org.dldyou.main.mob.SetMob;

import net.md_5.bungee.api.ChatColor;
 
//public class 클래스명 extends JavaPlugin implements Listener
public class Main extends JavaPlugin implements Listener {

	Random r = new Random();
	ConsoleCommandSender consol = Bukkit.getConsoleSender();
	private final File f_dmg = new File(getDataFolder(), "/stat_dmg.txt");
	private final File f_spd = new File(getDataFolder(), "/stat_spd.txt");
	private final File f_arm = new File(getDataFolder(), "/stat_arm.txt");
	private final File f_as = new File(getDataFolder(), "/stat_as.txt");
	private final File f_rem = new File(getDataFolder(), "/stat_rem.txt");
	private final File f_lv = new File(getDataFolder(), "/stat_lv.txt");
	private final File f_xp = new File(getDataFolder(), "/stat_xp.txt");
	private int seconds = 0;
	private void fileProcess(File f, HashMap<UUID, Integer> map) {
		makeFile(f);
		mapToFile(f, map);
		fileToMap(f, map);
	}
	
    public void onEnable(){
    	consol.sendMessage(ChatColor.BLUE + "[RPG] 플러그인이 활성화 중입니다.");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new StatClickEvent(), this);
        getServer().getPluginManager().registerEvents(new LvEvent(), this);
        getServer().getPluginCommand("stat").setExecutor(new OpenStatGUI());
        getServer().getPluginCommand("home").setExecutor(new GoHome(this));
        // getServer().getPluginCommand("reset").setExecutor(new ResetStat());
        
        fileProcess(f_dmg, Stat.dmg);
        fileProcess(f_spd, Stat.spd);
        fileProcess(f_arm, Stat.arm);
        fileProcess(f_as, Stat.as);
        fileProcess(f_rem, Stat.remain);
        fileProcess(f_lv, Stat.lv);
        fileProcess(f_xp, Stat.xp);
        
        startTick();
    }
    
    @Override 
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args) {
    	if (sender instanceof Player) {
    		//
    	}
    	return false;
    }
    
    @SuppressWarnings("deprecation")
	public void startTick() {
    	Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
    		
    		@Override 
    		public void run() {
    			seconds = (seconds + 1) % 86400;
    		}
    	}, 20 * 1, 20 * 1);
    }
    
	public void onDisable(){
    	consol.sendMessage(ChatColor.BLUE + "[RPG] 플러그인이 비활성화 됩니다.");
    }
    
    @EventHandler
    public void PlayerQuitEvent (PlayerQuitEvent e) {
    	String msg = "님이 퇴장하셨습니다.";
    	Player player = (Player) e.getPlayer();
    	e.setQuitMessage(player.getDisplayName() + msg);
    }
   
    public void makeFile(File f) {
		if (!f.exists() || !f.isFile()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
    @SuppressWarnings("deprecation")
	public void mapToFile(File f, HashMap<UUID, Integer> map) {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				try {
					consol.sendMessage(ChatColor.GREEN + "데이터를 저장 했습니다");
					FileWriter writer = new FileWriter(f, false);
					for(UUID uuid : map.keySet()){
						writer.write(uuid.toString()+"|"+map.get(uuid)+"\n");
					}
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 20 * 60, 20 * 60);
		
	}
    
    public void fileToMap(File f, HashMap<UUID, Integer> map) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String fileLine = null;
			while ((fileLine = reader.readLine()) != null) {

				UUID uuid = UUID.fromString(fileLine.split("\\|")[0]);
				String str = fileLine.split("\\|")[1];

				map.put(uuid, Integer.parseInt(str));
			}
			reader.close();
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		} catch (IOException e4) {
			e4.printStackTrace();
		}
	}
}