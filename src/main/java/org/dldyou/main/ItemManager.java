package org.dldyou.main;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	
	public static ItemStack buildItem(Material type, int amount, String displayName, String... lore) {
		ItemStack stack = new ItemStack(type, amount);
		ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
		return stack;
	}
	public static ItemStack buildItem(Material type, int amount, String displayName, Enchantment enchant, int enchantlvl, boolean enchantRestrict, String... lore) {
	    ItemStack stack = new ItemStack(type, amount);
	    ItemMeta meta = stack.getItemMeta();
	    meta.setDisplayName(displayName);
	    meta.setLore(Arrays.asList(lore));
	    meta.addEnchant(enchant, enchantlvl, enchantRestrict);
	    stack.setItemMeta(meta);
	    return stack;
	}
}