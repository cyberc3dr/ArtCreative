package creative.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CodingUtils {
	
	public String seperator = "::";
	
	public String col(String s){
    	return ChatColor.translateAlternateColorCodes("&".charAt(0), s);
    }
	
	public List<ItemStack> getCodeBlocks(List<String> list){
		
		List<ItemStack> is = new ArrayList<ItemStack>();
		
		for(String s : list){
			String[] parts = s.split(seperator);
			ItemStack block = new ItemStack(Material.GLASS);
			ItemMeta blockMeta = block.getItemMeta();
			List<String> lore = new ArrayList<String>();
			if(parts[0].equalsIgnoreCase("action")){
				blockMeta.setDisplayName("§fСделать игроку");
				block.setType(Material.COBBLESTONE);
				if(parts[1].equalsIgnoreCase("sendmsg")){
					lore.add("§7Отправить сообщение");
					lore.add("§f" + parts[2].toString().replace("&", "\u00a7"));
				}else if(parts[1].equalsIgnoreCase("additem")){
					lore.add("§7Выдать предмет");
					ItemStackUtils isu = new ItemStackUtils();
					String st;
					if(parts[2].equalsIgnoreCase(" ")){
						lore.add("§8<пусто>");
					}else{
						ItemStack isi = null;
						try {
							isi = isu.StringToItemStack(parts[2]);
						} catch (IOException e) {
							e.printStackTrace();
						}
						st = isi.getItemMeta().getDisplayName() + "§f * " + isi.getAmount();
						lore.add(st);
					}
				}else if(parts[1].equalsIgnoreCase("sendtitle")){
					lore.add("§7Отправить текст на экран (титл)");
					if( parts.length == 3 ){
						lore.add("§f" + col(parts[2]));
					}else{
						lore.add("§f" + col(parts[2]) + "§f, " + col(parts[3]));
					}
				}else{
					lore.add("§8<пусто>");
				}
	        	lore.add("§7");
	        	lore.add("§7ЛКМ - чтобы настроить блок.");
	        	lore.add("§7ПКМ - чтобы удалить блок.");
			}else if(parts[0].equalsIgnoreCase("if")){
				blockMeta.setDisplayName("§fЕсли игрок");
				block.setType(Material.WOOD);
				lore.add("§8<пусто>");
	        	lore.add("§7");
	        	lore.add("§7ЛКМ - чтобы настроить блок.");
	        	lore.add("§7ПКМ - чтобы удалить блок.");
			}else if(parts[0].equalsIgnoreCase("worldAction")){
				blockMeta.setDisplayName("§fДействие мира");
				block.setType(Material.NETHER_BRICK);
				lore.add("§8<пусто>");
	        	lore.add("§7");
	        	lore.add("§7ЛКМ - чтобы настроить блок.");
	        	lore.add("§7ПКМ - чтобы удалить блок.");
			}else{
				blockMeta.setDisplayName("§fНеизвестный блок");
			}
			blockMeta.setLore(lore);
			block.setItemMeta(blockMeta);
			is.add(block);
		}
		
		return is;
	}
	
	public boolean execute(Player p, List<String> list, Object e){
		for(String s : list){
			String[] parts = s.split(seperator);
			if(parts[0].equalsIgnoreCase("action")){
				if(parts[1].equalsIgnoreCase("sendmsg")){
					String msg = parts[2];
					p.sendMessage("§f" + msg.replace("&", "\u00a7"));
				}
				if(parts[1].equalsIgnoreCase("sendtitle")){
					String msg = parts[2];
					if( parts.length == 3 ){
						p.sendTitle("§f" + col(parts[2]), "");
					}else{
						p.sendTitle("§f" + col(parts[2]), "§f" + col(parts[3]));
					}
				}
				if(parts[1].equalsIgnoreCase("additem")){
					String out = parts[2];
					ItemStackUtils isu = new ItemStackUtils();
					ItemStack is = null;
					try {
						is = isu.StringToItemStack(out);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.getInventory().addItem(is);
				}
			}
		}
		return false;
	}
	
}
