package ench.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Enchanting extends JavaPlugin {
	public void onEnable() {
		getLogger().info("succesful started!");
		getCommand("epp").setExecutor(new Commands(this));
//		getCommand("epp").setExecutor(new LockChest(this));
		lock();
		key();
	}
	
	public void onDisable() {
		getLogger().info("plugin disabled.");
	}
	
	private void lock() {
		ItemStack lock = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = lock.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Замок для сундука");
		List<String> lore = new ArrayList<String>();
		lore.add("Chest lock");
		meta.setLore(lore);
		lock.setItemMeta(meta);
		
		ShapedRecipe rec = new ShapedRecipe(lock);
		rec.shape(new String[] {"A A", "ABA", "AAA"});
		rec.setIngredient('A', Material.IRON_INGOT);
		rec.setIngredient('B', Material.REDSTONE_WIRE);
		Bukkit.getServer().addRecipe(rec);
	
	}
	private void key() {
		ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta meta = key.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Ключ от сундука");
		List<String> lore = new ArrayList<String>();
		lore.add("Chest key");
		meta.setLore(lore);		
		key.setItemMeta(meta);
		
		ShapedRecipe rec = new ShapedRecipe(key);
		rec.shape(new String[] {"A A", "ABA", "AAA"});
		rec.setIngredient('A', Material.IRON_INGOT);
		rec.setIngredient('B', Material.REDSTONE_WIRE);
		Bukkit.getServer().addRecipe(rec);
	
	}

}
