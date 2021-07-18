package creative.suit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface Suit {
	public String getName();
	public void setSuit(PlayerInventory inv);
	public SuitType getType();
	public ItemStack getIcon();
	public int getPrice();
}
