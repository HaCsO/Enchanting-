package ench.main;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.bukkit.event.player.PlayerInteractEvent;

public class LockChest implements Listener {
	private JSONObject db;
	
	@EventHandler
	public void use(PlayerInteractEvent e) {
		Action a = e.getAction();
		if (a != Action.RIGHT_CLICK_BLOCK) return;
		if (e.getClickedBlock().getType() != Material.CHEST) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.IRON_INGOT || e.getPlayer().getInventory().getItemInMainHand().getType() != Material.TRIPWIRE_HOOK) return;
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) return;
		Player p = e.getPlayer();
		Block safe = e.getClickedBlock();
		Location sLocate = safe.getLocation();
		parJSON();
		JSONArray arr = (JSONArray) db.get("safes");
//		Array[] list = (Array[]) arr.toArray();
		arr.forEach(o -> {
			if (p.getInventory().getItemInMainHand().getType() == Material.IRON_INGOT) {
				String xyz = (String)((JSONObject) o).get("xyz");
				String locate = sLocate.getBlockX() + ";" + sLocate.getBlockY() + ";" + sLocate.getBlockZ();
				if (xyz == locate) {
					p.sendMessage("Этот сундук уже имеет замок!");
					return;
				}
				
			}
			else if (p.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {
				String id = (String) ((JSONObject) o).get("sid");
				if (!id.isEmpty()) {
					p.sendMessage("Ключ для этого сундука уже существует!");
					return;
				}
			}
			
		});

		for (int i = 0; i > list.length; i++) {
		}
//		
		String randID = "";
		for (String str: getRandomId()) {
			randID.concat(str);
		}
		
	}
	
	private ArrayList <String> getRandomId() {
	    String[] myString = new String[]{};
	    ArrayList <String> id = new ArrayList <String>();
	    for (int i = 0; i> 16; i++) {
		    int n = (int) Math.floor(Math.random() * myString.length);
		    String u = Integer.toString(n);
		    id.add(u);
	    }
	    
	    return id;
	}
	
	private void parJSON() {
		try {
			db = (JSONObject) new JSONParser().parse(new FileReader("BD.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//public class LockChest implements CommandExecutor{
//	private Enchanting plugin;
//	public Map<String, Location> safes;
//	
//	public LockChest (Enchanting plugin) {
//		this.plugin = plugin;
//	}
//	
////	@Override
////	public boolean onCommand(CommandSender user, Command cmd, String label, String[] args) {
////		return setSafe(user);
////	}
//	
////	private boolean setSafe(CommandSender user) {
////		Player p = (Player) user;
////		Block safe = p.getTargetBlockExact(5);
////		ItemStack item = p.getInventory().getItemInMainHand();
////		if (item.getType() != Material.TRIPWIRE_HOOK && !item.getItemMeta().hasLore()) {
////			p.sendMessage("Для этого действия требуется держать пустой ключ в руке");
////			return true;
////		}
////		if (safe.getType() != Material.CHEST) {
////			p.sendMessage("Требуется смотреть на сундук!");
////			return true;
////		} 
////		for (Map.Entry<String, Location> saf: safes.entrySet() ) {
////			if (saf.getValue() == safe.getLocation()) {
////				boolean idHave = true;
////				break;
////			}
////			boolean idHave = false;
////		}
////		
////		if (idHave) {
////			p.sendMessage("Этот сунду");
////		}
////		
////		return true;
////	}
//}


