package ench.main;

import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

public class Commands implements CommandExecutor{

	private Enchanting plugin;
	
	public Commands (Enchanting plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender user, Command cmd, String label, String[] args) {
		if (args[0] == "help") {
			user.sendMessage("/epp get - получить зачарование предмета в руке в виде книги");
			return true;
		} 
		else if (args[0] == "get") {
			return getEnchantmentCommand(user);
		}
		return false;
	}
	
	private boolean getEnchantmentCommand(CommandSender user) {
		Player p = (Player) user;
		ItemStack item = p.getInventory().getItemInMainHand();
		if(item.getType().isAir()) {
			p.sendMessage(ChatColor.RED + "В руке должен быть предмет!");
			return true;
		}
		else if(item.getType().BOOK != null || !item.getEnchantments().isEmpty()) {
			p.sendMessage(ChatColor.RED + "Нету смысла в том, что бы переносить зачарования из книги в книгу!");
			return true;
		}
		else if(item.getEnchantments().isEmpty()) {
			p.sendMessage(ChatColor.RED + "На предмете должны быть зачарования!");
			return true;
		}
		
		@Nonnull Map<Enchantment, Integer> ench = item.getEnchantments();
		
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
				
		EnchantmentStorageMeta metachild = (EnchantmentStorageMeta) book.getItemMeta();

		ItemMeta metaparent = item.getItemMeta();

		
		for (Map.Entry<Enchantment, Integer> pair: ench.entrySet()) {
			metachild.addStoredEnchant(pair.getKey(), pair.getValue(), false);
			metaparent.removeEnchant(pair.getKey());

		}
		
		book.setItemMeta(metachild);
		item.setItemMeta(metaparent);
		p.getInventory().addItem(book);
		
		p.sendMessage(ChatColor.GREEN + "Успешно! Книга у вас в инвентаре!");
		return true;
	}
}
