package creative.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ItemStackUtils {
	
	public String ItemStackToString(ItemStack is){
		String s = "{";
		s = s + is.getTypeId();
		s = s + ";;";
		s = s + is.getAmount();
		s = s + ";;";
		ItemMeta im = is.getItemMeta();
		MaterialData md = is.getData();
		s = s + ((int) md.getData());
		s = s + ";;";
		s = s + im.getDisplayName();
		s = s + ";;";
		s = s + im.getLocalizedName();
		s = s + ";;";
		List<String> lore = im.getLore();
		if(!im.hasLore()){
			s = s + " ";
		}else{
			for(int i = 0; i < lore.size(); i++){
				if( i == 0 ){
					s = s + lore.get(i);
				}else{
					s = s + "||" + lore.get(i);
				}
			}
		}
		s = s + ";;";
		if(im.isUnbreakable()){
			s = s + "1";
		}else{
			s = s + "0";
		}
		s = s + "}";
		return s;
	}
	
	public ItemStack StringToItemStack(String out) throws IOException {
		String s = StringUtils.substringBetween(out, "{", "}");
		String[] ss = s.split(";;");
		ItemStack is = new ItemStack(Material.getMaterial(Integer.parseInt(ss[0])), Integer.parseInt(ss[1]), (short) Integer.parseInt(ss[2]));
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ss[3]);
		im.setLocalizedName(ss[4]);
		if(ss[5].equalsIgnoreCase(" ") ){
			List<String> lore = null;
			im.setLore(lore);
		}else{
			List<String> lore = ArrayToListString(ss[5].split("||"));
			im.setLore(lore);
		}
		if( ss[6].equalsIgnoreCase("0")){
			im.setUnbreakable(false);
		}else if(ss[6].equalsIgnoreCase("1")){
			im.setUnbreakable(true);
		}else{
			im.setUnbreakable(false);
		}
		return is;
	}
	
	public List<String> ArrayToListString(String[] sa){
		List<String> list = new ArrayList<String>();
		for(String s : sa){
			list.add(s);
		}
		return list;
	}
	
	public List<ItemFlag> SetToListItemFlag(Set<ItemFlag> sa){
		List<ItemFlag> list = new ArrayList<ItemFlag>();
		for(ItemFlag ig : list){
			list.add(ig);
		}
		return list;
	}
	
}
