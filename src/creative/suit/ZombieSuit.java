package creative.suit;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ZombieSuit implements Suit {
	public String getName(){
		return "zombie";
	}
	public String getDisplayName(){
		return "§aЗомби";
	}
	public void setSuit(PlayerInventory inv){
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		headMeta.setOwner("zombie");
		headMeta.setDisplayName("§2Голова-зомби");
		head.setItemMeta(headMeta);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplateMeta.setColor(Color.GREEN);
		chestplateMeta.setDisplayName("§2Нагрудник-зомби");
		chestplate.setItemMeta(chestplateMeta);
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsMeta.setColor(Color.GREEN);
		leggingsMeta.setDisplayName("§2Штаны-зомби");
		leggings.setItemMeta(leggingsMeta);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(Color.GREEN);
		bootsMeta.setDisplayName("§2Ботинки-зомби");
		boots.setItemMeta(bootsMeta);
		inv.setHelmet(head);
		inv.setChestplate(chestplate);
		inv.setLeggings(leggings);
		inv.setBoots(boots);
	}
	public SuitType getType(){
		return SuitType.START;
	}
	public ItemStack getIcon(){
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwner("zombie");
		return item;
	}
	public int getPrice(){
		return 0;
	}
}
