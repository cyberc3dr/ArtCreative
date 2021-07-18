package creative.main;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerUtils
{
    protected static String[] forbidden;
    
    static {
        PlayerUtils.forbidden = new String[] { "\u0441\u0443\u043a\u0430", "\u0431\u043b\u044f\u0442\u044c", "\u043d\u0430\u0445\u0443\u0439", "\u0441\u0443\u043a\u0430\u0431\u043b\u044f\u0442\u044c", "\u043f\u0438\u0434\u0440\u0438\u043b\u0430", "suka", "blyat", "nahui", "nahuy", "sukablyat", "pidor", "pidrila", "\u043f\u0438\u0434\u0440", "pidr", "\u043f\u0438\u0434\u0440\u0438\u043b\u0430", "cuka", "syka", "cyka", "\u0445\u0443\u0439", "huy", "hui", "\u0433\u043e\u0432\u043d\u043e", "govno", "gavno", "\u0433\u0430\u0432\u043d\u043e", "fuck", "\u0444\u0430\u043a", "fak", "fack", "fuk", "ebalo", "\u0435\u0431\u0430\u043b\u043e", "\u0435\u0431\u0430\u043b", "ebal", "epal", "\u0435\u043f\u0430\u043b", "\u0443\u043a\u0430", "\u043b\u044f\u0442\u044c", "\u0438\u0434\u043e\u0440", "lox", "\u043b\u043e\u0445", "\u043f\u0438\u0434\u043e\u0440" };
    }
    
    public void clearAllEffects(Player p) {
        final List<Object> e = new ArrayList<Object>();
        e.add(PotionEffectType.ABSORPTION);
        e.add(PotionEffectType.BLINDNESS);
        e.add(PotionEffectType.CONFUSION);
        e.add(PotionEffectType.DAMAGE_RESISTANCE);
        e.add(PotionEffectType.FAST_DIGGING);
        e.add(PotionEffectType.FIRE_RESISTANCE);
        e.add(PotionEffectType.GLOWING);
        e.add(PotionEffectType.HARM);
        e.add(PotionEffectType.HEAL);
        e.add(PotionEffectType.HEALTH_BOOST);
        e.add(PotionEffectType.HUNGER);
        e.add(PotionEffectType.INCREASE_DAMAGE);
        e.add(PotionEffectType.INVISIBILITY);
        e.add(PotionEffectType.JUMP);
        e.add(PotionEffectType.LEVITATION);
        e.add(PotionEffectType.LUCK);
        e.add(PotionEffectType.NIGHT_VISION);
        e.add(PotionEffectType.POISON);
        e.add(PotionEffectType.REGENERATION);
        e.add(PotionEffectType.SATURATION);
        e.add(PotionEffectType.SLOW);
        e.add(PotionEffectType.SLOW_DIGGING);
        e.add(PotionEffectType.SPEED);
        e.add(PotionEffectType.UNLUCK);
        e.add(PotionEffectType.WATER_BREATHING);
        e.add(PotionEffectType.WEAKNESS);
        e.add(PotionEffectType.WITHER);
        for (final Object pet : e) {
            p.removePotionEffect((PotionEffectType)pet);
        }
    }
    
    public static String filter(String input) {
        for (int i = 0; i < PlayerUtils.forbidden.length; ++i) {
            final StringBuffer stars = new StringBuffer();
            for (int j = 0; j < PlayerUtils.forbidden[i].length(); ++j) {
                stars.append("*");
            }
            input = input.replaceAll("(?i)" + PlayerUtils.forbidden[i], stars.toString());
        }
        return input;
    }
    
    public List<Material> getBlocksWithGUI(){
    	List<Material> list = new ArrayList<Material>();
    	list.add(Material.CHEST);
    	list.add(Material.ENDER_CHEST);
    	list.add(Material.CAULDRON);
    	list.add(Material.BEACON);
    	list.add(Material.ENCHANTMENT_TABLE);
    	list.add(Material.FURNACE);
    	list.add(Material.DISPENSER);
    	list.add(Material.DROPPER);
    	
		return list;
    }
    
    public static String splitPlayers(String input, List<String> list, String endColor) {
        for (int i = 0; i < list.size(); ++i) {
            final StringBuffer stars = new StringBuffer();
            stars.append("§e");
            for (int j = 0; j < list.get(i).length(); ++j) {
            	stars.append(list.get(i).charAt(j));
            }
            stars.append(endColor);
            input = input.replaceAll("(?i)" + PlayerUtils.forbidden[i], stars.toString());
        }
        return input;
    }
}
