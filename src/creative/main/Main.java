package creative.main;

import net.md_5.bungee.api.ChatColor; 

import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;

import org.bukkit.WorldBorder;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.WorldType;
import org.bukkit.WorldCreator;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Vehicle;
import org.bukkit.Sound;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Location;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.GameMode;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.World;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.inventory.Inventory;
import org.bukkit.event.Listener;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import creative.suit.Suit;
import creative.suit.SuitType;
import creative.suit.ZombieSuit;

public class Main extends JavaPlugin implements Listener {
	private Inventory worlds;
    private Inventory myworld;
    private Inventory startMyWorld;
    private Inventory startedMyWorld;
    private Inventory generateMyWorld;
    private Inventory wOptions;
    private Inventory wPlayersList;
    private Inventory donate;
    private Inventory cosm;
    private Inventory gadjets;
    private Inventory suits;
    private Inventory worldSet;
    private Inventory worldActions;
    private Inventory worldTime;
    private Inventory playersBuild;
    private Inventory playersKick;
    private Inventory gameRules;
    private Inventory KitPvP;
    private Inventory worldBorder;
    private Inventory playersFly;
    private Inventory social;
    private Inventory playersBan;
    private Inventory playersBanDel;
    private Inventory playersWhite;
    private Inventory playersWhiteDel;
    private Inventory events;
    private Inventory code;
    private Inventory blockSet;
    private Inventory plMetod;
    private Inventory plMetodKom;
    private Inventory plMetodInv;
    private Inventory getItem;
    private Inventory topWorlds;
    public List<String> worldsList;
    public List<String> gamesList;
    public List<Suit> suitList;
    public boolean tech = false;
    public String split = "//split//";
    private String vkurl = "vk.com/artgameserv";
    private String discordurl = "discord.gg/nVj2PBN";
    private String siteurl = "artgameshop.mcdonate.ru";
    private HashMap<String, String> mp = new HashMap<String, String>();
    private HashMap<String, Object> mpb = new HashMap<String, Object>();
    private HashMap<String, Object> mpt = new HashMap<String, Object>();
    private HashMap<String, Object> mpn = new HashMap<String, Object>();
    
    public Main() {
        worldsList = new ArrayList<String>();
        gamesList = new ArrayList<String>();
        suitList = new ArrayList<Suit>();
    }
    
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        getLogger().info("enabled.");
        Bukkit.getPluginManager().registerEvents(this, this);
        gamesList.add("build");
        gamesList.add("kitpvp");
        gamesList.add("game");
        gamesList.add("parkour");
        gamesList.add("coding");
        loadSuits();
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        for(int i = 0; i<players.size(); i++){
        	players.get(i).kickPlayer("Рестарт.");
        }
    }
    
    public void onDisable() {
        this.getLogger().info("disabled.");
        
    }
    
    public void loadSuits(){
    	suitList.add(new ZombieSuit());
    }
    
    public static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; ++i) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            dir.delete();
        }
        else {
            dir.delete();
        }
    }
    
    public String col(String s){
    	return ChatColor.translateAlternateColorCodes("&".charAt(0), s);
    }
    
    public String name(Player p){
    	return p.getName();
    }
    
    public String codeEvent(Player p){
    	return getConfig().getString("players." + name(p) + ".options.codeEvent");
    }
    
    public void openEvents(Player p) {
        this.events = Bukkit.createInventory(null, 27, "Выбери событие игрока");
        ItemStack ev = new ItemStack(Material.DIAMOND);
        ItemMeta evMeta = ev.getItemMeta();
        evMeta.setDisplayName("§aВход игрока");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Когда игрок заходит в мир.");
        evMeta.setLore(lore);
        ev.setItemMeta(evMeta);
        this.events.setItem(0, ev);
        ItemStack ev2 = new ItemStack(Material.DIAMOND);
        ItemMeta evMeta2 = ev2.getItemMeta();
        evMeta2.setDisplayName("§bВыход игрока");
        List<String> lore2 = new ArrayList<String>();
        lore2.add("§7Когда игрок выходит из мира.");
        evMeta2.setLore(lore2);
        ev2.setItemMeta(evMeta2);
        this.events.setItem(1, ev2);
        p.openInventory(this.events);
    }
    
    public void openCode(Player p, String event) {
        String name = "";
        List<String> list = new ArrayList<String>();
        if (event.equalsIgnoreCase("join")) {
            name = "Вход игрока";
            mp.put(name(p), "join");
            if(!getConfig().contains("players." + p.getName() + ".code.join")){
            	getConfig().set("players." + p.getName() + ".code.join", list);
            	saveConfig();
            }else{
            	list = getConfig().getStringList("players." + p.getName() + ".code.join");
            }
        }else if (event.equalsIgnoreCase("quit")) {
            name = "Выход игрока";
            mp.put(name(p), "quit");
            if(!getConfig().contains("players." + p.getName() + ".code.quit")){
            	getConfig().set("players." + p.getName() + ".code.quit", list);
            	saveConfig();
            }else{
            	list = getConfig().getStringList("players." + p.getName() + ".code.quit");
            }
        }
        CodingUtils cu = new CodingUtils();
    	List<ItemStack> is = cu.getCodeBlocks(list);
        code = Bukkit.createInventory(null, 45, "Код: " + name);
        ItemStack block = new ItemStack(Material.COBBLESTONE);
        ItemMeta blockMeta = block.getItemMeta();
        blockMeta.setDisplayName("§fСделать игроку...");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Команды для игрока");
        lore.add("§7Например: §8Отправить сообщение, выдать предмет");
        blockMeta.setLore(lore);
        block.setItemMeta(blockMeta);
        code.setItem(36, block);
        
        ItemStack block1 = new ItemStack(Material.WOOD);
        ItemMeta blockMeta1 = block1.getItemMeta();
        blockMeta1.setDisplayName("§fЕсли игрок...");
        List<String> lore1 = new ArrayList<String>();
        lore1.add("§7Проверка чего либо у игрока");
        lore1.add("§7Напоминание: §8Чтобы закончить блок кода 'Если' - поставьте ");
        lore1.add("§8в конце 'Конец блока'");
        blockMeta1.setLore(lore1);
        block1.setItemMeta(blockMeta1);
        code.setItem(37, block1);
        
        ItemStack block2 = new ItemStack(Material.NETHER_BRICK);
        ItemMeta blockMeta2 = block2.getItemMeta();
        blockMeta2.setDisplayName("§fДействие с миром");
        List<String> lore2 = new ArrayList<String>();
        lore2.add("§7Команды для мира");
        lore2.add("§7Например: §8Включить PvP");
        blockMeta2.setLore(lore2);
        block2.setItemMeta(blockMeta2);
        code.setItem(38, block2);
        
        for(int i = 0; i < is.size(); i++){
    		code.setItem(i,is.get(i));
    	}
        
        p.openInventory(code);
        
    }
    
    public int getPlayerGroupId(Player p) {
        PermissionUser player = PermissionsEx.getUser(p);
        if (player.inGroup("admin")) {
            return 6;
        }
        if (player.inGroup("supporta")) {
            return 5;
        }
        if (player.inGroup("supportb")) {
            return 5;
        }
        if (player.inGroup("supportc")) {
            return 5;
        }
        if (player.inGroup("ultra")) {
            return 4;
        }
        if (player.inGroup("deluxe")) {
            return 3;
        }
        if (player.inGroup("lord")) {
            return 2;
        }
        if (player.inGroup("premium")) {
            return 1;
        }
        return 0;
    }
    
    public int getPlayerGroupId(String p) {
        PermissionUser player = PermissionsEx.getUser(p);
        if (player.inGroup("admin")) {
            return 6;
        }
        if (player.inGroup("supporta")) {
            return 5;
        }
        if (player.inGroup("supportb")) {
            return 5;
        }
        if (player.inGroup("supportc")) {
            return 5;
        }
        if (player.inGroup("ultra")) {
            return 4;
        }
        if (player.inGroup("deluxe")) {
            return 3;
        }
        if (player.inGroup("lord")) {
            return 2;
        }
        if (player.inGroup("premium")) {
            return 1;
        }
        return 0;
    }
    
    public void sendWorldMessage(String worldName, String message) {
        World w = Bukkit.getWorld(worldName);
        List<Player> playersList = w.getPlayers();
        for (int v = 0; v < playersList.size(); ++v) {
            Player p = playersList.get(v);
            p.sendMessage(message);
        }
    }
    
    public void sendWorldMessage(World world, String message) {
        World w = world;
        List<Player> playersList = w.getPlayers();
        for (int v = 0; v < playersList.size(); ++v) {
            Player p = playersList.get(v);
            p.sendMessage(message);
        }
    }
    
    public void openWorldOptions(Player p) {
        World w = p.getLocation().getWorld();
        this.worldSet = Bukkit.createInventory(null, 45, "Настройки мира");
        String getPlName = name(p);
		ItemStack item = new ItemStack(Material.COMPASS);
		ItemMeta itemMeta = item.getItemMeta();
		if (this.getPlayerGroupId(getPlName) > 1) {
            itemMeta.setDisplayName("§f" + col(getConfig().getString("players." + getPlName + ".worldName").replace("&", "§")));
        }
        else {
            itemMeta.setDisplayName("§f" + getConfig().getString("players." + getPlName + ".worldName"));
        }
		itemMeta.setLocalizedName(getPlName);
		List<String> lore = new ArrayList<String>();
		if (Bukkit.getPlayer(getPlName) == null) {
            lore.add("§fСоздатель: §7" + getPlName + " §8(Не в сети)");
        }else{
        	lore.add("§fСоздатель: §7" + Bukkit.getPlayer(getPlName).getDisplayName());
        }
		int resultLike = getLike(getPlName);
		if(resultLike == 0){
        	lore.add("§fОценка: §70");
        }else if(resultLike > 0){
        	lore.add("§fОценка: §a+" + resultLike);
        }else if(resultLike < 0){
        	lore.add("§fОценка: §c" + resultLike);
        }
		lore.add("§fВ топе: §e" + (getWorldPosition(getPlName)+1) + "§7 место");
		lore.add("§f");
		lore.add("§7ЛКМ - Для сохранения/закрытия мира");
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
        this.worldSet.setItem(10, item);
        ItemStack item2 = new ItemStack(Material.WATCH);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName("§f\u0418\u0433\u0440\u043e\u0432\u043e\u0435 \u0432\u0440\u0435\u043c\u044f");
        List<String> lore2 = new ArrayList<String>();
        lore2.add("§7\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u0432\u0440\u0435\u043c\u0435\u043d\u0438 \u0441\u0443\u0442\u043e\u043a \u0432 \u0438\u0433\u0440\u0435.");
        itemMeta2.setLore(lore2);
        item2.setItemMeta(itemMeta2);
        this.worldSet.setItem(11, item2);
        ItemStack item3 = new ItemStack(Material.ENDER_PEARL);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName("§f\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u0442\u043e\u0447\u043a\u0443 \u0441\u043f\u0430\u0432\u043d\u0430");
        List<String> lore3 = new ArrayList<String>();
        lore3.add("§7\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u0442\u043e\u0447\u043a\u0443 \u0432\u043e\u0437\u0440\u043e\u0436\u0434\u0435\u043d\u0438\u044f/\u0441\u043f\u0430\u0432\u043d\u0430 \u0433\u0434\u0435 \u0432\u044b \u0441\u0442\u043e\u0438\u0442\u0435.");
        itemMeta3.setLore(lore3);
        item3.setItemMeta(itemMeta3);
        this.worldSet.setItem(12, item3);
        ItemStack item4 = new ItemStack(Material.BRICK);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName("§fСтроительство игрока");
        List<String> lore4 = new ArrayList<String>();
        lore4.add("§7Управление правами строительства игроков.");
        itemMeta4.setLore(lore4);
        item4.setItemMeta(itemMeta4);
        this.worldSet.setItem(13, item4);
        ItemStack item5 = new ItemStack(Material.SKULL_ITEM);
        ItemMeta itemMeta5 = item5.getItemMeta();
        itemMeta5.setDisplayName("§f\u041a\u0438\u043a \u0438\u0433\u0440\u043e\u043a\u0430");
        List<String> lore5 = new ArrayList<String>();
        lore5.add("§7\u0412\u044b\u0433\u043d\u0430\u0442\u044c \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0451\u043d\u043d\u043e\u0433\u043e \u0438\u0433\u0440\u043e\u043a\u0430.");
        itemMeta5.setLore(lore5);
        item5.setItemMeta(itemMeta5);
        this.worldSet.setItem(28, item5);
        ItemStack item6 = new ItemStack(Material.SIGN);
        ItemMeta itemMeta6 = item6.getItemMeta();
        itemMeta6.setDisplayName("§f\u0418\u0433\u0440\u043e\u0432\u044b\u0435 \u043f\u0440\u0430\u0432\u0438\u043b\u0430");
        List<String> lore6 = new ArrayList<String>();
        lore6.add("§7\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0438\u0433\u0440\u043e\u0432\u044b\u043c\u0438 \u043f\u0440\u0430\u0432\u0438\u043b\u0430\u043c\u0438 \u043c\u0438\u0440\u0430.");
        lore6.add("§7(\u0410\u043d\u0430\u043b\u043e\u0433\u0438\u0447\u043d\u043e /gamerule)");
        itemMeta6.setLore(lore6);
        item6.setItemMeta(itemMeta6);
        this.worldSet.setItem(29, item6);
        ItemStack item7 = new ItemStack(Material.GRASS);
        ItemMeta itemMeta7 = item7.getItemMeta();
        itemMeta7.setDisplayName("§f\u0420\u0430\u0437\u043c\u0435\u0440 \u043c\u0438\u0440\u0430");
        List<String> lore7 = new ArrayList<String>();
        lore7.add("§7\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u0440\u0430\u0437\u043c\u0435\u0440\u0430 \u043c\u0438\u0440\u0430.");
        itemMeta7.setLore(lore7);
        item7.setItemMeta(itemMeta7);
        this.worldSet.setItem(30, item7);
        ItemStack item8 = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta8 = item8.getItemMeta();
        itemMeta8.setDisplayName("§fУправление правами флая игрока");
        List<String> lore8 = new ArrayList<String>();
        lore8.add("§7(аналогично /fly)");
        itemMeta8.setLore(lore8);
        item8.setItemMeta(itemMeta8);
        worldSet.setItem(31, item8);
        
        ItemStack item9 = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta9 = item9.getItemMeta();
        itemMeta9.setDisplayName("§e\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043c\u0438\u0440\u0430 \u0432 \u043c\u0435\u043d\u044e");
        List<String> lore9 = new ArrayList<String>();
        if (this.getPlayerGroupId(p.getName()) > 1) {
            lore9.add("§f" + col(getConfig().getString("players." + p.getName() + ".worldName")));
        }
        else {
            lore9.add("§f" + this.getConfig().getString("players." + p.getName() + ".worldName"));
        }
        itemMeta9.setLore(lore9);
        item9.setItemMeta(itemMeta9);
        this.worldSet.setItem(16, item9);
        
        ItemStack item10 = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta10 = item10.getItemMeta();
        itemMeta10.setDisplayName("§e\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u0434\u043e\u0441\u0442\u0443\u043f \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043a \u043c\u0438\u0440\u0443");
        List<String> lore10 = new ArrayList<String>();
        if (getConfig().getBoolean("players." + p.getName() + ".worldOptions.canJoin")) {
            lore10.add("§a\u041c\u0438\u0440 \u043e\u0442\u043a\u0440\u044b\u0442 \u0434\u043b\u044f \u0432\u0441\u0435\u0445");
        }
        else {
            lore10.add("§c\u041c\u0438\u0440 \u0437\u0430\u043a\u0440\u044b\u0442");
        }
        lore10.add(" ");
        lore10.add("§7\u041d\u0430\u0436\u043c\u0438 \u0447\u0442\u043e\u0431\u044b \u043e\u0442\u043a\u0440\u044b\u0442\u044c/\u0437\u0430\u043a\u0440\u044b\u0442\u044c \u0434\u043e\u0441\u0442\u0443\u043f \u043a \u043c\u0438\u0440\u0443.");
        itemMeta10.setLore(lore10);
        item10.setItemMeta(itemMeta10);
        this.worldSet.setItem(15, item10);
        p.openInventory(this.worldSet);
        
        ItemStack item11 = new ItemStack(Material.COAL_BLOCK);
        ItemMeta itemMeta11 = item11.getItemMeta();
        itemMeta11.setDisplayName("§fЧёрный список (бан-лист)");
        List<String> lore11 = new ArrayList<String>();
        lore11.add("§7Забанить определённого игрока");
        lore11.add("§7");
        lore11.add("§7ЛКМ - Забанить");
        lore11.add("§7ПКМ - Разбанить");
        itemMeta11.setLore(lore11);
        item11.setItemMeta(itemMeta11);
        worldSet.setItem(33, item11);
        ItemStack item12 = new ItemStack(Material.WOOL);
        ItemMeta itemMeta12 = item12.getItemMeta();
        itemMeta12.setDisplayName("§fБелый список (вайт-лист)");
        List<String> lore12 = new ArrayList<String>();
        lore12.add("§7Разрешить игроку заходить в закрытый мир");
        lore11.add("§7");
        lore11.add("§7ЛКМ - Добавить в белый список");
        lore11.add("§7ПКМ - Удалить из белого списка");
        itemMeta12.setLore(lore12);
        item12.setItemMeta(itemMeta12);
        worldSet.setItem(34, item12);
    }
    
    public void tpToWorld(Player p, String worldName, String modeName) {
    	World w = Bukkit.getWorld(worldName);
    	if(modeName.equalsIgnoreCase("coding")){
    		w = Bukkit.getWorld(worldName + "%c");
    	}
        p.setGameMode(GameMode.ADVENTURE);
        p.teleport(w.getSpawnLocation());
        p.getInventory().clear();
        PlayerUtils ps = new PlayerUtils();
        ps.clearAllEffects(p);
        if (modeName.equalsIgnoreCase("build")) {
            ItemStack hub = new ItemStack(Material.MAGMA_CREAM);
            ItemMeta hubMeta = hub.getItemMeta();
            hubMeta.setDisplayName("§fВыйти в лобби");
            hub.setItemMeta(hubMeta);
            p.getInventory().setItem(8, hub);
            if (w.getName().equalsIgnoreCase(p.getName())) {
                p.setGameMode(GameMode.CREATIVE);
                ItemStack opt = new ItemStack(Material.FEATHER);
                ItemMeta optMeta = opt.getItemMeta();
                optMeta.setDisplayName("§fНастройки мира");
                opt.setItemMeta(optMeta);
                p.getInventory().setItem(7, opt);
            }
            else {
                p.setGameMode(GameMode.ADVENTURE);
            }
        }
        else if(modeName.equalsIgnoreCase("kitpvp")) {
            p.setGameMode(GameMode.SPECTATOR);
            ItemStack hub = new ItemStack(Material.MAGMA_CREAM);
            ItemMeta hubMeta = hub.getItemMeta();
            hubMeta.setDisplayName("§fВыйти в лобби");
            hub.setItemMeta(hubMeta);
            p.getInventory().setItem(8, hub);
            p.sendMessage("Введите §7/kit§f для выбора набора пвп.");
        }
        else if(modeName.equalsIgnoreCase("game")){
        	List<String> list = getConfig().getStringList("players." + worldName + ".code.join");
        	CodingUtils cu = new CodingUtils();
        	cu.execute(p, list, null);
        	p.setGameMode(GameMode.ADVENTURE);
        }
        else if(modeName.equalsIgnoreCase("parkour")){
        	ItemStack hub = new ItemStack(Material.MAGMA_CREAM);
            ItemMeta hubMeta = hub.getItemMeta();
            hubMeta.setDisplayName("§fВыйти в лобби");
            hub.setItemMeta(hubMeta);
            p.getInventory().setItem(8, hub);
        	ItemStack item = new ItemStack(Material.DIAMOND);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("§fНачать паркур заново");
            item.setItemMeta(itemMeta);
            p.getInventory().setItem(0, item);
            ItemStack item1 = new ItemStack(Material.COAL);
            ItemMeta itemMeta1 = item1.getItemMeta();
            itemMeta1.setDisplayName("§fПоследний чекпоинт");
            item1.setItemMeta(itemMeta1);
            p.getInventory().setItem(1, item1);
        	p.setGameMode(GameMode.ADVENTURE);
        }
        else if(modeName.equalsIgnoreCase("coding")){
        	if(w.getName().split("%")[0].equalsIgnoreCase(p.getName())) {
        		p.setGameMode(GameMode.CREATIVE);
        		p.sendMessage("§3§lДобро пожаловать в режим Кодинга!");
        		p.sendMessage("§bЭтот режим поможет тебе создать свою собственную игру");
        		p.sendMessage("§f/dev §b- переключится на режим кодинга");
        		p.sendMessage("§f/dev <Ник> §b- дать доступ к коду игроку");
        		p.sendMessage("§3По вопросам обращайтесь в тех. поддержку §7/social");
        		p.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK));
        		p.getInventory().addItem(new ItemStack(Material.WOOD));
        		p.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
        	}else{
        		p.setGameMode(GameMode.SPECTATOR);
        	}
        }
        sendWorldMessage(w,p.getDisplayName() + "§f \u0432\u043e\u0448\u0451\u043b \u0432 \u043c\u0438\u0440.");
    }
    
    public void tpToHub(Player p, boolean noMessage) {
    	String worldName = p.getLocation().getWorld().getName().split("%")[0];
        /*if(this.worldsList.contains(worldName + split + "game")) {
        	List<String> list = getConfig().getStringList("players." + worldName + ".code.quit");
        	CodingUtils cu = new CodingUtils();
        	cu.execute(p, list, null);
        }*/
        int players = p.getLocation().getWorld().getPlayers().size();
        if (players == 1) {
            for (int num = 0; num < this.gamesList.size(); ++num) {
                this.worldsList.remove(worldName + split + gamesList.get(num));
            }
        }
        if (!p.getWorld().getName().equalsIgnoreCase("world")) {
            this.sendWorldMessage(p.getLocation().getWorld().getName(), String.valueOf(p.getDisplayName()) + "§f \u0432\u044b\u0448\u0435\u043b \u0438\u0437 \u043c\u0438\u0440\u0430.");
        }
        World hub = Bukkit.getWorld("world");
        p.setGameMode(GameMode.ADVENTURE);
        p.teleport(hub.getSpawnLocation());
        p.setHealth(20.0);
        p.setFoodLevel(20);
        PlayerUtils ps = new PlayerUtils();
        ps.clearAllEffects(p);
        Inventory pInv = (Inventory)p.getInventory();
        pInv.clear();
        ItemStack item1 = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName("§eЭффекты");
        List<String> lore1 = new ArrayList<String>();
        lore1.add("§7Всякие плюшки, гаджеты и многое другое!");
        itemMeta1.setLore((List)lore1);
        item1.setItemMeta(itemMeta1);
        pInv.setItem(4, item1);
        ItemStack item2 = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName("§f\u041c\u0438\u0440\u044b");
        List<String> lore2 = new ArrayList<String>();
        lore2.add("§7\u0421\u043f\u0438\u0441\u043e\u043a \u0437\u0430\u043f\u0443\u0449\u0435\u043d\u043d\u044b\u0445 \u043c\u0438\u0440\u043e\u0432 \u0441\u0435\u0440\u0432\u0435\u0440\u0430.");
        itemMeta2.setLore((List)lore2);
        item2.setItemMeta(itemMeta2);
        pInv.setItem(0, item2);
        ItemStack item3 = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName("§f\u041c\u043e\u0439 \u043c\u0438\u0440");
        List<String> lore3 = new ArrayList<String>();
        lore3.add("§7\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0441\u0432\u043e\u0438\u043c \u043c\u0438\u0440\u043e\u043c.");
        itemMeta3.setLore(lore3);
        item3.setItemMeta(itemMeta3);
        pInv.setItem(1, item3);
        ItemStack item4 = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName("§a\u0414\u043e\u043d\u0430\u0442");
        List<String> lore4 = new ArrayList<String>();
        lore4.add("§7\u0414\u043e\u043d\u0430\u0442-\u0443\u0441\u043b\u0443\u0433\u0438.");
        itemMeta4.setLore((List)lore4);
        item4.setItemMeta(itemMeta4);
        pInv.setItem(8, item4);
        ItemStack item5 = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta itemMeta5 = item5.getItemMeta();
        itemMeta5.setDisplayName("§e\u041c\u044b \u0432 \u0421\u043e\u0446. \u0421\u0435\u0442\u044f\u0445!");
        List<String> lore5 = new ArrayList<String>();
        lore5.add("§7\u0421\u043b\u0435\u0434\u0438 \u0437\u0430 \u043d\u043e\u0432\u043e\u0441\u0442\u044f\u043c\u0438 ");
        lore5.add("§7\u0432 \u043d\u0430\u0448\u0438\u0445 \u0441\u043e\u0446. \u0441\u0435\u0442\u044f\u0445!");
        itemMeta5.setLore((List)lore5);
        item5.setItemMeta(itemMeta5);
        pInv.setItem(6, item5);
    }
    
    public void openWorlds(Player p) {
        this.worlds = Bukkit.createInventory(null, 54, "\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0438\u0440\u043e\u0432 (" + this.worldsList.size() + ")");
        if (this.worldsList.size() == 0) {
            ItemStack world = new ItemStack(Material.BARRIER);
            ItemMeta worldMeta = world.getItemMeta();
            worldMeta.setDisplayName("§cСписок миров на сервере пуст.");
            List<String> list = new ArrayList<String>();
            list.add("§eСоздай свой мир первым! §8/myworld");
            world.setItemMeta(worldMeta);
            this.worlds.addItem(new ItemStack[] { world });
        }
        else {
            for (int i = 0; i < this.worldsList.size(); ++i) {
                String getPlName = worldsList.get(i).split(split)[0];
                String getType = this.worldsList.get(i).split(split)[1];
                ItemStack world2 = new ItemStack(Material.BRICK);
                if (getType.equalsIgnoreCase("build")) {
                    if (getConfig().getBoolean("players." + getPlName + ".worldOptions.canJoin")) {
                        world2.setType(Material.BRICK);
                    }
                    else {
                        world2.setType(Material.BARRIER);
                    }
                }
                else if (getType.equalsIgnoreCase("kitpvp")) {
                    world2.setType(Material.SHIELD);
                }
                else if (getType.equalsIgnoreCase("game")) {
                    world2.setType(Material.DIAMOND);
                }else if (getType.equalsIgnoreCase("parkour")) {
                    world2.setType(Material.LADDER);
                }else if (getType.equalsIgnoreCase("coding")) {
                    world2.setType(Material.COMMAND);
                }
                ItemMeta worldMeta2 = world2.getItemMeta();
                worldMeta2.setLocalizedName(getPlName);
                if (this.getPlayerGroupId(getPlName) > 1) {
                    worldMeta2.setDisplayName("§f" + col(getConfig().getString("players." + getPlName + ".worldName").replace("&", "§")));
                }
                else {
                    worldMeta2.setDisplayName("§f" + getConfig().getString("players." + getPlName + ".worldName"));
                }
                List<String> lore1 = new ArrayList<String>();
                if (Bukkit.getPlayer(getPlName) == null) {
                    lore1.add("§fСоздатель: §7" + getPlName + " §8(Не в сети)");
                }
                else {
                    lore1.add("§fСоздатель: §7" + Bukkit.getPlayer(getPlName).getDisplayName());
                }
                if(getType.equalsIgnoreCase("coding")){
                	lore1.add("§fОнлайн: §7" + Bukkit.getWorld(getPlName + "%c").getPlayers().size());
                }else{
                	lore1.add("§fОнлайн: §7" + Bukkit.getWorld(getPlName).getPlayers().size());
                }
                File like = new File(Bukkit.getWorldContainer() + File.separator + getPlName + File.separator + "like.txt");
                File dislike = new File(Bukkit.getWorldContainer() + File.separator + getPlName + File.separator + "dislike.txt");
                int l = 0;
                int d = 0;
                FileUtils fu = new FileUtils();
                if(like.exists()){ //Лайки
                	String[] players = null;
                	try {
						players = fu.getContent(like).split(",");
					} catch (Exception e) {
						l = 0;
						e.printStackTrace();
					}
                	for(String str : players){
                		if((str != null) || (str != "") || (str != "\n")){
                			l++;
                		}
                	}
                }else{
                	l = 0;
                }
                if(dislike.exists()){ //Дизлайки
                	String[] players = null;
                	try {
						players = fu.getContent(dislike).split(",");
					} catch (Exception e) {
						d = 0;
						e.printStackTrace();
					}
                	for(String str : players){
                		if((str != null) || (str != "") || (str != "\n")){
                			d--;
                		}
                	}
                }else{
                	d = 0;
                }
                int resultLike = l+d;
                boolean canSeeLike = getConfig().getBoolean("players." + getPlName + "." + "worldOptions.canSeeLike");
                if(canSeeLike){
                	if(resultLike == 0){
                    	lore1.add("§fОценка: §70");
                    }else if(resultLike > 0){
                    	lore1.add("§fОценка: §a+" + resultLike);
                    }else if(resultLike < 0){
                    	lore1.add("§fОценка: §c" + resultLike);
                    }
                }else{
                	lore1.add("§fОценка: §8(скрыта владельцем мира)");
                }
                lore1.add("§7ЛКМ - перейти в мир.");
                lore1.add("§7ПКМ - подробности и действия с миром.");
                worldMeta2.setLore(lore1);
                world2.setItemMeta(worldMeta2);
                this.worlds.addItem(new ItemStack[] { world2 });
            }
        }
        p.openInventory(this.worlds);
    }
    
    public void openMyWorld(Player p) {
        this.myworld = Bukkit.createInventory((InventoryHolder)null, 9, "\u041c\u043e\u0439 \u043c\u0438\u0440");
        ItemStack world = new ItemStack(Material.STAINED_GLASS, 1, (short)7);
        ItemMeta worldMeta = world.getItemMeta();
        worldMeta.setDisplayName("§e\u041e\u0442\u043a\u0440\u044b\u0442\u044c/\u0421\u043e\u0437\u0434\u0430\u0442\u044c§f \u043c\u0438\u0440.");
        List<String> lore1 = new ArrayList<String>();
        lore1.add("§7\u041a\u043b\u0438\u043a");
        worldMeta.setLore((List)lore1);
        world.setItemMeta(worldMeta);
        this.myworld.setItem(4, world);
        p.openInventory(this.myworld);
    }
    
    public void openDonate(Player p) {
        this.donate = Bukkit.createInventory((InventoryHolder)null, 45, "");
        ItemStack premium = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta premMeta = premium.getItemMeta();
        premMeta.setDisplayName("§b§l| §bPremium §7- 30 RUB");
        List<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add(" §7Убраны ограничения по времени установке скина.");
        lore.add(" §7Убраны ограничения по ограниченным скинам.");
        lore.add(" §7Размер мира до 200 блоков.");
        lore.add(" §7Цветной чат.");
        lore.add(" §7Сообщение при входе: §b[Premium] " + p.getName());
        lore.add(" ");
        lore.add("§aПокупая донат вы помогаете серверу!");
        premMeta.setLore((List)lore);
        premMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        premium.setItemMeta(premMeta);
        this.donate.setItem(20, premium);
        ItemStack lord = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta lordMeta = lord.getItemMeta();
        lordMeta.setDisplayName("§a§l| §aLord §7- 60 RUB");
        List<String> lore2 = new ArrayList<String>();
        lore2.add(" ");
        lore2.add(" §7Размер мира до 250 блоков.");
        lore2.add(" §7Цветное название мира.");
        lore2.add(" §7+ 2 монеты при убийстве игрока.");
        lore2.add(" §7И также все права группы §b[Premium]");
        lore2.add(" ");
        lore2.add("§aПокупая донат вы помогаете серверу!");
        lordMeta.setLore((List)lore2);
        lordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lord.setItemMeta(lordMeta);
        this.donate.setItem(21, lord);
        ItemStack deluxe = new ItemStack(Material.GOLD_CHESTPLATE);
        ItemMeta deluxeMeta = deluxe.getItemMeta();
        deluxeMeta.setDisplayName("§d§l| §dDeluxe §7- 100 RUB");
        List<String> lore3 = new ArrayList<String>();
        lore3.add(" ");
        lore3.add(" §7Размер мира до 300 блоков.");
        lore3.add(" §7Возможность писать объявления /bc <Текст>.");
        lore3.add(" §7+ 3 монеты при убийстве игрока.");
        lore3.add(" §7И также все права группы §a[Lord]");
        lore3.add(" ");
        lore3.add("§aПокупая донат вы помогаете серверу!");
        deluxeMeta.setLore((List)lore3);
        deluxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        deluxe.setItemMeta(deluxeMeta);
        this.donate.setItem(23, deluxe);
        ItemStack ultra = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta ultraMeta = ultra.getItemMeta();
        ultraMeta.setDisplayName("§4§l| §4Ultra §7- 200 RUB");
        List<String> lore4 = new ArrayList<String>();
        lore4.add(" ");
        lore4.add(" §7Размер мира до 350 блоков.");
        lore4.add(" §7И также все права группы §a[Lord]");
        lore4.add(" ");
        lore4.add(" ");
        lore4.add(" ");
        lore4.add("§aПокупая донат вы помогаете серверу!");
        ultraMeta.setLore((List)lore4);
        ultraMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ultra.setItemMeta(ultraMeta);
        this.donate.setItem(24, ultra);
        p.openInventory(this.donate);
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("worlds")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
                    this.openWorlds(p);
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("myworld")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
                    this.openMyWorld(p);
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("worldtp")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (p.isOp()) {
                    if (args.length == 0) {
                        p.sendMessage("§\u0441§l| §c\u0413\u0434\u0435 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043c\u0438\u0440\u0430? /worldtp <\u041c\u0438\u0440>");
                    }
                    else {
                        Location loc = Bukkit.getWorld(args[0].toString()).getSpawnLocation();
                        p.teleport(loc);
                    }
                }
                else {
                    p.sendMessage("§c§l| §c\u0423 \u0432\u0430\u0441 \u043d\u0435\u0442 \u043f\u0440\u0430\u0432 \u0434\u043b\u044f \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u0434\u0430\u043d\u043d\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b");
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("hub") || cmd.getName().equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                this.tpToHub(p, false);
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("donate") || cmd.getName().equalsIgnoreCase("shop")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                this.openDonate(p);
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("cosmetics")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if(p.getLocation().getWorld().getName().equalsIgnoreCase("world")){
                cosm = Bukkit.createInventory(null, 54, "§lМеню эффектов");
                /*ItemStack item1 = new ItemStack(Material.IRON_HOE);
                ItemMeta itemMeta1 = item1.getItemMeta();
                itemMeta1.setDisplayName("§f\u0421\u043d\u0435\u0433\u043e\u043c\u0451\u0442");
                List<String> list1 = new ArrayList<String>();
                list1.add("§7\u041f\u043e\u043f\u0443\u043b\u044f\u0439 \u0441\u043d\u0435\u0436\u043a\u0430\u043c\u0438!");
                itemMeta1.setLore(list1);
                item1.setItemMeta(itemMeta1);
                this.cosm.setItem(0, item1);
                ItemStack item2 = new ItemStack(Material.BOW);
                ItemMeta itemMeta2 = item2.getItemMeta();
                itemMeta2.setDisplayName("§f\u041b\u0443\u043a");
                List<String> list2 = new ArrayList<String>();
                list2.add("§7\u041f\u0440\u043e\u0432\u0435\u0440\u044c \u0441\u0432\u043e\u044e \u043c\u0435\u0442\u043a\u043e\u0441\u0442\u044c!");
                itemMeta2.setLore(list2);
                item2.setItemMeta(itemMeta2);
                this.cosm.setItem(1, item2);*/
                ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§f ");
                List<String> list = new ArrayList<String>();
                list.add("§7 ");
                itemMeta.setLore(list);
                item.setItemMeta(itemMeta);
                this.cosm.setItem(22, item);
                this.cosm.setItem(29, item);
                this.cosm.setItem(33, item);
                ItemStack item1 = new ItemStack(Material.DIAMOND_HOE);
                ItemMeta itemMeta1 = item1.getItemMeta();
                itemMeta1.setDisplayName("§eГаджеты");
                List<String> list1 = new ArrayList<String>();
                list1.add("§7Самое крутое из списка...");
                list1.add("§7поиграйся с игроками :)");
                itemMeta1.setLore(list1);
                itemMeta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item1.setItemMeta(itemMeta1);
                this.cosm.setItem(20, item1);
                ItemStack item2 = new ItemStack(Material.GOLD_HELMET);
                ItemMeta itemMeta2 = item2.getItemMeta();
                itemMeta2.setDisplayName("§eКостюмы §7§lСКОРО...");
                List<String> list2 = new ArrayList<String>();
                list2.add("§7Выделяйся в лобби");
                list2.add("§7с помощью костюмов!");
                itemMeta2.setLore(list2);
                itemMeta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item2.setItemMeta(itemMeta2);
                this.cosm.setItem(31, item2);
                ItemStack item3 = new ItemStack(Material.NETHER_STAR);
                ItemMeta itemMeta3 = item3.getItemMeta();
                itemMeta3.setDisplayName("§eЧастицы §7§lСКОРО...");
                List<String> list3 = new ArrayList<String>();
                list3.add("§7Красивые следы оставляемые");
                list3.add("§7при хотьбе или беге игрока.");
                itemMeta3.setLore(list3);
                itemMeta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item3.setItemMeta(itemMeta3);
                this.cosm.setItem(24, item3);
                p.openInventory(cosm);
                }else{
                	p.sendMessage("§cЭффекты работают только в лобби!");
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("kit")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                String worldName = p.getLocation().getWorld().getName();
                if (this.worldsList.contains(worldName + this.split + "kitpvp")) {
                    if (p.getGameMode() == GameMode.SPECTATOR) {
                        this.KitPvP = Bukkit.createInventory(null, 45, "\u041d\u0430\u0431\u043e\u0440\u044b KitPvP");
                        ItemStack kit = new ItemStack(Material.IRON_SWORD);
                        ItemMeta kitMeta = kit.getItemMeta();
                        kitMeta.setDisplayName("§f\u0420\u044b\u0446\u0430\u0440\u044c §a§l\u0421\u0422\u0410\u0420\u0422\u041e\u0412\u042b\u0419");
                        kitMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                        List<String> lore = new ArrayList<String>();
                        lore.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c.");
                        kitMeta.setLore((List)lore);
                        kit.setItemMeta(kitMeta);
                        this.KitPvP.setItem(0, kit);
                        ItemStack kit2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                        ItemMeta kitMeta2 = kit2.getItemMeta();
                        kitMeta2.setDisplayName("§f\u0412\u043e\u0438\u043d");
                        kitMeta2.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                        List<String> lore2 = new ArrayList<String>();
                        if (this.getConfig().getBoolean("players." + p.getName() + ".kit.voin")) {
                            lore2.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c.");
                        }
                        else {
                            lore2.add("§f\u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c: §720 \u043c\u043e\u043d\u0435\u0442");
                            lore2.add("§e\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u043a\u0443\u043f\u0438\u0442\u044c.");
                        }
                        kitMeta2.setLore((List)lore2);
                        kit2.setItemMeta(kitMeta2);
                        this.KitPvP.setItem(1, kit2);
                        ItemStack kit3 = new ItemStack(Material.BOW);
                        ItemMeta kitMeta3 = kit3.getItemMeta();
                        kitMeta3.setDisplayName("§f\u041b\u0443\u0447\u043d\u0438\u043a §e§lПОПУЛЯРНЫЙ");
                        kitMeta3.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                        List<String> lore3 = new ArrayList<String>();
                        if (this.getConfig().getBoolean("players." + p.getName() + ".kit.archer")) {
                            lore3.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c.");
                        }
                        else {
                            lore3.add("§f\u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c: §750 \u043c\u043e\u043d\u0435\u0442");
                            lore3.add("§e\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u043a\u0443\u043f\u0438\u0442\u044c.");
                        }
                        kitMeta3.setLore((List)lore3);
                        kit3.setItemMeta(kitMeta3);
                        this.KitPvP.setItem(2, kit3);
                        ItemStack kit4 = new ItemStack(Material.IRON_AXE);
                        ItemMeta kitMeta4 = kit4.getItemMeta();
                        kitMeta4.setDisplayName("§f\u0411\u0435\u0440\u0441\u0435\u043a");
                        kitMeta4.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                        List<String> lore4 = new ArrayList<String>();
                        if (this.getConfig().getBoolean("players." + p.getName() + ".kit.bersek")) {
                            lore4.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c.");
                        }
                        else {
                            lore4.add("§f\u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c: §780 \u043c\u043e\u043d\u0435\u0442");
                            lore4.add("§e\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u043a\u0443\u043f\u0438\u0442\u044c.");
                        }
                        kitMeta4.setLore(lore4);
                        kit4.setItemMeta(kitMeta4);
                        this.KitPvP.setItem(3, kit4);
                        ItemStack kit5 = new ItemStack(Material.COOKED_MUTTON);
                        ItemMeta kitMeta5 = kit5.getItemMeta();
                        kitMeta5.setDisplayName("§f\u041c\u044f\u0441\u043d\u0438\u043a §c§l\u041b\u0423\u0427\u0428\u0418\u0419");
                        kitMeta5.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
                        List<String> lore5 = new ArrayList<String>();
                        if (this.getConfig().getBoolean("players." + p.getName() + ".kit.myaso")) {
                            lore5.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c.");
                        }
                        else {
                            lore5.add("§f\u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c: §7100 \u043c\u043e\u043d\u0435\u0442");
                            lore5.add("§e\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u043a\u0443\u043f\u0438\u0442\u044c.");
                        }
                        kitMeta5.setLore(lore5);
                        kit5.setItemMeta(kitMeta5);
                        this.KitPvP.setItem(4, kit5);
                        ItemStack bal = new ItemStack(Material.DOUBLE_PLANT);
                        ItemMeta balMeta = bal.getItemMeta();
                        int balance = getConfig().getInt("players." + name(p) + ".money");
                        balMeta.setDisplayName("§fВаш баланс: §e" + balance);
                        bal.setItemMeta(balMeta);
                        KitPvP.setItem(44, bal);
                        p.openInventory(KitPvP);
                    }
                    else {
                        p.sendMessage("§c§l| §c\u041d\u0435\u043b\u044c\u0437\u044f \u043c\u0435\u043d\u044f\u0442\u044c \u043d\u0430\u0431\u043e\u0440 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0438\u0433\u0440\u044b. \u041f\u0435\u0440\u0435\u0437\u0430\u0439\u0434\u0438\u0442\u0435.");
                    }
                }
                else {
                    p.sendMessage("§c§l| §c\u0412\u044b \u043d\u0435 \u0432 \u043c\u0438\u0440\u0435 \u041a\u0438\u0442\u041f\u0432\u041f!");
                }
            }
            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("bal") || cmd.getName().equalsIgnoreCase("balance")) && sender instanceof Player) {
            Player p = (Player)sender;
            p.sendMessage("\u0411\u0430\u043b\u0430\u043d\u0441:");
            p.sendMessage("§6§l| §6" + this.getConfig().getInt("players." + p.getName() + "." + "money") + " \u043c\u043e\u043d\u0435\u0442");
            p.sendMessage("§c§l| §c" + this.getConfig().getInt("players." + p.getName() + "." + "ruby") + " \u0440\u0443\u0431\u0438\u043d\u043e\u0432");
        }
        if (cmd.getName().equalsIgnoreCase("tell")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (args.length == 0) {
                    p.sendMessage("§c§l| §c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 /tell <\u0418\u0433\u0440\u043e\u043a> <\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>.");
                }
                else if (args.length == 1) {
                    p.sendMessage("§c§l| §c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 /tell <\u0418\u0433\u0440\u043e\u043a> <\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>.");
                }
                else {
                    String playerName = args[0].toString();
                    if (Bukkit.getPlayer(playerName) != null) {
                        String messageText = "";
                        Player pl = Bukkit.getPlayer(playerName);
                        for (int value = 1; value < args.length; ++value) {
                            if (messageText == "") {
                                messageText = args[value].toString();
                            }
                            else {
                                messageText = String.valueOf(messageText) + " " + args[value].toString();
                            }
                        }
                        if (p.getName().equalsIgnoreCase(pl.getName())) {
                            p.sendMessage("§c§l| §c\u0412\u044b \u043d\u0435 \u043c\u043e\u0436\u0435\u0442\u0435 \u043e\u0442\u043f\u0440\u0430\u0432\u0438\u0442\u044c \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435 \u0441\u0430\u043c\u043e\u043c\u0443 \u0441\u0435\u0431\u0435!");
                        }
                        else {
                            p.sendMessage("\u0412\u044b \u043e\u0442\u043f\u0440\u0430\u0432\u0438\u043b\u0438 " + pl.getDisplayName() + "§f §7: §f" + messageText);
                            pl.sendMessage("\u041d\u043e\u0432\u043e\u0435 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435 \u043e\u0442 " + p.getDisplayName() + "§f §7: §f" + messageText);
                            pl.sendTitle(p.getDisplayName(), messageText);
                        }
                    }
                    else {
                        p.sendMessage("§c§l| §c\u0422\u0430\u043a\u043e\u0433\u043e \u0438\u0433\u0440\u043e\u043a\u0430 \u043d\u0435\u0442 \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440\u0435!");
                    }
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("w") || cmd.getName().equalsIgnoreCase("msg")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                p.sendMessage("§c§l| §c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 /tell <\u0418\u0433\u0440\u043e\u043a> <\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>.");
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("social")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                this.social = Bukkit.createInventory((InventoryHolder)null, 27, "\u041c\u044b \u0432 \u0421\u043e\u0446. \u0421\u0435\u0442\u044f\u0445!");
                ItemStack vk = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
                ItemMeta vkMeta = vk.getItemMeta();
                vkMeta.setDisplayName("§3ВКонтакте");
                List<String> lore6 = new ArrayList<String>();
                lore6.add("§f" + vkurl);
                vkMeta.setLore(lore6);
                vk.setItemMeta(vkMeta);
                this.social.setItem(12, vk);
                ItemStack discord = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11);
                ItemMeta discordMeta = vk.getItemMeta();
                discordMeta.setDisplayName("§9Discord");
                List<String> lore7 = new ArrayList<String>();
                lore7.add("§f" + discordurl);
                discordMeta.setLore(lore7);
                discord.setItemMeta(discordMeta);
                this.social.setItem(14, discord);
                p.openInventory(this.social);
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("bc")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (this.getPlayerGroupId(p) > 2) {
                    if (args.length == 0) {
                        p.sendMessage("§c§l| §c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 /bc <\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>.");
                    }
                    else {
                        String bcText = "";
                        for (int value2 = 0; value2 < args.length; ++value2) {
                            bcText = String.valueOf(bcText) + " " + args[value2].toString();
                        }
                        String bcTextColor = bcText.replace("&", "§");
                        Bukkit.broadcastMessage("§7\u041e\u0431\u044a\u044f\u0432\u043b\u0435\u043d\u0438\u0435 \u043e\u0442 " + p.getDisplayName() + "§7 :§f" + bcTextColor);
                    }
                }
                else {
                    p.sendMessage("§c§l| §cНедостаточно прав! Купите донат §dDeluxe §c - /donate");
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("name")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (args.length == 0) {
                    p.sendMessage("§c§l| §c\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 /name <\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435>.");
                }
                else {
                    String nameText = "";
                    for (int value2 = 0; value2 < args.length; ++value2) {
                        if (nameText == "") {
                            nameText = args[value2].toString();
                        }
                        else {
                            nameText = String.valueOf(nameText) + " " + args[value2].toString();
                        }
                    }
                    if (nameText.length() <= 60) {
                        if (this.getPlayerGroupId(p) > 1) {
                            nameText = nameText.replace("&", "§");
                            p.sendMessage("§a\u0418\u043c\u044f \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430:");
                            p.sendMessage("§f" + nameText);
                        }
                        else {
                            p.sendMessage("§a\u0418\u043c\u044f \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430:");
                            p.sendMessage("§f" + nameText);
                        }
                        this.getConfig().set("players." + p.getName() + ".worldName", (Object)nameText);
                        this.saveConfig();
                    }
                    else {
                        p.sendMessage("§c§l| §c\u0412 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0438 \u043c\u0438\u0440\u0430 \u0434\u043e\u043b\u0436\u043d\u043e \u0431\u044b\u0442\u044c \u043d\u0435 \u0431\u043e\u043b\u044c\u0448\u0435 60 \u0441\u0438\u043c\u0432\u043e\u043b\u043e\u0432!");
                    }
                }
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("agreload")) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (p.isOp()) {
                    this.reloadConfig();
                    sender.sendMessage("\u041a\u043e\u043d\u0444\u0438\u0433 ArtGame \u043e\u0431\u043d\u043e\u0432\u043b\u0451\u043d!");
                }
                else {
                    sender.sendMessage("\u041d\u0438\u0437\u044f!");
                }
            }
            return true;
        }
        if( cmd.getName().equalsIgnoreCase("gm")){
        	if( sender instanceof Player ){
        		Player p = (Player) sender;
        		if( args.length != 0 ){
        			if(sender.hasPermission("artgame.commands.gm")){
        				if( args[0].equalsIgnoreCase("0") ){
        					p.setGameMode(GameMode.SURVIVAL);
        					p.sendMessage("§7Ваш игровой режим изменён на: §eВыживание");
        				}
        				if( args[0].equalsIgnoreCase("1") ){
        					p.setGameMode(GameMode.CREATIVE);
        					p.sendMessage("§7Ваш игровой режим изменён на: §eКреатив");
        				}
        				if( args[0].equalsIgnoreCase("2") ){
        					p.setGameMode(GameMode.ADVENTURE);
        					p.sendMessage("§7Ваш игровой режим изменён на: §eПриключение");
        				}
        				if( args[0].equalsIgnoreCase("3") ){
        					p.setGameMode(GameMode.SPECTATOR);
        					p.sendMessage("§7Ваш игровой режим изменён на: §eСпектатор");
        				}
        			}
        		}else{
        			sender.sendMessage("§c§l| §cИспользуйте /gm <0-3>");
        		}
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("like")){
        	if(sender instanceof Player){
        		Player p = (Player) sender;
        		String worldName = p.getLocation().getWorld().getName().split("%")[0];
        		try {
					boolean isNotPrefer = likeWorld(p, worldName);
					if(isNotPrefer){
						p.sendMessage("§fТы поставил §aлайк§f этому миру! :)");
						sendWorldMessage(worldName, p.getDisplayName() + "§a лайкнул этот мир!");
					}else{
						p.sendMessage("§cТы уже поставил оценку этому миру!");
					}
				} catch (Exception e) {
					p.sendMessage("§cПроизошла ошибка при проставлении оценки миру.");
					p.sendMessage("§fПо вопросам спрашивайте в тех-поддержке §9/social");
					e.printStackTrace();
				}
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("dislike")){
        	if(sender instanceof Player){
        		Player p = (Player) sender;
        		String worldName = p.getLocation().getWorld().getName().split("%")[0];
        		try {
					boolean isNotPrefer = dislikeWorld(p, worldName);
					if(isNotPrefer){
						p.sendMessage("§fТы поставил §cдизлайк§f этому миру! :(");
						sendWorldMessage(worldName, p.getDisplayName() + "§c дизлайкнул этот мир!");
					}else{
						p.sendMessage("§cТы уже поставил оценку этому миру!");
					}
				} catch (Exception e) {
					p.sendMessage("§cПроизошла ошибка при проставлении оценки миру.");
					p.sendMessage("§fПо вопросам спрашивайте в тех-поддержке §9/social");
					e.printStackTrace();
				}
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("stopworlds")){
        	if(sender instanceof Player){
        		Player p = (Player) sender;
        		if(getPlayerGroupId(p) > 5){
        			List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        			for(Player pl : players){
        				if(!pl.getLocation().getWorld().getName().equalsIgnoreCase("world")){
        					tpToHub(pl, false);
        				}
        			}
        		}else{
        			p.sendMessage("§cВам товарищ нельзя такую команду использовать!");
        		}
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("givemoney")){
        	if(sender.hasPermission("artgame.commands.givemoney")){
        		if(args.length == 0){
            		sender.sendMessage("§cНеверно веденна команда!");
            		sender.sendMessage("§cUsage: " + cmd.getUsage());
            	}else{
            		if(args.length >= 2){
            			String nickname = args[0];
            			int count;
            			try{
            				count = Integer.parseInt(args[1]);
            			}catch(NumberFormatException e){
            				sender.sendMessage("§cНеверно веденна команда!");
                    		sender.sendMessage("§cUsage: " + cmd.getUsage());
                    		return true;
            			}
            			if(getConfig().contains("players." + nickname + ".money")){
            				giveCash(nickname, count, CashType.MONEY);
                			sender.sendMessage("§fУспешно!");
            			}else{
            				sender.sendMessage("§cНеверно веденна команда!");
                    		sender.sendMessage("§cUsage: " + cmd.getUsage());
            			}
            		}
            	}
        	}else{
        		sender.sendMessage("§cНету прав!");
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("giveruby")){
        	if(sender.hasPermission("artgame.commands.giveruby")){
        		if(args.length == 0){
            		sender.sendMessage("§cНеверно веденна команда!");
            		sender.sendMessage("§cUsage: " + cmd.getUsage());
            	}else{
            		if(args.length >= 2){
            			String nickname = args[0];
            			int count;
            			try{
            				count = Integer.parseInt(args[1]);
            			}catch(NumberFormatException e){
            				sender.sendMessage("§cНеверно веденна команда!");
                    		sender.sendMessage("§cUsage: " + cmd.getUsage());
                    		return true;
            			}
            			if(getConfig().contains("players." + nickname + ".ruby")){
            				giveCash(nickname, count, CashType.RUBY);
                			sender.sendMessage("§fУспешно!");
            			}else{
            				sender.sendMessage("§cНеверно веденна команда!");
                    		sender.sendMessage("§cUsage: " + cmd.getUsage());
            			}
            		}
            	}
        	}else{
        		sender.sendMessage("§cНету прав!");
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("top")){
        	if(sender instanceof Player){
        		Player p = (Player) sender;
        		topWorlds = Bukkit.createInventory(null, 45, "§9§nТоп миров по лайкам!");
        		List<String> sortedList = getWorldsTop();
        		for(int i = 0; i < sortedList.size(); i++){
        			if(i >= 45){
        				break;
        			}
        			String getPlName = sortedList.get(i);
        			ItemStack item = new ItemStack(Material.BRICK);
        			ItemMeta itemMeta = item.getItemMeta();
        			if (this.getPlayerGroupId(getPlName) > 1) {
                        itemMeta.setDisplayName("§f" + col(getConfig().getString("players." + getPlName + ".worldName").replace("&", "§")));
                    }
                    else {
                        itemMeta.setDisplayName("§f" + getConfig().getString("players." + getPlName + ".worldName"));
                    }
        			itemMeta.setLocalizedName(getPlName);
        			List<String> lore = new ArrayList<String>();
        			if (Bukkit.getPlayer(getPlName) == null) {
                        lore.add("§fСоздатель: §7" + getPlName + " §8(Не в сети)");
                    }else{
                    	lore.add("§fСоздатель: §7" + Bukkit.getPlayer(getPlName).getDisplayName());
                    }
        			int resultLike = getLike(getPlName);
        			if(resultLike == 0){
                    	lore.add("§fОценка: §70");
                    }else if(resultLike > 0){
                    	lore.add("§fОценка: §a+" + resultLike);
                    }else if(resultLike < 0){
                    	lore.add("§fОценка: §c" + resultLike);
                    }
        			lore.add("§fВ топе: §e" + (i+1) + "§7 место");
        			itemMeta.setLore(lore);
        			item.setItemMeta(itemMeta);
        			topWorlds.addItem(item);
        			p.openInventory(topWorlds);
        		}
        	}else{
        		List<String> sortedList = getWorldsTop();
        		for(String str : sortedList){
        			sender.sendMessage(str+"["+getLike(str)+"],");
        		}
        	}
        	return true;
        }
        if(cmd.getName().equalsIgnoreCase("tech")){
        	if(sender.hasPermission("artgame.commands.tech")){
        		if(tech){
        			tech = false;
        			sender.sendMessage("§cРежим технического перерыва выключен!");
        		}else{
        			tech = true;
        			sender.sendMessage("§aРежим технического перерыва включен!");
        		}
        	}
        	return true;
        }
        return false;
    }
    
    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.sendMessage(" ");
        p.sendMessage(" §f §f §f §fДобро пожаловать на §9§lA" + "r" + "t§c§lG" + "a" + "m" + "e");
        p.sendMessage(" §f §f §f §a§lКреатив+ §fнаш лучший режим.");
        p.sendMessage(" §f §f §f §fТвой мир - §e§l/myworld");
        p.sendMessage(" §f §f §f §fМиры игроков - §e§l/worlds");
        p.sendMessage(" §f §f §f §fДонат-услуги - §e§l/donate");
        p.sendMessage(" §f §f §f §fНаши соц. сети - §e§l/social");
        p.sendMessage(" §f §f §f §fАвтор и создатель проекта - §e§lM" + "r" + "A" + "r" + "t" + "h" + "u" + "r" + "4" + "I" + "k");
        p.sendMessage(" ");
        //p.sendTitle("§9Art§cGame §aКреатив", "§fЛучше чем просто креатив");
        p.sendTitle("§b§lART§6§lGAME", "§eБольше чем просто креатив");
        PermissionUser player = PermissionsEx.getUser(p);
        //Делаем префикс в табе
        if (player.inGroup("admin")) {
        	p.setPlayerListName("§c[Admin] " + p.getName() + "§f");
        }
        else if (player.inGroup("supporta")) {
            p.setPlayerListName("§e[Support¹] " + p.getName() + "§f");
        }
        else if (player.inGroup("supportb")) {
            p.setPlayerListName("§e[Support²] " + p.getName() + "§f");
        }
        else if (player.inGroup("supportc")) {
            p.setPlayerListName("§e[Support³] " + p.getName() + "§f");
        }
        else if (player.inGroup("ultra")) {
        	p.setPlayerListName("§4[Ultra] " + p.getName() + "§f");
        }
        else if (player.inGroup("deluxe")) {
        	p.setPlayerListName("§d[Deluxe] " + p.getName() + "§f");
        }
        else if (player.inGroup("lord")) {
        	p.setPlayerListName("§a[Lord] " + p.getName() + "§f");
        }
        else if (player.inGroup("premium")) {
        	p.setPlayerListName("§b[Premium] " + p.getName() + "§f");
        }
        else {
        	p.setPlayerListName("§7" + p.getName() + "§f");
        }
        //Делаем префикс в чате
        if (player.inGroup("admin")) {
            p.setDisplayName("§c[Admin] " + p.getName() + "§f");
        }
        else if (player.inGroup("supporta")) {
            p.setDisplayName("§e[Support¹] " + p.getName() + "§f");
        }
        else if (player.inGroup("supportb")) {
            p.setDisplayName("§e[Support²] " + p.getName() + "§f");
        }
        else if (player.inGroup("supportc")) {
            p.setDisplayName("§e[Support³] " + p.getName() + "§f");
        }
        else if (player.inGroup("ultra")) {
            p.setDisplayName("§4[Ultra] " + p.getName() + "§f");
        }
        else if (player.inGroup("deluxe")) {
            p.setDisplayName("§d[Deluxe] " + p.getName() + "§f");
        }
        else if (player.inGroup("lord")) {
            p.setDisplayName("§a[Lord] " + p.getName() + "§f");
        }
        else if (player.inGroup("premium")) {
            p.setDisplayName("§b[Premium] " + p.getName() + "§f");
        }
        else {
            p.setDisplayName("§7" + p.getName() + "§f");
        }
        if (!getConfig().contains("players." + p.getName())) {
            getConfig().set("players." + p.getName() + "." + "money", 0);
            getConfig().set("players." + p.getName() + "." + "ruby", 0);
            getConfig().set("players." + p.getName() + "." + "worldName", p.getName());
            getConfig().set("players." + p.getName() + "." + "kit.voin", false);
            getConfig().set("players." + p.getName() + "." + "kit.archer", false);
            getConfig().set("players." + p.getName() + "." + "kit.bersek", false);
            getConfig().set("players." + p.getName() + "." + "kit.myaso", false);
            getConfig().set("players." + p.getName() + "." + "kit.kol", false);
            getConfig().set("players." + p.getName() + "." + "banList", new ArrayList<String>());
            getConfig().set("players." + p.getName() + "." + "whiteList", new ArrayList<String>());
            Bukkit.broadcastMessage("§6§lПриветствуем нового игрока " + p.getDisplayName() + "§f §6§lна нашем сервере!");
        }
        if(getConfig().contains("players." + p.getName() + "." + "playersCanJoin")){
        	getConfig().set("players." + p.getName() + "." + "playersCanJoin", null);
        }
        if(!getConfig().contains("players." + p.getName() + "." + "worldOptions.canJoin")){
        	getConfig().set("players." + p.getName() + "." + "worldOptions.canJoin", true);
        }
        if(!getConfig().contains("players." + p.getName() + "." + "worldOptions.canInteract")){
        	getConfig().set("players." + p.getName() + "." + "worldOptions.canInteract", true);
        }
        if(!getConfig().contains("players." + p.getName() + "." + "worldOptions.canPVP")){
        	getConfig().set("players." + p.getName() + "." + "worldOptions.canPVP", true);
        }
        if(!getConfig().contains("players." + p.getName() + "." + "worldOptions.canSeeLike")){
        	getConfig().set("players." + p.getName() + "." + "worldOptions.canSeeLike", true);
        }
        /*if (!getConfig().contains("players." + p.getName() + ".suits")) {
        	List<Suit> resultList = new ArrayList<Suit>();
        	for(Suit suit : suitList){
        		if(suit.getType() == SuitType.START){
        			resultList.add(suit);
        		}
        	}
            getConfig().set("players." + p.getName() + "." + "suits", resultList);
        }*/
        saveConfig();
        if(getPlayerGroupId(p) > 0){
        	e.setJoinMessage(p.getDisplayName() + "§f §fзашёл на сервер.");
        }else{
        	e.setJoinMessage("");
        }
        tpToHub(p, false);
        /*FileUtils fu = new FileUtils();
        File test = new File(Bukkit.getWorldContainer()+File.separator+"test.txt");
        try {
			fu.setContent(test, "This is test.\nHello world!");
		} catch (Exception e1) {
			p.sendMessage("Неудачно!");
			e1.printStackTrace();
		}*/
    }
    
    @EventHandler
    public void quit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        this.tpToHub(p, false);
        e.setQuitMessage("");
    }
    
    @EventHandler
    public void interact(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
            	if (p.getGameMode() != GameMode.CREATIVE) {
                    e.setCancelled(true);
                }
                if (p.getItemInHand().getType() == Material.IRON_HOE && p.getCooldown(Material.IRON_HOE) == 0) {
                    p.launchProjectile((Class)Snowball.class);
                    Location l = p.getLocation();
                    l.getWorld().playSound(l, Sound.ENTITY_SNOWBALL_THROW, 10.0f, 1.0f);
                    p.setCooldown(Material.IRON_HOE, 5);
                }
                if (p.getItemInHand().getType() == Material.ARROW && p.getCooldown(Material.ARROW) == 0) {
                    p.launchProjectile((Class)Arrow.class);
                    Location l = p.getLocation();
                    l.getWorld().playSound(l, Sound.ENTITY_ARROW_SHOOT, 10.0f, 1.0f);
                    p.setCooldown(Material.ARROW, 5);
                }
                if (p.getItemInHand().getType() == Material.ENDER_PEARL && p.getCooldown(Material.ENDER_PEARL) == 0) {
                	ItemStack item = p.getItemInHand();
                	p.getInventory().setItemInHand(item);
                    EnderPearl ender = (EnderPearl) p.launchProjectile(EnderPearl.class);
                    ender.addPassenger(p);
                    p.setCooldown(Material.ENDER_PEARL, 15);
                }
                if (p.getInventory().getItemInMainHand().getType() == Material.SADDLE && !p.isInsideVehicle() && p.getCooldown(Material.SADDLE) == 0) {
                    Location l = p.getLocation();
                    Horse horse = (Horse)l.getWorld().spawnEntity(l, EntityType.HORSE);
                    horse.setTamed(true);
                    horse.setOwner((AnimalTamer)p);
                    horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                    horse.addPassenger((Entity)p);
                    horse.setInvulnerable(true);
                    p.setCooldown(Material.SADDLE, 15);
                }
                if (p.getInventory().getItemInMainHand().getType() == Material.TNT && p.getCooldown(Material.TNT) == 0) {
                    Location l = p.getLocation();
                    l.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, l.getX(), l.getY(), l.getZ(), 10, 1.0, 1.0, 1.0);
                    l.getWorld().playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 10.0f, 1.0f);
                    p.setCooldown(Material.TNT, 15);
                }
                if (p.getItemInHand().getType() == Material.COMPASS) {
                    p.performCommand("worlds");
                }
                if (p.getItemInHand().getType() == Material.FEATHER) {
                    p.performCommand("myworld");
                }
                if (p.getItemInHand().getType() == Material.EMERALD) {
                    p.performCommand("donate");
                }
                if (p.getItemInHand().getType() == Material.BLAZE_POWDER) {
                    p.performCommand("cosmetics");
                }
                if (p.getItemInHand().getType() == Material.GLOWSTONE_DUST) {
                    p.performCommand("social");
                }
            }
            else {
                if (this.worldsList.contains(String.valueOf(p.getLocation().getWorld().getName()) + this.split + "kitpvp")) {
                	if (a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_BLOCK){
                		e.setCancelled(true);
                	}
                }
                if (this.worldsList.contains(String.valueOf(p.getLocation().getWorld().getName()) + this.split + "build")) {
                	if (a == Action.PHYSICAL || a == Action.RIGHT_CLICK_BLOCK){
                		if(p.getGameMode() != GameMode.CREATIVE){
                			boolean canInteract = getConfig().getBoolean("players." + p.getLocation().getWorld().getName().split("%")[0] + "." + "worldOptions.canInteract");
                			if(!canInteract){
                				e.setCancelled(true);
                			}
                		}
                	}
                }
                if (p.getItemInHand().getType() == Material.MAGMA_CREAM) {
                    p.performCommand("hub");
                }
                if (p.getItemInHand().getType() == Material.FEATHER && p.getLocation().getWorld().getName().equalsIgnoreCase(name(p))) {
                	if(worldsList.contains(name(p) + split + "build")){
                		openWorldOptions(p);
                	}
                }
                if (p.getItemInHand().getType() == Material.DIAMOND) {
                	if(worldsList.contains(name(p) + split + "parkour")){
                		if(p.getCooldown(Material.DIAMOND) == 0){
                			p.teleport(p.getLocation().getWorld().getSpawnLocation());
                    		p.sendMessage("§6Телепортирование в начало паркура §8(Все чекпоинты очищены)");
                    		p.setCooldown(Material.DIAMOND, 20);
                		}
                	}
                }
            }
        }
        if (a == Action.PHYSICAL && p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
            e.setCancelled(true);
        }
        if ((a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) && p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
            if (p.getGameMode() != GameMode.CREATIVE) {
                e.setCancelled(true);
            }
            if (p.getItemInHand().getType() == Material.IRON_HOE) {
                p.getItemInHand().setType(Material.AIR);
            }
            if (p.getItemInHand().getType() == Material.BOW) {
                p.getItemInHand().setType(Material.AIR);
            }
        }
    }
    
    @EventHandler
    public void chat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        boolean mptb = false;
        if(mpt.containsKey(name(p))){
        	mptb = (boolean) mpt.get(name(p));
        }else{
        	mptb = false;
        }
        if(mptb){
        	if(e.getMessage().equalsIgnoreCase("ОТМЕНА")){
        		mpt.put(name(p), false);
        		p.sendMessage("§fОперация отменена.");
        		openCode(p, mp.get(name(p)));
        	}else{
        		List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
        		String[] parts = list.get((int) mpb.get(name(p))).split("::");
    			if(parts[0].equalsIgnoreCase("action")){
    				if(parts[1].equalsIgnoreCase("sendmsg")){
    					list.set((int) mpb.get(name(p)), "action::sendmsg::" + e.getMessage());
    					getConfig().set("players." + p.getName() + ".code." + mp.get(name(p)), list);
    					saveConfig();
    					p.sendMessage("§aЗначение установлено: §f" + e.getMessage().replace("&", "\u00a7"));
    					mpt.put(name(p), false);
    					openCode(p, mp.get(name(p)));
    				}
    				if(parts[1].equalsIgnoreCase("sendtitle")){
    					list.set((int) mpb.get(name(p)), "action::sendtitle::" + e.getMessage());
    					getConfig().set("players." + p.getName() + ".code." + mp.get(name(p)), list);
    					saveConfig();
    					p.sendMessage("§aЗначение установлено: §f" + e.getMessage().replace("&", "\u00a7"));
    					mpt.put(name(p), false);
    					openCode(p, mp.get(name(p)));
    				}
    			}
        	}
        	e.setCancelled(true);
        }else{
        	/*List<Player> getPlayers = (List<Player>) Bukkit.getOnlinePlayers();
        	List<String> strPlayers = new ArrayList<String>();
        	String endColor = null;
        	for(Player pl : getPlayers){
        		strPlayers.add(name(pl));
        	}
        	if (this.getPlayerGroupId(p) > 0) {
        		endColor = "§f";
        	}else{
        		endColor = "§7";
        	}
        	e.setMessage(endColor + PlayerUtils.splitPlayers(e.getMessage(), strPlayers, endColor));*/
        	if (this.getPlayerGroupId(p) > 0) {
                if (e.getMessage().startsWith("!")) {
                    e.setFormat("§8[§aG§8] §7" + p.getDisplayName() + " §7» §f" + col(e.getMessage().substring(1)));
                }
                else {
                    e.setCancelled(true);
                    this.sendWorldMessage(p.getLocation().getWorld().getName(), "§8[§eL§8] §f" + p.getDisplayName() + " §7» §f" + col(e.getMessage()));
                }
            }else{
            	if (e.getMessage().startsWith("!")) {
                	e.setFormat("§8[§aG§8] §7" + p.getDisplayName() + " §7» §7" + e.getMessage().substring(1));
            	}else{
            		e.setCancelled(true);
                	this.sendWorldMessage(p.getLocation().getWorld().getName(), "§8[§eL§8] §7" + p.getDisplayName() + " §7» §7" + e.getMessage());
            	}
            }
        }
    }
    
    @EventHandler
    public void damage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void food(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
                e.setCancelled(true);
            }
            else {
                World w = p.getLocation().getWorld();
                if (this.worldsList.contains(String.valueOf(w.getName()) + this.split + "build")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void drop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getWorld().getName().equalsIgnoreCase("world")) {
            e.setCancelled(true);
        }
        if(worldsList.contains(p.getLocation().getWorld().getName() + split + "parkour")){
        	e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory i = e.getInventory();
        if (i.equals(this.myworld)) {
            e.setCancelled(true);
            if (e.getSlot() == 4) {
                File worldFolder = new File(Bukkit.getWorldContainer() + File.separator + p.getName());
                if (worldFolder.exists()) {
                    String worldName = p.getName();
                    for (int num = 0; num < this.gamesList.size(); ++num) {
                        if (this.worldsList.contains(worldName + "//split//" + this.gamesList.get(num))) {
                            this.startedMyWorld = Bukkit.createInventory(null, 27, "Ваш мир запущен. Ваше действие?");
                            ItemStack stop = new ItemStack(Material.LAVA_BUCKET);
                            ItemMeta stopMeta = stop.getItemMeta();
                            stopMeta.setDisplayName("§f\u041e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u043c\u0438\u0440");
                            List<String> lore1 = new ArrayList<String>();
                            lore1.add("§7\u0417\u0430\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u0432\u0430\u0448 \u043c\u0438\u0440 \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440\u0435.");
                            stopMeta.setLore((List)lore1);
                            stop.setItemMeta(stopMeta);
                            this.startedMyWorld.setItem(14, stop);
                            ItemStack tp = new ItemStack(Material.ENDER_PEARL);
                            ItemMeta tpMeta = tp.getItemMeta();
                            tpMeta.setDisplayName("§f\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u0432 \u043c\u0438\u0440");
                            List<String> lore2 = new ArrayList<String>();
                            lore2.add("§7\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f \u0432 \u0441\u0432\u043e\u0439 \u043c\u0438\u0440.");
                            tpMeta.setLore((List)lore2);
                            tp.setItemMeta(tpMeta);
                            this.startedMyWorld.setItem(12, tp);
                            p.openInventory(this.startedMyWorld);
                            break;
                        }
                        this.startMyWorld = Bukkit.createInventory(null, 27, "Выберите режим запуска мира");
                        ItemStack stop = new ItemStack(Material.BRICK);
                        ItemMeta stopMeta = stop.getItemMeta();
                        stopMeta.setDisplayName("§f\u0421\u0442\u0440\u043e\u0438\u0442\u0435\u043b\u044c\u0441\u0442\u0432\u043e");
                        List<String> lore1 = new ArrayList<String>();
                        lore1.add("§7\u0421\u0442\u0440\u043e\u0439, \u043b\u043e\u043c\u0430\u0439, \u0438\u0437\u043c\u0435\u043d\u044f\u0439");
                        lore1.add("§7\u0441\u0432\u043e\u0439 \u043c\u0438\u0440.");
                        stopMeta.setLore(lore1);
                        stop.setItemMeta(stopMeta);
                        this.startMyWorld.setItem(0, stop);
                        ItemStack stop2 = new ItemStack(Material.IRON_SWORD);
                        ItemMeta stopMeta2 = stop2.getItemMeta();
                        stopMeta2.setDisplayName("§f\u041a\u0438\u0442\u041f\u0432\u041f");
                        List<String> lore2 = new ArrayList<String>();
                        lore2.add("§7\u0412\u044b\u0431\u0435\u0440\u0438 \u0441\u0432\u043e\u044e \u0440\u043e\u043b\u044c");
                        lore2.add("§7\u0438 \u0441\u0440\u0430\u0436\u0430\u0439\u0441\u044f!");
                        stopMeta2.setLore(lore2);
                        stopMeta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        stop2.setItemMeta(stopMeta2);
                        this.startMyWorld.setItem(1, stop2);
                        ItemStack stop3 = new ItemStack(Material.DIAMOND);
                        ItemMeta stopMeta3 = stop3.getItemMeta();
                        stopMeta3.setDisplayName("§fИгра §7§lВ РАЗРАБОТКЕ!");
                        List<String> lore3 = new ArrayList<String>();
                        lore3.add("§7Сделай свою игру в кодинге ");
                        lore3.add("§7и запусти её здесь!");
                        stopMeta3.setLore(lore3);
                        stop3.setItemMeta(stopMeta3);
                        this.startMyWorld.setItem(2, stop3);
                        ItemStack delete = new ItemStack(Material.LAVA_BUCKET);
                        ItemMeta meta = delete.getItemMeta();
                        meta.setDisplayName("§c\u0423\u0434\u0430\u043b\u0438\u0442\u044c \u043c\u0438\u0440.");
                        List<String> lore4 = new ArrayList<String>();
                        lore4.add("§7\u0412\u043d\u0438\u043c\u0430\u043d\u0438\u0435! \u0423\u0434\u0430\u043b\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430 \u0431\u0435\u0437\u0432\u043e\u0437\u0432\u0440\u0430\u0442\u043d\u0430.");
                        meta.setLore(lore4);
                        delete.setItemMeta(meta);
                        this.startMyWorld.setItem(26, delete);
                        p.openInventory(this.startMyWorld);
                    }
                }
                else {
                    this.generateMyWorld = Bukkit.createInventory(null, 27, "Выбери тип генерации мира");
                    ItemStack gen = new ItemStack(Material.GRASS);
                    ItemMeta genMeta = gen.getItemMeta();
                    genMeta.setDisplayName("§fРавнина");
                    gen.setItemMeta(genMeta);
                    ItemStack gen2 = new ItemStack(Material.GLASS);
                    ItemMeta genMeta2 = gen2.getItemMeta();
                    genMeta2.setDisplayName("§fПустота");
                    gen2.setItemMeta(genMeta2);
                    ItemStack gen3 = new ItemStack(Material.SAND);
                    ItemMeta genMeta3 = gen3.getItemMeta();
                    genMeta3.setDisplayName("§fПустыня");
                    gen3.setItemMeta(genMeta3);
                    ItemStack gen4 = new ItemStack(Material.SNOW_BLOCK);
                    ItemMeta genMeta4 = gen4.getItemMeta();
                    genMeta4.setDisplayName("§fСнежная равнина");
                    gen4.setItemMeta(genMeta4);
                    this.generateMyWorld.setItem(0, gen);
                    this.generateMyWorld.setItem(1, gen2);
                    this.generateMyWorld.setItem(2, gen3);
                    this.generateMyWorld.setItem(3, gen4);
                    p.openInventory(this.generateMyWorld);
                }
            }
        }
        if (i.equals(this.startMyWorld)) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                p.closeInventory();
                p.sendTitle("§6\u0417\u0430\u0433\u0440\u0443\u0437\u043a\u0430 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:air;127;decoration");
                World w = Bukkit.createWorld(wc);
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                this.tpToWorld(p, p.getName(), "build");
                w.save();
                boolean canPVP = getConfig().getBoolean("players." + p.getName() + "." + "worldOptions.canPVP");
                if(canPVP){
                	w.setPVP(true);
                }else{
                	w.setPVP(false);
                }
                String worldName2 = p.getName();
                this.worldsList.add(String.valueOf(worldName2) + "//split//build");
                p.sendTitle("§a\u041c\u0438\u0440 \u0437\u0430\u043f\u0443\u0449\u0435\u043d!", "");
            }
            if (e.getSlot() == 1) {
                p.closeInventory();
                p.sendTitle("§6\u0417\u0430\u0433\u0440\u0443\u0437\u043a\u0430 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:air;127;decoration");
                World w = Bukkit.createWorld(wc);
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                this.tpToWorld(p, p.getName(), "kitpvp");
                w.save();
                w.setPVP(true);
                String worldName2 = p.getName();
                this.worldsList.add(String.valueOf(worldName2) + "//split//kitpvp");
                p.sendTitle("§a\u041c\u0438\u0440 \u0437\u0430\u043f\u0443\u0449\u0435\u043d!", "");
            }
            if (e.getSlot() == 2) {
                p.closeInventory();
                if(!(getPlayerGroupId(p) > 4)) return;
                p.sendTitle("§6Загрузка мира...", "§7Пожалуйста, подождите...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:air;127;decoration");
                World w = Bukkit.createWorld(wc);
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                w.setPVP(true);
                String worldName2 = p.getName();
                this.worldsList.add(String.valueOf(worldName2) + "//split//game");
                p.sendTitle("§aМир запущен!", "");
                this.tpToWorld(p, p.getName(), "game");
                w.save();
            }
            if (e.getSlot() == 3) {
            	if(!(getPlayerGroupId(p) > 4)) return;
                p.closeInventory();
                p.sendTitle("§6Загрузка мира...", "§7Пожалуйста, подождите...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:air;127;decoration");
                World w = Bukkit.createWorld(wc);
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                w.setPVP(false);
                String worldName2 = p.getName();
                this.worldsList.add(String.valueOf(worldName2) + "//split//parkour");
                p.sendTitle("§aМир запущен!", "");
                this.tpToWorld(p, p.getName(), "parkour");
                w.save();
            }
            if (e.getSlot() == 4) {
            	if(!(getPlayerGroupId(p) > 4)) return;
                p.closeInventory();
                p.sendTitle("§6Загрузка мира...", "§7Пожалуйста, подождите...");
                WorldCreator wc = new WorldCreator(p.getName() + "%c");
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:air;127;");
                World w = wc.createWorld();
                w.setGameRuleValue("doMobSpawning", "false");
                w.setGameRuleValue("doDaylightCycle", "false");
                w.setGameRuleValue("doWeatherCycle", "false");
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.5, 0.5);
                wb.setSize(100.0);
                w.setPVP(false);
                String worldName2 = p.getName();
                this.worldsList.add(String.valueOf(worldName2) + "//split//coding");
                w.setSpawnLocation(48, 4, 48);
                for(int x = -50; x <= 50; x++){
                	for(int y = -50; y <= 50; y++){
                    	Block block = w.getBlockAt(x, 3, y);
                    	block.setType(Material.STAINED_GLASS);
                    }
                }
                int startX = 46;
                int startY = 46;
                for(int r = 0; r < 24; r++){
                	for(int b = 0; b < 48; b++){
                		Block block = w.getBlockAt(startX+b*(-2), 3, startY+r*(-4));
                    	block.setType(Material.STAINED_GLASS);
                    	if(b == 0){
                    		block.setData((byte)15);
                    	}else{
                    		block.setData((byte)7);
                    	}
                	}
                }
                Block block = w.getBlockAt(50, 4, 50);
                block.setType(Material.WORKBENCH);
                block = w.getBlockAt(50, 5, 50);
                block.setType(Material.ANVIL);
                block = w.getBlockAt(50, 6, 50);
                block.setType(Material.ENDER_CHEST);
                block = w.getBlockAt(50, 7, 50);
                block.setType(Material.ENCHANTMENT_TABLE);
                p.sendTitle("§aМир запущен!", "");
                tpToWorld(p, p.getName(), "coding");
                w.save();
            }
            if (e.getSlot() == 26) {
                p.closeInventory();
                p.sendTitle("§c\u0423\u0434\u0430\u043b\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                Bukkit.unloadWorld(p.getName(), false);
                File worldFolder = new File(Bukkit.getWorldContainer() + File.separator + p.getName());
                deleteDirectory(worldFolder);
                File worldCode = new File(Bukkit.getWorldContainer() + File.separator + p.getName() + "%c");
                if(worldCode.exists()){
                	Bukkit.unloadWorld(p.getName() + "%c", false);
                	deleteDirectory(worldCode);
                }
                p.sendTitle("§a\u0423\u0434\u0430\u043b\u0435\u043d\u0438\u0435 \u043f\u0440\u043e\u0448\u043b\u043e \u0443\u0441\u043f\u0435\u0448\u043d\u043e!", "§7\u0412\u044b \u043c\u043e\u0436\u0435\u0442\u0435 \u0441\u043e\u0437\u0434\u0430\u0442\u044c \u043c\u0438\u0440 \u0437\u0430\u043d\u043e\u0432\u043e.");
            }
        }
        if (i.equals(this.startedMyWorld)) {
            e.setCancelled(true);
            if (e.getSlot() == 14) {
                String worldName3 = p.getName();
                for (int num2 = 0; num2 < this.gamesList.size(); num2++) {
                	World w2;
                	if(gamesList.get(num2).equalsIgnoreCase("coding")){
                		w2 = Bukkit.getWorld(worldName3 + "%c");
                	}else{
                		w2 = Bukkit.getWorld(worldName3);
                	}
                    List<Player> pls = (List<Player>)w2.getPlayers();
                    for (Player pl : pls) {
                        this.tpToHub(pl, true);
                        pl.sendTitle("\u041c\u0438\u0440 \u0431\u044b\u043b \u0437\u0430\u043a\u0440\u044b\u0442", "");
                        pl.sendMessage("\u041c\u0438\u0440 \u0431\u044b\u043b \u0437\u0430\u043a\u0440\u044b\u0442.");
                    }
                    this.worldsList.remove(worldName3 + this.split + this.gamesList.get(num2));
                }
                p.closeInventory();
            }
            if (e.getSlot() == 12) {
                String worldName3 = p.getName();
                p.closeInventory();
                for(int g = 0; g < gamesList.size(); g++){
                	if (worldsList.contains(worldName3 + split + gamesList.get(g))) {
                        tpToWorld(p, p.getName(), gamesList.get(g));
                    }
                }
            }
        }
        if (i.equals(this.worlds)) {
            e.setCancelled(true);
            if(e.getSlot() < 46){
            	if(e.getCurrentItem().getType() != Material.AIR){
            		if (e.isLeftClick()) {
                        String worldName3 = e.getCurrentItem().getItemMeta().getLocalizedName();
                        if(worldName3 == null) return;
                        List<String> banList = getConfig().getStringList("players." + worldName3 + ".banList");
                        if(banList.contains(name(p))){
                        	p.sendTitle("§cВы забанены","§8в этом мире.");
                        	p.closeInventory();
                        }else{
                        	if(e.getCurrentItem().getType() == Material.BRICK){
                        		if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "build")) {
                            		this.tpToWorld(p, worldName3, "build");
                            	}
                        	}
                        	if(e.getCurrentItem().getType() == Material.SHIELD){
                        		if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "kitpvp")) {
                            		this.tpToWorld(p, worldName3, "kitpvp");
                            	}
                        	}
                        	if(e.getCurrentItem().getType() == Material.DIAMOND){
                        		if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "game")) {
                            		this.tpToWorld(p, worldName3, "game");
                            	}
                        	}
                        	if(e.getCurrentItem().getType() == Material.BARRIER){
                        		if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "build")) {
                        			List<String> whiteList = getConfig().getStringList("players." + worldName3 + ".whiteList");
                                    if(whiteList.contains(name(p))){
                                    	tpToWorld(p, worldName3, "build");
                                    	if(p.getLocation().getWorld().getName().equalsIgnoreCase(worldName3)){
                                    		p.setGameMode(GameMode.CREATIVE);
                                    	}
                                    }else{
                                    	if(p.getLocation().getWorld().getName().equalsIgnoreCase(worldName3)){
                                    		tpToWorld(p, worldName3, "build");
                                    	}else{
                                    		p.closeInventory();
                                    		p.sendTitle("§cМир закрыт", "владельцем мира!");
                                    	}
                                    }
                            	}
                        	}
                        	if(e.getCurrentItem().getType() == Material.LADDER){
                        		if (this.worldsList.contains(worldName3 + this.split + "parkour")) {
                            		this.tpToWorld(p, worldName3, "parkour");
                            	}
                        	}
                        	if(e.getCurrentItem().getType() == Material.COMMAND){
                        		if (this.worldsList.contains(worldName3 + this.split + "coding")) {
                            		this.tpToWorld(p, worldName3, "coding");
                            	}
                        	}
                        }
                    }else if (e.isRightClick() && e.getCurrentItem().getItemMeta().getLocalizedName() != null) {
                        wOptions = Bukkit.createInventory(null, 27, "Мир игрока §7" + e.getCurrentItem().getItemMeta().getLocalizedName());
                        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
                        itemMeta.setDisplayName("§fСписок игроков мира");
                        itemMeta.setLocalizedName(e.getCurrentItem().getItemMeta().getLocalizedName());
                        item.setItemMeta(itemMeta);
                        wOptions.setItem(12, item);
                        ItemStack item1 = new ItemStack(Material.CONCRETE, 1, (short) 5);
                        ItemMeta itemMeta1 = item1.getItemMeta();
                        if(isPreferred(p, e.getCurrentItem().getItemMeta().getLocalizedName())){
                        	itemMeta1.setDisplayName("§7Ты уже поставил оценку этому миру!");
                        }else{
                        	itemMeta1.setDisplayName("§fПоставить §aлайк§f этому миру");
                        }
                        itemMeta1.setLocalizedName(e.getCurrentItem().getItemMeta().getLocalizedName());
                        item1.setItemMeta(itemMeta1);
                        wOptions.setItem(13, item1);
                        ItemStack item2 = new ItemStack(Material.CONCRETE, 1, (short) 14);
                        ItemMeta itemMeta2 = item2.getItemMeta();
                        if(isPreferred(p, e.getCurrentItem().getItemMeta().getLocalizedName())){
                        	itemMeta2.setDisplayName("§7Ты уже поставил оценку этому миру!");
                        }else{
                        	itemMeta2.setDisplayName("§fПоставить §cдизлайк§f этому миру");
                        }
                        itemMeta2.setLocalizedName(e.getCurrentItem().getItemMeta().getLocalizedName());
                        item2.setItemMeta(itemMeta2);
                        wOptions.setItem(14, item2);
                        ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
                        ItemMeta backMeta = back.getItemMeta();
                        backMeta.setDisplayName("§fНазад");
                        back.setItemMeta(backMeta);
                        wOptions.setItem(26, back);
                        p.openInventory(wOptions);
                    }
            	}
            }
            /*if (e.getCurrentItem().getType() == Material.BRICK) {
                if (e.isLeftClick()) {
                    String worldName3 = e.getCurrentItem().getItemMeta().getLocalizedName();
                    List<String> banList = getConfig().getStringList("players." + worldName3 + ".banList");
                    if(banList.contains(name(p))){
                    	p.sendTitle("§cВы забанены","§8в этом мире.");
                    	p.closeInventory();
                    }else{
                    	if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "build")) {
                            this.tpToWorld(p, worldName3, "build");
                        }
                    }
                }
                else if (e.isRightClick()) {
                    this.wPlayersList = Bukkit.createInventory((InventoryHolder)null, 54, "\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043c\u0438\u0440\u0430 " + e.getCurrentItem().getItemMeta().getLocalizedName());
                    World world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName());
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        this.wPlayersList.addItem(new ItemStack[] { accountBtn });
                    }
                    p.openInventory(this.wPlayersList);
                }
            }
            else if (e.getCurrentItem().getType() == Material.SHIELD) {
                if (e.isLeftClick()) {
                    String worldName3 = e.getCurrentItem().getItemMeta().getLocalizedName();
                    List<String> banList = getConfig().getStringList("players." + worldName3 + ".banList");
                    if(banList.contains(name(p))){
                    	p.sendTitle("§cВы забанены","§8в этом мире.");
                    	p.closeInventory();
                    }else{
                    	if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "kitpvp")) {
                            this.tpToWorld(p, worldName3, "kitpvp");
                        }
                    }
                }
                else if (e.isRightClick()) {
                    this.wPlayersList = Bukkit.createInventory((InventoryHolder)null, 54, "\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043c\u0438\u0440\u0430 " + e.getCurrentItem().getItemMeta().getLocalizedName());
                    World world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName());
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        this.wPlayersList.addItem(new ItemStack[] { accountBtn });
                    }
                    p.openInventory(this.wPlayersList);
                }
            }
            else if (e.getCurrentItem().getType() == Material.DIAMOND) {
                if (e.isLeftClick()) {
                    String worldName3 = e.getCurrentItem().getItemMeta().getLocalizedName();
                    List<String> banList = getConfig().getStringList("players." + worldName3 + ".banList");
                    if(banList.contains(name(p))){
                    	p.sendTitle("§cВы забанены","§8в этом мире.");
                    	p.closeInventory();
                    }else{
                    	if (this.worldsList.contains(String.valueOf(worldName3) + this.split + "game")) {
                            this.tpToWorld(p, worldName3, "game");
                        }
                    }
                }
                else if (e.isRightClick()) {
                    this.wPlayersList = Bukkit.createInventory((InventoryHolder)null, 54, "\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043c\u0438\u0440\u0430 " + e.getCurrentItem().getItemMeta().getLocalizedName());
                    World world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName());
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        this.wPlayersList.addItem(new ItemStack[] { accountBtn });
                    }
                    p.openInventory(this.wPlayersList);
                }
            }
            else if (e.getCurrentItem().getType() == Material.BARRIER) {
                if (e.isLeftClick()) {
                    String worldName3 = e.getCurrentItem().getItemMeta().getLocalizedName();
                    if (worldName3 != null) {
                        p.closeInventory();
                        List<String> whiteList = getConfig().getStringList("players." + worldName3 + ".whiteList");
                        if(whiteList.contains(name(p))){
                        	tpToWorld(p, worldName3, "build");
                        	if(p.getLocation().getWorld().getName().equalsIgnoreCase(worldName3)){
                        		p.setGameMode(GameMode.CREATIVE);
                        	}
                        }else{
                        	p.sendTitle("§cМир закрыт!", "");
                        }
                    }
                }
                else if (e.isRightClick() && e.getCurrentItem().getItemMeta().getLocalizedName() != null) {
                    this.wPlayersList = Bukkit.createInventory((InventoryHolder)null, 54, "\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043c\u0438\u0440\u0430 " + e.getCurrentItem().getItemMeta().getLocalizedName());
                    World world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName());
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        wPlayersList.addItem(accountBtn);
                    }
                    p.openInventory(this.wPlayersList);
                }
            }else if (e.getCurrentItem().getType() == Material.LADDER) {
                if (e.isLeftClick()) {
                    String worldName3 = e.getCurrentItem().getItemMeta().getLocalizedName();
                    List<String> banList = getConfig().getStringList("players." + worldName3 + ".banList");
                    if(banList.contains(name(p))){
                    	p.sendTitle("§cВы забанены","§8в этом мире.");
                    	p.closeInventory();
                    }else{
                    	if (this.worldsList.contains(String.valueOf(worldName3) + split + "parkour")) {
                            this.tpToWorld(p, worldName3, "parkour");
                        }
                    }
                }
                else if (e.isRightClick()) {
                    this.wPlayersList = Bukkit.createInventory((InventoryHolder)null, 54, "\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043c\u0438\u0440\u0430 " + e.getCurrentItem().getItemMeta().getLocalizedName());
                    World world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName());
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        this.wPlayersList.addItem(new ItemStack[] { accountBtn });
                    }
                    p.openInventory(this.wPlayersList);
                }
            }*/
		}
        if(i.equals(this.wOptions)){
        	e.setCancelled(true);
        	if(e.getSlot() == 12){
        		wPlayersList = Bukkit.createInventory(null, 54, "Список игроков мира " + e.getCurrentItem().getItemMeta().getLocalizedName());
                World world;
        		if(worldsList.contains(e.getCurrentItem().getItemMeta().getLocalizedName()+split+"coding")){
                	world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName()+"%c");
                }else{
                	world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getLocalizedName());
                }
                for (int v = 0; v < world.getPlayers().size(); ++v) {
                    Player pl2 = world.getPlayers().get(v);
                    ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                    SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                    accountSkullMeta.setOwner(pl2.getName());
                    accountSkullMeta.setDisplayName(pl2.getDisplayName());
                    accountSkullMeta.setLocalizedName(pl2.getName());
                    accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                    this.wPlayersList.addItem(new ItemStack[] { accountBtn });
                }
                p.openInventory(this.wPlayersList);
        	}
        	if(e.getSlot() == 13){
        		String worldName = e.getCurrentItem().getItemMeta().getLocalizedName();
        		try {
					boolean isNotPrefer = likeWorld(p, worldName);
					if(isNotPrefer){
						p.sendMessage("§fТы поставил §aлайк§f этому миру! :)");
						sendWorldMessage(worldName, p.getDisplayName() + "§a лайкнул этот мир!");
					}else{
						p.sendMessage("§cТы уже поставил оценку этому миру!");
					}
				} catch (Exception e1) {
					p.sendMessage("§cПроизошла ошибка при проставлении оценки миру.");
					p.sendMessage("§fПо вопросам спрашивайте в тех-поддержке §9/social");
					e1.printStackTrace();
				}
        		p.closeInventory();
        	}
        	if(e.getSlot() == 14){
        		String worldName = e.getCurrentItem().getItemMeta().getLocalizedName();
        		try {
					boolean isNotPrefer = dislikeWorld(p, worldName);
					if(isNotPrefer){
						p.sendMessage("§fТы поставил §cдизлайк§f этому миру! :(");
						sendWorldMessage(worldName, p.getDisplayName() + "§c дизлайкнул этот мир!");
					}else{
						p.sendMessage("§cТы уже поставил оценку этому миру!");
					}
				} catch (Exception e1) {
					p.sendMessage("§cПроизошла ошибка при проставлении оценки миру.");
					p.sendMessage("§fПо вопросам спрашивайте в тех-поддержке §9/social");
					e1.printStackTrace();
				}
        		p.closeInventory();
        	}
        	if(e.getSlot() == 26){
        		openWorlds(p);
        	}
        }
        if (i.equals(this.generateMyWorld)) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                p.closeInventory();
                p.sendTitle("§6\u0421\u043e\u0437\u0434\u0430\u043d\u0438\u0435 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:bedrock,2*minecraft:dirt,minecraft:grass;1");
                World w = Bukkit.createWorld(wc);
                w.setGameRuleValue("doMobSpawning", "false");
                w.setGameRuleValue("doDaylightCycle", "false");
                w.setGameRuleValue("doWeatherCycle", "false");
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                wb.setSize(100.0);
                p.sendTitle("§a\u041c\u0438\u0440 \u0433\u043e\u0442\u043e\u0432!", "§7\u0414\u043b\u044f \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0437\u0430\u0439\u0434\u0438\u0442\u0435 \u0432 \u0442\u043e\u0436\u0435 \u043c\u0435\u043d\u044e \u043c\u0438\u0440\u0430.");
            }
            if (e.getSlot() == 1) {
                p.closeInventory();
                p.sendTitle("§6\u0421\u043e\u0437\u0434\u0430\u043d\u0438\u0435 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:air;127;decoration");
                World w = Bukkit.createWorld(wc);
                w.setGameRuleValue("doMobSpawning", "false");
                w.setGameRuleValue("doDaylightCycle", "false");
                w.setGameRuleValue("doWeatherCycle", "false");
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                wb.setSize(100.0);
                p.sendTitle("§a\u041c\u0438\u0440 \u0433\u043e\u0442\u043e\u0432!", "§7\u0414\u043b\u044f \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0437\u0430\u0439\u0434\u0438\u0442\u0435 \u0432 \u0442\u043e\u0436\u0435 \u043c\u0435\u043d\u044e \u043c\u0438\u0440\u0430.");
            }
            if (e.getSlot() == 2) {
                p.closeInventory();
                p.sendTitle("§6\u0421\u043e\u0437\u0434\u0430\u043d\u0438\u0435 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:bedrock,2*minecraft:sandstone,minecraft:sand;1");
                World w = Bukkit.createWorld(wc);
                w.setGameRuleValue("doMobSpawning", "false");
                w.setGameRuleValue("doDaylightCycle", "false");
                w.setGameRuleValue("doWeatherCycle", "false");
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                wb.setSize(100.0);
                p.sendTitle("§a\u041c\u0438\u0440 \u0433\u043e\u0442\u043e\u0432!", "§7\u0414\u043b\u044f \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0437\u0430\u0439\u0434\u0438\u0442\u0435 \u0432 \u0442\u043e\u0436\u0435 \u043c\u0435\u043d\u044e \u043c\u0438\u0440\u0430.");
            }
            if (e.getSlot() == 3) {
                p.closeInventory();
                p.sendTitle("§6\u0421\u043e\u0437\u0434\u0430\u043d\u0438\u0435 \u043c\u0438\u0440\u0430...", "§7\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430, \u043f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435...");
                WorldCreator wc = new WorldCreator(p.getName());
                wc.generateStructures(false);
                wc.type(WorldType.FLAT);
                wc.generatorSettings("3;minecraft:bedrock,2*minecraft:dirt,minecraft:snow;1");
                World w = Bukkit.createWorld(wc);
                w.setGameRuleValue("doMobSpawning", "false");
                w.setGameRuleValue("doDaylightCycle", "false");
                w.setGameRuleValue("doWeatherCycle", "false");
                WorldBorder wb = w.getWorldBorder();
                wb.setCenter(0.0, 0.0);
                wb.setSize(100.0);
                p.sendTitle("§a\u041c\u0438\u0440 \u0433\u043e\u0442\u043e\u0432!", "§7\u0414\u043b\u044f \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0437\u0430\u0439\u0434\u0438\u0442\u0435 \u0432 \u0442\u043e\u0436\u0435 \u043c\u0435\u043d\u044e \u043c\u0438\u0440\u0430.");
            }
        }
        if (i.equals(this.wPlayersList)) {
            e.setCancelled(true);
        }
        if (i.equals(this.donate)) {
            e.setCancelled(true);
            if (e.getSlot() == 20) {
                p.closeInventory();
                p.sendMessage("§a§l| §aПокупка на " + siteurl);
            }
            if (e.getSlot() == 21) {
                p.closeInventory();
                p.sendMessage("§a§l| §aПокупка на " + siteurl);
            }
            if (e.getSlot() == 23) {
                p.closeInventory();
                p.sendMessage("§a§l| §aПокупка на " + siteurl);
            }
            if (e.getSlot() == 24) {
                p.closeInventory();
                p.sendMessage("§a§l| §aПокупка на " + siteurl);
            }
        }
        if (i.equals(this.cosm)) {
            e.setCancelled(true);
            if (e.getSlot() == 20) {
            	gadjets = Bukkit.createInventory(null, 27, " ");
            	ItemStack item1 = new ItemStack(Material.IRON_HOE);
                ItemMeta itemMeta1 = item1.getItemMeta();
                itemMeta1.setDisplayName("§fСнегомёт");
                List<String> list1 = new ArrayList<String>();
                list1.add("§7Снежки. Юхууу!");
                itemMeta1.setLore(list1);
                item1.setItemMeta(itemMeta1);
                ItemStack item2 = new ItemStack(Material.BOW);
                ItemMeta itemMeta2 = item2.getItemMeta();
                itemMeta2.setDisplayName("§fЛук");
                List<String> list2 = new ArrayList<String>();
                list2.add("§7Проверь свою меткость!");
                itemMeta2.setLore(list2);
                item2.setItemMeta(itemMeta2);
                ItemStack item3 = new ItemStack(Material.ENDER_PEARL);
                ItemMeta itemMeta3 = item2.getItemMeta();
                itemMeta3.setDisplayName("§fЭндер-пёрл");
                List<String> list3 = new ArrayList<String>();
                list3.add("§7Прокатись в воздухе!");
                itemMeta3.setLore(list3);
                item3.setItemMeta(itemMeta3);
                ItemStack item4 = new ItemStack(Material.SADDLE);
                ItemMeta itemMeta4 = item4.getItemMeta();
                itemMeta4.setDisplayName("§fЛошадь на прокат");
                List<String> list4 = new ArrayList<String>();
                list4.add("§7Эх прокачу!");
                itemMeta4.setLore(list4);
                item4.setItemMeta(itemMeta4);
                ItemStack item5 = new ItemStack(Material.TNT);
                ItemMeta itemMeta5 = item5.getItemMeta();
                itemMeta5.setDisplayName("§fТНТ-взрыв");
                List<String> list5 = new ArrayList<String>();
                list5.add("§7Безвредная но крутая бомба!");
                itemMeta5.setLore(list5);
                item5.setItemMeta(itemMeta5);
                ItemStack close = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                ItemMeta closeMeta = close.getItemMeta();
                closeMeta.setDisplayName("§cВ разработке...");
                List<String> closeList = new ArrayList<String>();
                closeList.add("§7");
                ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
                ItemMeta backMeta = back.getItemMeta();
                backMeta.setDisplayName("§eНазад");
                List<String> backList = new ArrayList<String>();
                backList.add("§7");
                back.setItemMeta(backMeta);
                gadjets.setItem(26, back);
                
                gadjets.setItem(11, item1);
                gadjets.setItem(12, item2);
                gadjets.setItem(13, item3);
                gadjets.setItem(14, item4);
                gadjets.setItem(15, item5);
                p.openInventory(gadjets);
            }
            if(e.getSlot() == 31){
            	if(getPlayerGroupId(p)>4){
            		suits = Bukkit.createInventory(null, 27, "Костюмы");
            	}else{
            		p.sendMessage("§cКостюмы закрыты на бета-тест!");
            	}
            }
        }
        if (i.equals(this.gadjets)) {
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                p.closeInventory();
                ItemStack item1 = new ItemStack(Material.IRON_HOE);
                ItemMeta itemMeta1 = item1.getItemMeta();
                itemMeta1.setDisplayName("§fСнегомёт");
                List<String> list1 = new ArrayList<String>();
                list1.add("§7Снежки. Юхууу!");
                list1.add("§7ПКМ - Чтобы использовать");
                list1.add("§7ЛКМ - Чтобы убрать");
                itemMeta1.setLore((List)list1);
                item1.setItemMeta(itemMeta1);
                p.getInventory().addItem(item1);
            }
            if (e.getSlot() == 12) {
                p.closeInventory();
                ItemStack item2 = new ItemStack(Material.ARROW);
                ItemMeta itemMeta2 = item2.getItemMeta();
                itemMeta2.setDisplayName("§fЛук");
                List<String> list2 = new ArrayList<String>();
                list2.add("§7Проверь свою меткость!");
                list2.add("§7ПКМ - Чтобы использовать");
                list2.add("§7ЛКМ - Чтобы убрать");
                itemMeta2.setLore((List)list2);
                item2.setItemMeta(itemMeta2);
                p.getInventory().addItem(item2);
            }
            if (e.getSlot() == 13) {
                p.closeInventory();
                ItemStack item3 = new ItemStack(Material.ENDER_PEARL);
                ItemMeta itemMeta3 = item3.getItemMeta();
                itemMeta3.setDisplayName("§fЭндер-пёрл");
                List<String> list3 = new ArrayList<String>();
                list3.add("§7Прокатись по воздуху!");
                list3.add("§7ПКМ - Чтобы использовать");
                list3.add("§7ЛКМ - Чтобы убрать");
                itemMeta3.setLore((List)list3);
                item3.setItemMeta(itemMeta3);
                p.getInventory().addItem(item3);
            }
            if (e.getSlot() == 14) {
                p.closeInventory();
                ItemStack item4 = new ItemStack(Material.SADDLE);
                ItemMeta itemMeta4 = item4.getItemMeta();
                itemMeta4.setDisplayName("§fЛощадь на прокат");
                List<String> list4 = new ArrayList<String>();
                list4.add("§7Эх прокачу!");
                list4.add("§7ПКМ - Чтобы использовать");
                list4.add("§7ЛКМ - Чтобы убрать");
                itemMeta4.setLore((List)list4);
                item4.setItemMeta(itemMeta4);
                p.getInventory().addItem(item4);
            }
            if (e.getSlot() == 15) {
                p.closeInventory();
                ItemStack item5 = new ItemStack(Material.TNT);
                ItemMeta itemMeta5 = item5.getItemMeta();
                itemMeta5.setDisplayName("§fТНТ-взрыв");
                List<String> list5 = new ArrayList<String>();
                list5.add("§7Безвредная но крутая бомба!");
                list5.add("§7ПКМ - Чтобы использовать");
                list5.add("§7ЛКМ - Чтобы убрать");
                itemMeta5.setLore((List)list5);
                item5.setItemMeta(itemMeta5);
                p.getInventory().addItem(item5);
            }
            if(e.getSlot() == 26){
            	p.performCommand("cosmetics");
            }
        }
        if(i.equals(suits)){
        	e.setCancelled(true);
        	if(e.getSlot() == 26){
            	p.performCommand("cosmetics");
            }
        }
        if (i.equals(this.worldSet)) {
            e.setCancelled(true);
            if (e.getSlot() == 10) {
                worldActions = Bukkit.createInventory(null, InventoryType.HOPPER, "Сохранение/закрытие мира");
                ItemStack item3 = new ItemStack(Material.COMPASS);
                ItemMeta itemMeta3 = item3.getItemMeta();
                itemMeta3.setDisplayName("§fСохранить мир.");
                item3.setItemMeta(itemMeta3);
                this.worldActions.setItem(1, item3);
                ItemStack item4 = new ItemStack(Material.IRON_DOOR);
                ItemMeta itemMeta4 = item4.getItemMeta();
                itemMeta4.setDisplayName("§fЗакрыть мир.");
                item4.setItemMeta(itemMeta4);
                this.worldActions.setItem(3, item4);
                p.openInventory(this.worldActions);
            }
            if (e.getSlot() == 11) {
                this.worldTime = Bukkit.createInventory((InventoryHolder)null, 27, " ");
                ItemStack item3 = new ItemStack(Material.WATCH);
                ItemMeta itemMeta3 = item3.getItemMeta();
                itemMeta3.setDisplayName("§f\u0423\u0442\u0440\u043e");
                item3.setItemMeta(itemMeta3);
                this.worldTime.setItem(10, item3);
                ItemStack item4 = new ItemStack(Material.WATCH);
                ItemMeta itemMeta4 = item4.getItemMeta();
                itemMeta4.setDisplayName("§f\u0414\u0435\u043d\u044c");
                item4.setItemMeta(itemMeta4);
                this.worldTime.setItem(12, item4);
                ItemStack item5 = new ItemStack(Material.WATCH);
                ItemMeta itemMeta5 = item5.getItemMeta();
                itemMeta5.setDisplayName("§f\u0412\u0435\u0447\u0435\u0440");
                item5.setItemMeta(itemMeta5);
                this.worldTime.setItem(14, item5);
                ItemStack item6 = new ItemStack(Material.WATCH);
                ItemMeta itemMeta6 = item6.getItemMeta();
                itemMeta6.setDisplayName("§f\u041d\u043e\u0447\u044c");
                item6.setItemMeta(itemMeta6);
                this.worldTime.setItem(16, item6);
                p.openInventory(this.worldTime);
            }
            if (e.getSlot() == 12) {
                p.closeInventory();
                World w3 = Bukkit.getWorld(p.getName());
                Location l = p.getLocation();
                w3.setSpawnLocation(l.getBlockX(), l.getBlockY(), l.getBlockZ());
                p.sendMessage("\u0422\u043e\u0447\u043a\u0430 \u0441\u043f\u0430\u0432\u043d\u0430 \u0443\u0441\u043f\u0435\u0448\u043d\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430 \u043d\u0430 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u0445: §7" + p.getLocation().getBlockX() + " " + p.getLocation().getBlockY() + " " + p.getLocation().getBlockZ());
            }
            if (e.getSlot() == 13) {
                playersBuild = Bukkit.createInventory((InventoryHolder)null, 54, "\u041f\u0440\u0430\u0432\u0430 \u043d\u0430 \u0441\u0442\u0440\u043e\u0438\u0442\u0435\u043b\u044c\u0441\u0442\u0432\u043e");
                World world = p.getLocation().getWorld();
                for (int v = 0; v < world.getPlayers().size(); ++v) {
                    Player pl2 = world.getPlayers().get(v);
                    if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        List<String> lore1 = new ArrayList<String>();
                        if (pl2.getGameMode() == GameMode.CREATIVE) {
                            lore1.add("§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                        }
                        else {
                            lore1.add("§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                        }
                        accountSkullMeta.setLore((List)lore1);
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        playersBuild.addItem(new ItemStack[] { accountBtn });
                    }
                }
                p.openInventory(playersBuild);
            }
            if (e.getSlot() == 28) {
                this.playersKick = Bukkit.createInventory((InventoryHolder)null, 54, "\u041a\u0438\u043a \u0438\u0433\u0440\u043e\u043a\u0430");
                World world = p.getLocation().getWorld();
                for (int v = 0; v < world.getPlayers().size(); ++v) {
                    Player pl2 = world.getPlayers().get(v);
                    if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        List<String> lore1 = new ArrayList<String>();
                        lore1.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0433\u043d\u0430\u0442\u044c \u044d\u0442\u043e\u0433\u043e \u0438\u0433\u0440\u043e\u043a\u0430.");
                        accountSkullMeta.setLore((List)lore1);
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                        this.playersKick.addItem(new ItemStack[] { accountBtn });
                    }
                }
                p.openInventory(this.playersKick);
            }
            if (e.getSlot() == 29) {
                this.gameRules = Bukkit.createInventory(null, 27, "Игровые правила");
                boolean canPVP = getConfig().getBoolean("players." + p.getName() + "." + "worldOptions.canPVP");
                boolean canInteract = getConfig().getBoolean("players." + p.getName() + "." + "worldOptions.canInteract");
                boolean canSeeLike = getConfig().getBoolean("players." + p.getName() + "." + "worldOptions.canSeeLike");
                World w3 = p.getLocation().getWorld();
                ItemStack item = new ItemStack(Material.BLAZE_POWDER);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§eРаспространение огня");
                List<String> list = new ArrayList<String>();
                if (w3.getGameRuleValue("doFireTick").equalsIgnoreCase("true")) {
                    list.add("§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                }
                else {
                    list.add("§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                }
                itemMeta.setLore((List)list);
                item.setItemMeta(itemMeta);
                this.gameRules.setItem(0, item);
                ItemStack item1 = new ItemStack(Material.ROTTEN_FLESH);
                ItemMeta itemMeta1 = item1.getItemMeta();
                itemMeta1.setDisplayName("§eЛут с мобов");
                List<String> list1 = new ArrayList<String>();
                if (w3.getGameRuleValue("doMobLoot").equalsIgnoreCase("true")) {
                    list1.add("§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                }
                else {
                    list1.add("§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                }
                itemMeta1.setLore(list1);
                item1.setItemMeta(itemMeta1);
                this.gameRules.setItem(1, item1);
                ItemStack item2 = new ItemStack(Material.SLIME_BALL);
                ItemMeta itemMeta2 = item2.getItemMeta();
                itemMeta2.setDisplayName("§eДроп с блоков");
                List<String> list2 = new ArrayList<String>();
                if (w3.getGameRuleValue("doTileDrops").equalsIgnoreCase("true")) {
                    list2.add("§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                }
                else {
                    list2.add("§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
                }
                itemMeta2.setLore(list2);
                item2.setItemMeta(itemMeta2);
                this.gameRules.setItem(2, item2);
                ItemStack item3 = new ItemStack(Material.IRON_SWORD);
                ItemMeta itemMeta3 = item3.getItemMeta();
                itemMeta3.setDisplayName("§eПвП в мире");
                List<String> list3 = new ArrayList<String>();
                if (canPVP) {
                    list3.add("§aВключено");
                }
                else {
                    list3.add("§cВыключено");
                }
                itemMeta3.setLore(list3);
                item3.setItemMeta(itemMeta3);
                this.gameRules.setItem(3, item3);
                ItemStack item4 = new ItemStack(Material.FENCE_GATE);
                ItemMeta itemMeta4 = item4.getItemMeta();
                itemMeta4.setDisplayName("§eВзаимодействие с миром §8(использование рычагов, дверей, сундуков, плит и т.д.)");
                List<String> list4 = new ArrayList<String>();
                if (canInteract) {
                    list4.add("§aВключено");
                }
                else {
                    list4.add("§cВыключено");
                }
                itemMeta4.setLore(list4);
                item4.setItemMeta(itemMeta4);
                this.gameRules.setItem(4, item4);
                ItemStack item5 = new ItemStack(Material.FENCE_GATE);
                ItemMeta itemMeta5 = item4.getItemMeta();
                itemMeta5.setDisplayName("§eОтображение оценки мира");
                List<String> list5 = new ArrayList<String>();
                if (canSeeLike) {
                    list5.add("§aВключено");
                }
                else {
                    list5.add("§cВыключено");
                }
                itemMeta5.setLore(list5);
                item5.setItemMeta(itemMeta5);
                this.gameRules.setItem(5, item5);
                p.openInventory(this.gameRules);
            }
            if (e.getSlot() == 30) {
                World w3 = p.getLocation().getWorld();
                WorldBorder wb2 = w3.getWorldBorder();
                this.worldBorder = Bukkit.createInventory(null, 27, "Размер мира");
                ItemStack size = new ItemStack(Material.GRASS);
                ItemMeta sizeMeta = size.getItemMeta();
                sizeMeta.setDisplayName("§f100 \u0431\u043b\u043e\u043a\u043e\u0432");
                List<String> lore4 = new ArrayList<String>();
                if (wb2.getSize() == 100.0) {
                    lore4.add("§a\u0412\u044b\u0431\u0440\u0430\u043d\u043e");
                }
                else {
                    lore4.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c");
                }
                sizeMeta.setLore((List)lore4);
                size.setItemMeta(sizeMeta);
                this.worldBorder.setItem(0, size);
                ItemStack size2 = new ItemStack(Material.GRASS);
                ItemMeta sizeMeta2 = size2.getItemMeta();
                sizeMeta2.setDisplayName("§f200 \u0431\u043b\u043e\u043a\u043e\u0432");
                List<String> lore5 = new ArrayList<String>();
                if (this.getPlayerGroupId(p) > 0) {
                    if (wb2.getSize() == 200.0) {
                        lore5.add("§a\u0412\u044b\u0431\u0440\u0430\u043d\u043e");
                    }
                    else {
                        lore5.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c");
                    }
                }
                else {
                    lore5.add("§7\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u043e \u0441 §b[Premium]");
                }
                sizeMeta2.setLore((List)lore5);
                size2.setItemMeta(sizeMeta2);
                this.worldBorder.setItem(1, size2);
                ItemStack size3 = new ItemStack(Material.GRASS);
                ItemMeta sizeMeta3 = size3.getItemMeta();
                sizeMeta3.setDisplayName("§f250 \u0431\u043b\u043e\u043a\u043e\u0432");
                List<String> lore6 = new ArrayList<String>();
                if (this.getPlayerGroupId(p) > 1) {
                    if (wb2.getSize() == 250.0) {
                        lore6.add("§a\u0412\u044b\u0431\u0440\u0430\u043d\u043e");
                    }
                    else {
                        lore6.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c");
                    }
                }
                else {
                    lore6.add("§7\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u043e \u0441 §a[Lord]");
                }
                sizeMeta3.setLore((List)lore6);
                size3.setItemMeta(sizeMeta3);
                this.worldBorder.setItem(2, size3);
                ItemStack size4 = new ItemStack(Material.GRASS);
                ItemMeta sizeMeta4 = size4.getItemMeta();
                sizeMeta4.setDisplayName("§f300 \u0431\u043b\u043e\u043a\u043e\u0432");
                List<String> lore7 = new ArrayList<String>();
                if (this.getPlayerGroupId(p) > 2) {
                    if (wb2.getSize() == 300.0) {
                        lore7.add("§a\u0412\u044b\u0431\u0440\u0430\u043d\u043e");
                    }
                    else {
                        lore7.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c");
                    }
                }
                else {
                    lore7.add("§7\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u043e \u0441 §d[Deluxe]");
                }
                sizeMeta4.setLore((List)lore7);
                size4.setItemMeta(sizeMeta4);
                this.worldBorder.setItem(3, size4);
                p.openInventory(this.worldBorder);
                ItemStack size5 = new ItemStack(Material.GRASS);
                ItemMeta sizeMeta5 = size5.getItemMeta();
                sizeMeta5.setDisplayName("§f350 \u0431\u043b\u043e\u043a\u043e\u0432");
                List<String> lore8 = new ArrayList<String>();
                if (this.getPlayerGroupId(p) > 3) {
                    if (wb2.getSize() == 350.0) {
                        lore8.add("§a\u0412\u044b\u0431\u0440\u0430\u043d\u043e");
                    }
                    else {
                        lore8.add("§7\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0447\u0442\u043e\u0431\u044b \u0432\u044b\u0431\u0440\u0430\u0442\u044c");
                    }
                }
                else {
                    lore8.add("§7\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u043e \u0441 §4[Ultra]");
                }
                sizeMeta5.setLore((List)lore8);
                size5.setItemMeta(sizeMeta5);
                this.worldBorder.setItem(4, size5);
                p.openInventory(this.worldBorder);
            }
            if (e.getSlot() == 31) {
            	playersFly = Bukkit.createInventory((InventoryHolder)null, 54, "Права на флай");
                World world = p.getLocation().getWorld();
                for (int v = 0; v < world.getPlayers().size(); ++v) {
                    Player pl2 = world.getPlayers().get(v);
                    if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                        ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                        accountSkullMeta.setOwner(pl2.getName());
                        accountSkullMeta.setDisplayName(pl2.getDisplayName());
                        List<String> lore1 = new ArrayList<String>();
                        if (pl2.getAllowFlight()) {
                            lore1.add("§aВключено");
                        }
                        else {
                            lore1.add("§cВыключено");
                        }
                        accountSkullMeta.setLore((List)lore1);
                        accountSkullMeta.setLocalizedName(pl2.getName());
                        accountBtn.setItemMeta(accountSkullMeta);
                        playersFly.addItem(accountBtn);
                    }
                }
                p.openInventory(playersFly);
            }
            if (e.getSlot() == 16) {
                p.closeInventory();
                p.sendMessage("§e\u0414\u043b\u044f \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u044f \u043c\u0438\u0440\u0430 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u0443:");
                p.sendMessage("§f/name <\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435>");
            }
            if (e.getSlot() == 15) {
                p.closeInventory();
                if (this.getConfig().getBoolean("players." + p.getName() + ".worldOptions.canJoin")) {
                    this.getConfig().set("players." + p.getName() + ".worldOptions.canJoin", false);
                    p.sendTitle("§c\u041c\u0438\u0440 \u0437\u0430\u043a\u0440\u044b\u0442!", "§7\u0418\u0433\u0440\u043e\u043a\u0438 \u043d\u0435 \u0441\u043c\u043e\u0433\u0443\u0442 \u0437\u0430\u0439\u0442\u0438 \u0432 \u0432\u0430\u0448 \u043c\u0438\u0440");
                    saveConfig();
                    p.closeInventory();
                }
                else {
                    this.getConfig().set("players." + p.getName() + ".worldOptions.canJoin", true);
                    p.sendTitle("§a\u041c\u0438\u0440 \u043e\u0442\u043a\u0440\u044b\u0442!", "§7\u0418\u0433\u0440\u043e\u043a\u0438 \u0441\u043c\u043e\u0433\u0443\u0442 \u0437\u0430\u0439\u0442\u0438 \u0432 \u0432\u0430\u0448 \u043c\u0438\u0440");
                    
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 33) {
            	if(e.isLeftClick()){
            		playersBan = Bukkit.createInventory(null, 54, "Чёрный список");
                    World world = p.getLocation().getWorld();
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                            ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                            SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                            accountSkullMeta.setOwner(pl2.getName());
                            accountSkullMeta.setDisplayName(pl2.getDisplayName());
                            List<String> lore1 = new ArrayList<String>();
                            lore1.add("§7Нажмите чтобы забанить");
                            accountSkullMeta.setLore((List)lore1);
                            accountSkullMeta.setLocalizedName(pl2.getName());
                            accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                            playersBan.addItem(accountBtn);
                        }
                    }
                    p.openInventory(playersBan);
            	}else if(e.isRightClick()){
            		playersBanDel = Bukkit.createInventory(null, 54, "Чёрный список (Разбан)");
                    World world = p.getLocation().getWorld();
                    List<String> banPlays = getConfig().getStringList("players." + name(p) + ".banList");
                    for (int v = 0; v < banPlays.size(); ++v) {
                        Player pl2 = Bukkit.getPlayer(banPlays.get(v));
                        if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                            ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                            SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                            accountSkullMeta.setOwner(pl2.getName());
                            if(pl2 == null){
                            	accountSkullMeta.setDisplayName("§7" + banPlays.get(v) + " §8(Не в сети)");
                            }else{
                            	accountSkullMeta.setDisplayName(pl2.getDisplayName());
                            }
                            List<String> lore1 = new ArrayList<String>();
                            lore1.add("§7Нажмите чтобы разбанить");
                            accountSkullMeta.setLore((List)lore1);
                            accountSkullMeta.setLocalizedName(pl2.getName());
                            accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                            playersBanDel.addItem(accountBtn);
                        }
                    }
                    p.openInventory(playersBanDel);
            	}
            }
            if(e.getSlot() == 34){
            	e.setCancelled(true);
            	if(e.isLeftClick()){
            		playersWhite = Bukkit.createInventory(null, 54, "Белый список");
                    World world = p.getLocation().getWorld();
                    for (int v = 0; v < world.getPlayers().size(); ++v) {
                        Player pl2 = world.getPlayers().get(v);
                        if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                            ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                            SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                            accountSkullMeta.setOwner(pl2.getName());
                            accountSkullMeta.setDisplayName(pl2.getDisplayName());
                            List<String> lore1 = new ArrayList<String>();
                            lore1.add("§7Нажмите чтобы добавить в белый список.");
                            accountSkullMeta.setLore((List)lore1);
                            accountSkullMeta.setLocalizedName(pl2.getName());
                            accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                            playersWhite.addItem(accountBtn);
                        }
                    }
                    p.openInventory(playersWhite);
            	}else if(e.isRightClick()){
            		playersWhiteDel = Bukkit.createInventory(null, 54, "Белый список (Удаление)");
                    World world = p.getLocation().getWorld();
                    List<String> banPlays = getConfig().getStringList("players." + name(p) + ".whiteList");
                    for (int v = 0; v < banPlays.size(); ++v) {
                        Player pl2 = Bukkit.getPlayer(banPlays.get(v));
                        if (!pl2.getName().equalsIgnoreCase(p.getName())) {
                            ItemStack accountBtn = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                            SkullMeta accountSkullMeta = (SkullMeta)accountBtn.getItemMeta();
                            accountSkullMeta.setOwner(pl2.getName());
                            if(pl2 == null){
                            	accountSkullMeta.setDisplayName("§7" + banPlays.get(v) + " §8(Не в сети)");
                            }else{
                            	accountSkullMeta.setDisplayName(pl2.getDisplayName());
                            }
                            List<String> lore1 = new ArrayList<String>();
                            lore1.add("§7Нажмите чтобы убрать из белого списка");
                            accountSkullMeta.setLore(lore1);
                            accountSkullMeta.setLocalizedName(pl2.getName());
                            accountBtn.setItemMeta((ItemMeta)accountSkullMeta);
                            playersWhiteDel.addItem(accountBtn);
                        }
                    }
                    p.openInventory(playersWhiteDel);
            	}
            }
        }
        if (i.equals(this.worldActions)) {
            e.setCancelled(true);
            if (e.getSlot() == 1) {
                p.closeInventory();
                p.sendMessage("\u0421\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0435 \u043c\u0438\u0440\u0430...");
                World w3 = Bukkit.getWorld(p.getName());
                w3.save();
                this.sendWorldMessage(p.getName(), "\u041c\u0438\u0440 \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d!");
            }
            if (e.getSlot() == 3) {
                String worldName3 = p.getName();
                for (int num2 = 0; num2 < this.gamesList.size(); ++num2) {
                    World w2 = Bukkit.getWorld(worldName3);
                    List<Player> pls = (List<Player>)w2.getPlayers();
                    for (Player pl : pls) {
                        this.tpToHub(pl, true);
                        pl.sendTitle("\u041c\u0438\u0440 \u0431\u044b\u043b \u0437\u0430\u043a\u0440\u044b\u0442", "");
                        pl.sendMessage("\u041c\u0438\u0440 \u0431\u044b\u043b \u0437\u0430\u043a\u0440\u044b\u0442.");
                    }
                    this.worldsList.remove(String.valueOf(worldName3) + this.split + this.gamesList.get(num2));
                }
                p.closeInventory();
            }
        }
        if (i.equals(this.worldTime)) {
            if (e.getSlot() == 10) {
                p.closeInventory();
                World w3 = Bukkit.getWorld(p.getName());
                w3.setTime(0L);
                p.sendMessage("\u0418\u0433\u0440\u043e\u0432\u043e\u0435 \u0432\u0440\u0435\u043c\u044f \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u043e!");
            }
            if (e.getSlot() == 12) {
                p.closeInventory();
                World w3 = Bukkit.getWorld(p.getName());
                w3.setTime(6000L);
                p.sendMessage("\u0418\u0433\u0440\u043e\u0432\u043e\u0435 \u0432\u0440\u0435\u043c\u044f \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u043e!");
            }
            if (e.getSlot() == 14) {
                p.closeInventory();
                World w3 = Bukkit.getWorld(p.getName());
                w3.setTime(13000L);
                p.sendMessage("\u0418\u0433\u0440\u043e\u0432\u043e\u0435 \u0432\u0440\u0435\u043c\u044f \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u043e!");
            }
            if (e.getSlot() == 16) {
                p.closeInventory();
                World w3 = Bukkit.getWorld(p.getName());
                w3.setTime(18000L);
                p.sendMessage("\u0418\u0433\u0440\u043e\u0432\u043e\u0435 \u0432\u0440\u0435\u043c\u044f \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u043e!");
            }
            e.setCancelled(true);
        }
        if (i.equals(this.playersBuild)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                Player getPl = Bukkit.getPlayer(playerName);
                if (p.getLocation().getWorld().getPlayers().contains(getPl)) {
                    if (getPl.getGameMode() == GameMode.CREATIVE) {
                        getPl.setGameMode(GameMode.ADVENTURE);
                        getPl.sendTitle("§c\u0412\u0430\u043c \u0437\u0430\u043f\u0440\u0435\u0442\u0438\u043b\u0438 \u0441\u0442\u0440\u043e\u0438\u0442\u044c!", "");
                    }
                    else {
                        getPl.setGameMode(GameMode.CREATIVE);
                        getPl.sendTitle("§a\u0412\u0430\u043c \u0440\u0430\u0437\u0440\u0435\u0448\u0438\u043b\u0438 \u0441\u0442\u0440\u043e\u0438\u0442\u044c!", "");
                    }
                }
                p.closeInventory();
            }
        }
        if (i.equals(playersKick)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                Player getPl = Bukkit.getPlayer(playerName);
                if (p.getLocation().getWorld().getPlayers().contains(getPl)) {
                    sendWorldMessage(getPl.getLocation().getWorld().getName(), String.valueOf(getPl.getDisplayName()) + "§f был выгнат из мира.");
                    tpToHub(getPl, true);
                    getPl.sendTitle("§7Вас выгнали из мира!", "");
                }
                p.closeInventory();
            }
        }
        if (i.equals(playersBan)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                List<String> banPlays = getConfig().getStringList("players." + name(p) + ".banList");
                Player getPl = Bukkit.getPlayer(playerName);
                if (p.getLocation().getWorld().getPlayers().contains(getPl)) {
                	if(!banPlays.contains(playerName)){
                		banPlays.add(playerName);
                		getConfig().set("players." + name(p) + ".banList", banPlays);
                        sendWorldMessage(getPl.getLocation().getWorld().getName(), getPl.getDisplayName() + "§f был забанен.");
                        tpToHub(getPl, true);
                        getPl.sendTitle("§7Вы были забанены", "");
                        saveConfig();
                	}
                }
                p.closeInventory();
            }
        }
        if (i.equals(playersBanDel)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                List<String> banPlays = getConfig().getStringList("players." + name(p) + ".banList");
                if(banPlays.contains(playerName)){
                	banPlays.remove(playerName);
                	getConfig().set("players." + name(p) + ".banList", banPlays);
                	p.sendMessage("§fИгрок "+playerName+" §fбыл разбанен.");
                }else{
                	p.sendMessage("§cПроизошла ошибка.");
                }
                saveConfig();
                p.closeInventory();
            }
        }
        if (i.equals(playersWhite)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                List<String> banPlays = getConfig().getStringList("players." + name(p) + ".whiteList");
                Player getPl = Bukkit.getPlayer(playerName);
                if (p.getLocation().getWorld().getPlayers().contains(getPl)) {
                	if(!banPlays.contains(playerName)){
                		banPlays.add(playerName);
                		getConfig().set("players." + name(p) + ".whiteList", banPlays);
                        sendWorldMessage(getPl.getLocation().getWorld().getName(), getPl.getDisplayName() + "§f был забанен.");
                        getPl.sendTitle("§7Вы были добавлены", "§fв белый список.");
                        saveConfig();
                	}
                }
                p.closeInventory();
            }
        }
        if (i.equals(playersWhiteDel)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                List<String> banPlays = getConfig().getStringList("players." + name(p) + ".whiteList");
                if(banPlays.contains(playerName)){
                	banPlays.remove(playerName);
                	getConfig().set("players." + name(p) + ".whiteList", banPlays);
                	p.sendMessage("§fИгрок "+playerName+" §fбыл убран из белого списка.");
                }else{
                	p.sendMessage("§cПроизошла ошибка.");
                }
                saveConfig();
                p.closeInventory();
            }
        }
        if (i.equals(playersFly)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                String playerName = e.getCurrentItem().getItemMeta().getLocalizedName();
                Player getPl = Bukkit.getPlayer(playerName);
                if (p.getLocation().getWorld().getPlayers().contains(getPl)) {
                	if(p.getAllowFlight()){
                		p.setAllowFlight(false);
                		getPl.sendTitle("§cВам запретили флай!", "");
                	}else{
                		p.setAllowFlight(true);
                		getPl.sendTitle("§aВам разрешили флай!", "");
                	}
                }
                p.closeInventory();
            }
        }
        if (i.equals(this.gameRules)) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                World w3 = p.getLocation().getWorld();
                if (w3.getGameRuleValue("doFireTick").equalsIgnoreCase("true")) {
                    w3.setGameRuleValue("doFireTick", "false");
                    p.closeInventory();
                    p.sendTitle("§e\u0420\u0430\u0441\u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u0435\u043d\u0438\u0435 \u043e\u0433\u043d\u044f", "§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e!");
                }
                else {
                    w3.setGameRuleValue("doFireTick", "true");
                    p.closeInventory();
                    p.sendTitle("§e\u0420\u0430\u0441\u043f\u0440\u043e\u0441\u0442\u0440\u0430\u043d\u0435\u043d\u0438\u0435 \u043e\u0433\u043d\u044f", "§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e!");
                }
            }
            if (e.getSlot() == 1) {
                World w3 = p.getLocation().getWorld();
                if (w3.getGameRuleValue("doMobLoot").equalsIgnoreCase("true")) {
                    w3.setGameRuleValue("doMobLoot", "false");
                    p.closeInventory();
                    p.sendTitle("§e\u041b\u0443\u0442 \u0441 \u043c\u043e\u0431\u043e\u0432", "§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e!");
                }
                else {
                    w3.setGameRuleValue("doMobLoot", "true");
                    p.closeInventory();
                    p.sendTitle("§e\u041b\u0443\u0442 \u0441 \u043c\u043e\u0431\u043e\u0432", "§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e!");
                }
            }
            if (e.getSlot() == 2) {
                World w3 = p.getLocation().getWorld();
                if (w3.getGameRuleValue("doTileDrops").equalsIgnoreCase("true")) {
                    w3.setGameRuleValue("doTileDrops", "false");
                    p.closeInventory();
                    p.sendTitle("§e\u0414\u0440\u043e\u043f \u0441 \u0431\u043b\u043e\u043a\u043e\u0432", "§c\u0412\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e!");
                }
                else {
                    w3.setGameRuleValue("doTileDrops", "true");
                    p.closeInventory();
                    p.sendTitle("§e\u0414\u0440\u043e\u043f \u0441 \u0431\u043b\u043e\u043a\u043e\u0432", "§a\u0412\u043a\u043b\u044e\u0447\u0435\u043d\u043e!");
                }
            }
            if(e.getSlot() == 3){
            	String valuePath = "players." + p.getName() + "." + "worldOptions.canPVP";
            	if(getConfig().getBoolean(valuePath)){
            		getConfig().set(valuePath, false);
            		p.closeInventory();
            		p.sendTitle("§eПвП в мире", "§cВыключено!");
            	}else{
            		getConfig().set(valuePath, true);
            		p.closeInventory();
            		p.sendTitle("§eПвП в мире", "§aВключено!");
            	}
            }
            if(e.getSlot() == 4){
            	String valuePath = "players." + p.getName() + "." + "worldOptions.canInteract";
            	if(getConfig().getBoolean(valuePath)){
            		getConfig().set(valuePath, false);
            		p.closeInventory();
            		p.sendTitle("§eВзаимодействие с миром", "§cВыключено!");
            	}else{
            		getConfig().set(valuePath, true);
            		p.closeInventory();
            		p.sendTitle("§eВзаимодействие в миром", "§aВключено!");
            	}
            }
            if(e.getSlot() == 5){
            	String valuePath = "players." + p.getName() + "." + "worldOptions.canSeeLike";
            	if(getConfig().getBoolean(valuePath)){
            		getConfig().set(valuePath, false);
            		p.closeInventory();
            		p.sendTitle("§eОтображение оценки мира", "§cВыключено!");
            	}else{
            		getConfig().set(valuePath, true);
            		p.closeInventory();
            		p.sendTitle("§eОтображение оценки мира", "§aВключено!");
            	}
            }
        }
        if (i.equals(this.KitPvP)) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                p.setGameMode(GameMode.ADVENTURE);
                p.teleport(p.getLocation().getWorld().getSpawnLocation());
                p.closeInventory();
                ItemStack sword = new ItemStack(Material.STONE_SWORD);
                ItemMeta swordMeta = sword.getItemMeta();
                swordMeta.setUnbreakable(true);
                sword.setItemMeta(swordMeta);
                ItemStack beef = new ItemStack(Material.COOKED_BEEF, 16);
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                ItemMeta cpMeta = chestplate.getItemMeta();
                cpMeta.setUnbreakable(true);
                chestplate.setItemMeta(cpMeta);
                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                ItemMeta lgnMeta = leggings.getItemMeta();
                lgnMeta.setUnbreakable(true);
                leggings.setItemMeta(lgnMeta);
                p.getInventory().addItem(new ItemStack[] { sword });
                p.getInventory().addItem(new ItemStack[] { beef });
                p.getInventory().setChestplate(chestplate);
                p.getInventory().setLeggings(leggings);
            }
            if (e.getSlot() == 1) {
                if (this.getConfig().getBoolean("players." + p.getName() + ".kit.voin")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.teleport(p.getLocation().getWorld().getSpawnLocation());
                    p.closeInventory();
                    ItemStack sword = new ItemStack(Material.IRON_SWORD);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    sword.setItemMeta(swordMeta);
                    ItemStack beef = new ItemStack(Material.COOKED_BEEF, 16);
                    ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                    ItemMeta cpMeta = chestplate.getItemMeta();
                    cpMeta.setUnbreakable(true);
                    chestplate.setItemMeta(cpMeta);
                    ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                    ItemMeta lgnMeta = leggings.getItemMeta();
                    lgnMeta.setUnbreakable(true);
                    leggings.setItemMeta(lgnMeta);
                    p.getInventory().addItem(new ItemStack[] { sword });
                    p.getInventory().addItem(new ItemStack[] { beef });
                    p.getInventory().setChestplate(chestplate);
                    p.getInventory().setLeggings(leggings);
                }
                else if (this.getConfig().getInt("players." + p.getName() + ".money") >= 20) {
                    String moneys = "players." + p.getName() + ".money";
                    this.getConfig().set(moneys, (Object)(this.getConfig().getInt(moneys) - 20));
                    this.getConfig().set("players." + p.getName() + ".kit.voin", (Object)true);
                    p.closeInventory();
                    p.sendTitle("§e\u0412\u044b \u043a\u0443\u043f\u0438\u043b\u0438 \u043d\u0430\u0431\u043e\u0440", "§7\u0412\u043e\u0438\u043d");
                    this.saveConfig();
                }
                else {
                    p.closeInventory();
                    p.sendTitle("§c\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0441\u0440\u0435\u0434\u0441\u0442\u0432!", "");
                }
            }
            if (e.getSlot() == 2) {
                if (this.getConfig().getBoolean("players." + p.getName() + ".kit.archer")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.teleport(p.getLocation().getWorld().getSpawnLocation());
                    p.closeInventory();
                    ItemStack sword = new ItemStack(Material.STONE_SWORD);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    sword.setItemMeta(swordMeta);
                    ItemStack bow = new ItemStack(Material.BOW);
                    ItemMeta bowMeta = bow.getItemMeta();
                    bowMeta.setUnbreakable(true);
                    bow.setItemMeta(bowMeta);
                    ItemStack arrows = new ItemStack(Material.ARROW, 64);
                    ItemStack beef2 = new ItemStack(Material.COOKED_BEEF, 16);
                    ItemStack chestplate2 = new ItemStack(Material.IRON_CHESTPLATE);
                    ItemMeta cpMeta2 = chestplate2.getItemMeta();
                    cpMeta2.setUnbreakable(true);
                    chestplate2.setItemMeta(cpMeta2);
                    ItemStack leggings2 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                    ItemMeta lgnMeta2 = leggings2.getItemMeta();
                    lgnMeta2.setUnbreakable(true);
                    leggings2.setItemMeta(lgnMeta2);
                    p.getInventory().addItem(new ItemStack[] { sword });
                    p.getInventory().addItem(new ItemStack[] { bow });
                    p.getInventory().addItem(new ItemStack[] { arrows });
                    p.getInventory().addItem(new ItemStack[] { arrows });
                    p.getInventory().addItem(new ItemStack[] { beef2 });
                    p.getInventory().setChestplate(chestplate2);
                    p.getInventory().setLeggings(leggings2);
                }
                else if (this.getConfig().getInt("players." + p.getName() + ".money") >= 50) {
                    String moneys = "players." + p.getName() + ".money";
                    this.getConfig().set(moneys, (Object)(this.getConfig().getInt(moneys) - 50));
                    this.getConfig().set("players." + p.getName() + ".kit.archer", (Object)true);
                    p.closeInventory();
                    p.sendTitle("§e\u0412\u044b \u043a\u0443\u043f\u0438\u043b\u0438 \u043d\u0430\u0431\u043e\u0440", "§7\u041b\u0443\u0447\u043d\u0438\u043a");
                    this.saveConfig();
                }
                else {
                    p.closeInventory();
                    p.sendTitle("§c\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0441\u0440\u0435\u0434\u0441\u0442\u0432!", "");
                }
            }
            if (e.getSlot() == 3) {
                if (this.getConfig().getBoolean("players." + p.getName() + ".kit.bersek")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.teleport(p.getLocation().getWorld().getSpawnLocation());
                    p.closeInventory();
                    ItemStack sword = new ItemStack(Material.IRON_AXE);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    sword.setItemMeta(swordMeta);
                    ItemStack beef = new ItemStack(Material.COOKED_BEEF, 32);
                    ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                    ItemMeta cpMeta = chestplate.getItemMeta();
                    cpMeta.setUnbreakable(true);
                    chestplate.setItemMeta(cpMeta);
                    ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                    ItemMeta lgnMeta = leggings.getItemMeta();
                    lgnMeta.setUnbreakable(true);
                    leggings.setItemMeta(lgnMeta);
                    ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
                    ItemMeta bMeta = boots.getItemMeta();
                    bMeta.setUnbreakable(true);
                    boots.setItemMeta(bMeta);
                    p.getInventory().addItem(new ItemStack[] { sword });
                    p.getInventory().addItem(new ItemStack[] { beef });
                    p.getInventory().setChestplate(chestplate);
                    p.getInventory().setChestplate(leggings);
                    p.getInventory().setBoots(boots);
                }
                else if (this.getConfig().getInt("players." + p.getName() + ".money") >= 80) {
                    String moneys = "players." + p.getName() + ".money";
                    this.getConfig().set(moneys, (Object)(this.getConfig().getInt(moneys) - 80));
                    this.getConfig().set("players." + p.getName() + ".kit.bersek", (Object)true);
                    p.closeInventory();
                    p.sendTitle("§e\u0412\u044b \u043a\u0443\u043f\u0438\u043b\u0438 \u043d\u0430\u0431\u043e\u0440", "§7\u0411\u0435\u0440\u0441\u0435\u043a");
                    this.saveConfig();
                }
                else {
                    p.closeInventory();
                    p.sendTitle("§c\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0441\u0440\u0435\u0434\u0441\u0442\u0432!", "");
                }
            }
            if (e.getSlot() == 4) {
                if (this.getConfig().getBoolean("players." + p.getName() + ".kit.myaso")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.teleport(p.getLocation().getWorld().getSpawnLocation());
                    p.closeInventory();
                    ItemStack sword = new ItemStack(Material.DIAMOND_AXE);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    swordMeta.setDisplayName("§f\u041d\u043e\u0436");
                    sword.setItemMeta(swordMeta);
                    ItemStack beef = new ItemStack(Material.COOKED_MUTTON, 32);
                    ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                    ItemMeta cpMeta = chestplate.getItemMeta();
                    cpMeta.setUnbreakable(true);
                    chestplate.setItemMeta(cpMeta);
                    ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
                    ItemMeta lgnMeta = leggings.getItemMeta();
                    lgnMeta.setUnbreakable(true);
                    leggings.setItemMeta(lgnMeta);
                    p.getInventory().addItem(new ItemStack[] { sword });
                    p.getInventory().addItem(new ItemStack[] { beef });
                    p.getInventory().setChestplate(chestplate);
                    p.getInventory().setLeggings(leggings);
                }
                else if (this.getConfig().getInt("players." + p.getName() + ".money") >= 100) {
                    String moneys = "players." + p.getName() + ".money";
                    this.getConfig().set(moneys, (Object)(this.getConfig().getInt(moneys) - 100));
                    this.getConfig().set("players." + p.getName() + ".kit.myaso", (Object)true);
                    p.closeInventory();
                    p.sendTitle("§e\u0412\u044b \u043a\u0443\u043f\u0438\u043b\u0438 \u043d\u0430\u0431\u043e\u0440", "§7\u041c\u044f\u0441\u043d\u0438\u043a");
                    this.saveConfig();
                }
                else {
                    p.closeInventory();
                    p.sendTitle("§c\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0441\u0440\u0435\u0434\u0441\u0442\u0432!", "");
                }
            }
            if(e.getSlot() == 44){
            	p.closeInventory();
            	p.performCommand("bal");
            }
        }
        if (i.equals(this.worldBorder)) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                World w3 = p.getLocation().getWorld();
                WorldBorder wb2 = w3.getWorldBorder();
                wb2.setSize(100.0);
                p.closeInventory();
                p.sendMessage("§f\u0420\u0430\u0437\u043c\u0435\u0440 \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430 §7100§f.");
            }
            if (e.getSlot() == 1) {
                if (this.getPlayerGroupId(p) > 0) {
                    World w3 = p.getLocation().getWorld();
                    WorldBorder wb2 = w3.getWorldBorder();
                    wb2.setSize(200.0);
                    p.closeInventory();
                    p.sendMessage("§f\u0420\u0430\u0437\u043c\u0435\u0440 \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430 §7200§f.");
                }
                else {
                    p.closeInventory();
                    p.sendMessage("§c§l| §c\u0423 \u0432\u0430\u0441 \u043d\u0435\u0442 \u043f\u0440\u0430\u0432! \u041a\u0443\u043f\u0438\u0442\u044c \u0434\u043e\u043d\u0430\u0442 /donate");
                }
            }
            if (e.getSlot() == 2) {
                if (this.getPlayerGroupId(p) > 1) {
                    World w3 = p.getLocation().getWorld();
                    WorldBorder wb2 = w3.getWorldBorder();
                    wb2.setSize(250.0);
                    p.closeInventory();
                    p.sendMessage("§f\u0420\u0430\u0437\u043c\u0435\u0440 \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430 §7250§f.");
                }
                else {
                    p.closeInventory();
                    p.sendMessage("§c§l| §c\u0423 \u0432\u0430\u0441 \u043d\u0435\u0442 \u043f\u0440\u0430\u0432! \u041a\u0443\u043f\u0438\u0442\u044c \u0434\u043e\u043d\u0430\u0442 /donate");
                }
            }
            if (e.getSlot() == 3) {
                if (this.getPlayerGroupId(p) > 2) {
                    World w3 = p.getLocation().getWorld();
                    WorldBorder wb2 = w3.getWorldBorder();
                    wb2.setSize(300.0);
                    p.closeInventory();
                    p.sendMessage("§f\u0420\u0430\u0437\u043c\u0435\u0440 \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430 §7300§f.");
                }
                else {
                    p.closeInventory();
                    p.sendMessage("§c§l| §c\u0423 \u0432\u0430\u0441 \u043d\u0435\u0442 \u043f\u0440\u0430\u0432! \u041a\u0443\u043f\u0438\u0442\u044c \u0434\u043e\u043d\u0430\u0442 /donate");
                }
            }
            if (e.getSlot() == 4) {
                if (this.getPlayerGroupId(p) > 3) {
                    World w3 = p.getLocation().getWorld();
                    WorldBorder wb2 = w3.getWorldBorder();
                    wb2.setSize(350.0);
                    p.closeInventory();
                    p.sendMessage("§f\u0420\u0430\u0437\u043c\u0435\u0440 \u043c\u0438\u0440\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430 §7350§f.");
                }
                else {
                    p.closeInventory();
                    p.sendMessage("§c§l| §c\u0423 \u0432\u0430\u0441 \u043d\u0435\u0442 \u043f\u0440\u0430\u0432! \u041a\u0443\u043f\u0438\u0442\u044c \u0434\u043e\u043d\u0430\u0442 /donate");
                }
            }
        }
        if (i.equals(topWorlds)) {
        	e.setCancelled(true);
        }
        if (i.equals(this.social)) {
            e.setCancelled(true);
            if (e.getSlot() == 12) {
                p.closeInventory();
                p.sendMessage("§3ВКонтакте §f: " + vkurl);
            }
            if (e.getSlot() == 14) {
                p.closeInventory();
                p.sendMessage("§9Discord §f: " + discordurl);
            }
        }
        if (i.equals(events)) {
            e.setCancelled(true);
            if (e.getSlot() == 0) {
                openCode(p, "join");
            }else if (e.getSlot() == 1) {
                openCode(p, "quit");
            }
        }
        if (i.equals(code)) {
            e.setCancelled(true);
            if( e.getSlot() < 36 ){
            	if( e.isRightClick() ){
            		List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
            		if(!(e.getSlot() >= list.size())){
            			String s = list.remove(e.getSlot());
                		getConfig().set("players." + p.getName() + ".code." + mp.get(name(p)), list);
                		saveConfig();
                		openCode(p, mp.get(name(p)));
            		}
            	}
            	if( e.isLeftClick() ){
            		List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
            		if(!(e.getSlot() >= list.size())){
            			blockSet = Bukkit.createInventory(null, 9, "Управление блоком");
            			
            			ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
            			ItemMeta backMeta = back.getItemMeta();
            			backMeta.setDisplayName("§fНазад в код");
            			back.setItemMeta(backMeta);
            			blockSet.setItem(8, back);
            			
            			ItemStack item = new ItemStack(Material.CHEST);
            			ItemMeta itemMeta = item.getItemMeta();
            			itemMeta.setDisplayName("§fНастройка блока");
            			item.setItemMeta(itemMeta);
            			blockSet.setItem(0, item);
            			String[] parts = list.get(e.getSlot()).split("::");
            			if(parts[0].equalsIgnoreCase("action")){
            				if(parts[1].equalsIgnoreCase("sendmsg")){
                				ItemStack it = new ItemStack(Material.BOOK);
                    			ItemMeta itMeta = it.getItemMeta();
                    			itMeta.setDisplayName("§fТекст сообщения");
                    			List<String> lore = new ArrayList<String>();
                    			lore.add("§f §7Значение: " + parts[2]);
                    			itMeta.setLore(lore);
                    			it.setItemMeta(itMeta);
                    			blockSet.setItem(1, it);
                			}else if(parts[1].equalsIgnoreCase("sendtitle")){
                				ItemStack it = new ItemStack(Material.BOOK);
                    			ItemMeta itMeta = it.getItemMeta();
                    			itMeta.setDisplayName("§fТекст заголовка");
                    			List<String> lore = new ArrayList<String>();
                    			lore.add("§f §7Значение: " + parts[2].split("||")[0]);
                    			itMeta.setLore(lore);
                    			it.setItemMeta(itMeta);
                    			blockSet.setItem(1, it);
                    			
                    			ItemStack it1 = new ItemStack(Material.BOOK);
                    			ItemMeta itMeta1 = it1.getItemMeta();
                    			itMeta1.setDisplayName("§fТекст подзаголовка");
                    			List<String> lore1 = new ArrayList<String>();
                    			lore1.add("§f §7Значение: " + parts[2].split("||")[1]);
                    			itMeta1.setLore(lore1);
                    			it1.setItemMeta(itMeta1);
                    			blockSet.setItem(2, it1);
                			}
            				
            				if(parts[1].equalsIgnoreCase("additem")){
                				ItemStack it = new ItemStack(Material.IRON_INGOT);
                    			ItemMeta itMeta = it.getItemMeta();
                    			itMeta.setDisplayName("§fПредмет");
                    			List<String> lore = new ArrayList<String>();
                    			lore.add("§f §7Значение: ");
                    			itMeta.setLore(lore);
                    			it.setItemMeta(itMeta);
                    			blockSet.setItem(1, it);
                			}
            			}
            			
            			mpb.put(name(p), e.getSlot());
            			
            			p.openInventory(blockSet);
            		}
            	}
            }else{
            	if( e.getSlot() == 36 ){
                	List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
                	list.add("action:: :: ");
                	getConfig().set("players." + p.getName() + ".code." + mp.get(name(p)), list);
                	saveConfig();
                	openCode(p, mp.get(name(p)));
                }
            	if( e.getSlot() == 37 ){
                	List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
                	list.add("if:: :: ");
                	getConfig().set("players." + p.getName() + ".code." + mp.get(name(p)), list);
                	saveConfig();
                	openCode(p, mp.get(name(p)));
                }
            	if( e.getSlot() == 38 ){
                	List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
                	list.add("worldAction:: :: ");
                	getConfig().set("players." + p.getName() + ".code." + mp.get(name(p)), list);
                	saveConfig();
                	openCode(p, mp.get(name(p)));
                }
            }
        }
        if(i.equals(plMetod)){
        	e.setCancelled(true);
        	if( e.getSlot() == 26 ){
        		openCode(p, mp.get(name(p)));
        	}
        	if( e.getSlot() == 0 ){
        		plMetodInv = Bukkit.createInventory(null, 27, "Управление инвентарём");
        		
        		ItemStack item = new ItemStack(Material.CHEST);
    			ItemMeta itemMeta = item.getItemMeta();
    			itemMeta.setDisplayName("§fВыдать предмет");
    			List<String> lore = new ArrayList<String>();
    			lore.add("§7Выдать предмет игроку.");
    			lore.add("§7Атрибуты: §8Предмет");
    			item.setItemMeta(itemMeta);
    			plMetodInv.setItem(0, item);
    			p.openInventory(plMetodInv);
        	}
        	if( e.getSlot() == 1 ){
        		plMetodKom = Bukkit.createInventory(null, 27, "Коммуникация");
        		
        		ItemStack item = new ItemStack(Material.BOOK);
    			ItemMeta itemMeta = item.getItemMeta();
    			itemMeta.setDisplayName("§fОтправить сообщение");
    			List<String> lore = new ArrayList<String>();
    			lore.add("§7Отправить сообщение игроку в чат.");
    			lore.add("§7Атрибуты: §8Сообщение (Текст)");
    			item.setItemMeta(itemMeta);
    			plMetodKom.setItem(0, item);
    			
    			ItemStack item1 = new ItemStack(Material.SIGN);
    			ItemMeta itemMeta1 = item.getItemMeta();
    			itemMeta1.setDisplayName("§fОтправить титл");
    			List<String> lore1 = new ArrayList<String>();
    			lore1.add("§7Отправить сообщение на экран.");
    			lore1.add("§7Атрибуты: §81 строка (Текст)§7, §82 строка (Текст)");
    			item1.setItemMeta(itemMeta1);
    			plMetodKom.setItem(1, item1);
    			p.openInventory(plMetodKom);
        	}
        }
        if(i.equals(blockSet)){
        	e.setCancelled(true);
        	if( e.getSlot() == 0 ){
        		List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
        		String[] parts = list.get(e.getSlot()).split("::");
        		if(parts[0].equalsIgnoreCase("action")){
        			plMetod = Bukkit.createInventory(null, 27, "Действия для игрока");
        			
        			ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
        			ItemMeta backMeta = back.getItemMeta();
        			backMeta.setDisplayName("§fНазад в код");
        			back.setItemMeta(backMeta);
        			plMetod.setItem(26, back);
        			
        			ItemStack item = new ItemStack(Material.CHEST);
        			ItemMeta itemMeta = item.getItemMeta();
        			itemMeta.setDisplayName("§fУправление инвентарём");
        			item.setItemMeta(itemMeta);
        			plMetod.setItem(0, item);
        			
        			ItemStack item1 = new ItemStack(Material.BEACON);
        			ItemMeta itemMeta1 = item1.getItemMeta();
        			itemMeta1.setDisplayName("§fКоммуникация");
        			item1.setItemMeta(itemMeta1);
        			plMetod.setItem(1, item1);
        			
        			ItemStack item2 = new ItemStack(Material.BEACON);
        			ItemMeta itemMeta2 = item2.getItemMeta();
        			itemMeta2.setDisplayName("§fКоммуникация");
        			item2.setItemMeta(itemMeta2);
        			plMetod.setItem(1, item2);
        			
        			p.openInventory(plMetod);
        		}
        	}
        	if( e.getSlot() == 1 ){
        		List<String> list = getConfig().getStringList("players." + p.getName() + ".code." + mp.get(name(p)));
        		String[] parts = list.get((int) mpb.get(name(p))).split("::");
        		if(parts[0].equalsIgnoreCase("action")){
        			if(parts[1].equalsIgnoreCase("sendmsg")){
            			p.closeInventory();
                		mpt.put(name(p), true);
                		p.sendMessage("§fНапишите в чат текст сообщения. Для отмены напиши §eОТМЕНА");
            		}
        			if(parts[1].equalsIgnoreCase("sendtitle")){
            			p.closeInventory();
                		mpt.put(name(p), true);
                		p.sendMessage("§fНапишите в чат текст титла разделяя через §7::");
                		p.sendMessage("§fНапример: §7Заголовок::Подзаголовок");
                		p.sendMessage("§fДля отмены напиши §eОТМЕНА");
            		}
            		if(parts[1].equalsIgnoreCase("additem")){
            			getItem = Bukkit.createInventory(null, 9, "Перетащите предмет в пустую ячейку");
            			ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
            			ItemMeta itemMeta = item.getItemMeta();
            			itemMeta.setDisplayName("§f");
            			item.setItemMeta(itemMeta);
            			getItem.setItem(1, item);
            			getItem.setItem(2, item);
            			getItem.setItem(3, item);
            			getItem.setItem(5, item);
            			getItem.setItem(6, item);
            			getItem.setItem(7, item);
            			
            			ItemStack item1 = new ItemStack(Material.IRON_INGOT);
            			ItemMeta itemMeta1 = item1.getItemMeta();
            			itemMeta1.setDisplayName("§fПрименить");
            			item1.setItemMeta(itemMeta1);
            			getItem.setItem(0, item1);
            			
            			ItemStack item2 = new ItemStack(Material.SPECTRAL_ARROW);
            			ItemMeta itemMeta2 = item2.getItemMeta();
            			itemMeta2.setDisplayName("§fОтмена (назад в код)");
            			item2.setItemMeta(itemMeta2);
            			getItem.setItem(8, item2);
            			
            			p.openInventory(getItem);
            		}
        		}
        	}
        	if( e.getSlot() == 8 ){
        		openCode(p, mp.get(name(p)));
        	}
        }
        if(i.equals(plMetodKom)){
        	e.setCancelled(true);
        	if( e.getSlot() == 0 ){
        		List<String> list = getConfig().getStringList("players." + name(p) + ".code." + mp.get(name(p)));
        		list.set((int)mpb.get(name(p)), "action::sendmsg:: ");
        		getConfig().set("players." + name(p) + ".code." + mp.get(name(p)), list);
        		saveConfig();
        		openCode(p, mp.get(name(p)));
        	}
        	if( e.getSlot() == 1 ){
        		List<String> list = getConfig().getStringList("players." + name(p) + ".code." + mp.get(name(p)));
        		list.set((int)mpb.get(name(p)), "action::sendtitle:: || ");
        		getConfig().set("players." + name(p) + ".code." + mp.get(name(p)), list);
        		saveConfig();
        		openCode(p, mp.get(name(p)));
        	}
        }
        
        if(i.equals(plMetodInv)){
        	e.setCancelled(true);
        	if( e.getSlot() == 0 ){
        		List<String> list = getConfig().getStringList("players." + name(p) + ".code." + mp.get(name(p)));
        		list.set((int)mpb.get(name(p)), "action::additem:: ");
        		getConfig().set("players." + name(p) + ".code." + mp.get(name(p)), list);
        		saveConfig();
        		openCode(p, mp.get(name(p)));
        	}
        }
        
        if(i.equals(getItem)){
        	if( e.getSlotType() == SlotType.CONTAINER ){
        		if( e.getSlot() == 0 ){
            		e.setCancelled(true);
            		ItemStack is = e.getInventory().getItem(4);
            		ItemStackUtils isu = new ItemStackUtils();
            		String s = isu.ItemStackToString(is);
            		List<String> list = getConfig().getStringList("players." + name(p) + ".code." + mp.get(name(p)));
            		String[] parts = list.get((int) mpb.get(name(p))).split("::");
            		if(parts[0].equalsIgnoreCase("action")){
            			if(parts[1].equalsIgnoreCase("additem")){
            				list.set((int)mpb.get(name(p)), "action::additem::" + s);
                    		getConfig().set("players." + name(p) + ".code." + mp.get(name(p)), list);
                    		saveConfig();
                    		openCode(p, mp.get(name(p)));
            			}
            		}
            	}else if( e.getSlot() == 1 ){
            		e.setCancelled(true);
            	}else if( e.getSlot() == 2 ){
            		e.setCancelled(true);
            	}else if( e.getSlot() == 3 ){
            		e.setCancelled(true);
            	}else if( e.getSlot() == 5 ){
            		e.setCancelled(true);
            	}else if( e.getSlot() == 6 ){
            		e.setCancelled(true);
            	}else if( e.getSlot() == 7 ){
            		e.setCancelled(true);
            	}else if( e.getSlot() == 8 ){
            		e.setCancelled(true);
            		openCode(p, mp.get(name(p)));
            	}
        	}
        }
    }
    
    @EventHandler
    public void death(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.teleport(p.getLocation().getWorld().getSpawnLocation());
        e.setKeepInventory(true);
        e.setDeathMessage("");
        if (p.getKiller() == null) {
            this.sendWorldMessage(p.getLocation().getWorld().getName(), String.valueOf(p.getDisplayName()) + " §f\u043f\u043e\u0433\u0438\u0431.");
            p.sendTitle("§f\u0412\u044b \u043f\u043e\u0433\u0438\u0431\u043b\u0438", "");
        }
        else {
            this.sendWorldMessage(p.getLocation().getWorld().getName(), String.valueOf(p.getDisplayName()) + " §f\u0443\u0431\u0438\u0442 " + p.getKiller().getDisplayName());
            p.getKiller().sendTitle("§f\u0412\u044b \u0443\u0431\u0438\u043b\u0438 ", p.getDisplayName());
            if (p.getKiller().getGameMode() != GameMode.CREATIVE && p.getKiller().getName() != p.getName()) {
                int money = this.getConfig().getInt("players." + p.getKiller().getName() + ".money");
                if( getPlayerGroupId(p.getKiller()) == 0){
                	this.getConfig().set("players." + p.getKiller().getName() + ".money", (Object)(money + 1));
                	p.getKiller().sendMessage("§e+ 1 монета");
                }else if( getPlayerGroupId(p.getKiller()) == 1){
                	this.getConfig().set("players." + p.getKiller().getName() + ".money", (Object)(money + 1));
                	p.getKiller().sendMessage("§e+ 1 монета");
                }else if( getPlayerGroupId(p.getKiller()) > 1 ){
                	this.getConfig().set("players." + p.getKiller().getName() + ".money", (Object)(money + 2));
                	p.getKiller().sendMessage("§e+ 2 монеты");
                }else if( getPlayerGroupId(p.getKiller()) > 2 ){
                	this.getConfig().set("players." + p.getKiller().getName() + ".money", (Object)(money + 3));
                	p.getKiller().sendMessage("§e+ 3 монеты");
                }else if(getPlayerGroupId(p.getKiller()) > 3){
                	this.getConfig().set("players." + p.getKiller().getName() + ".money", (Object)(money + 3));
                	p.getKiller().sendMessage("§e+ 4 монеты");
                }
                this.saveConfig();
            }
            p.sendTitle("§f\u0412\u044b \u0443\u0431\u0438\u0442\u044b", p.getKiller().getDisplayName());
        }
    }
    
    @EventHandler
    public void respawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        e.setRespawnLocation(p.getLocation().getWorld().getSpawnLocation());
        p.teleport(p.getLocation().getWorld().getSpawnLocation());
    }
    
    @EventHandler
    public void ping(ServerListPingEvent e) {
    	if(tech){
    		e.setMotd(col("§3Технический перерыв!\n§3§lПодробнее: " + vkurl));
    	}else{
    		e.setMotd(col(getConfig().getString("motd-first")) + "\n" + col(getConfig().getString("motd-second")));
    	}
        e.setMaxPlayers(2020);
    }
    
    @EventHandler
    public void teleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SPECTATOR) {
            Location from = e.getFrom();
            Location to = e.getTo();
            if (from.getWorld().getName() != to.getWorld().getName()) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void projHit(ProjectileHitEvent e){
    	if( e.getEntity() instanceof Arrow ){
    		if( e.getEntity().getShooter() instanceof Player ){
    			Player p = (Player) e.getEntity().getShooter();
    			if( p.getLocation().getWorld().getName().equalsIgnoreCase("world")){
    				e.getEntity().remove();
    			}
    		}
    	}
    	if( e.getEntity() instanceof Snowball ){
    		if( e.getEntity().getShooter() instanceof Player ){
    			Player p = (Player) e.getEntity().getShooter();
    			if( p.getLocation().getWorld().getName().equalsIgnoreCase("world")){
    				Location l = p.getLocation();
    				Location le = e.getEntity().getLocation();
    				for(int i = 0; i < 10; i++){
    					l.getWorld().playEffect(le, Effect.SPELL, 100, 100);
    					l.getWorld().playSound(le, Sound.ENTITY_BLAZE_SHOOT, 2, 1);
    				}
    			}
    		}
    	}
    }
    @EventHandler
    public void place(BlockPlaceEvent e){
    	Player p = e.getPlayer();
    	if(p.getLocation().getWorld().getName().equalsIgnoreCase("world")){
    		if(getPlayerGroupId(p) != 6){
    			e.setCancelled(true);
    			p.sendMessage("Низя!");
    			System.out.println("Player " + p.getName() + "is break block " + e.getBlock().getType() + " in lobby at " + e.getBlock().getX() + " " + e.getBlock().getY() + " " + e.getBlock().getZ() + ".");
    		}
    	}else{
    		if(worldsList.contains(p.getLocation().getWorld().getName().split("%")[0] + split + "coding")){
    			Block block = e.getBlock();
    			Block downBlock = block.getRelative(0, -1, 0);
    			if(downBlock.getType() == Material.STAINED_GLASS){
    				if(downBlock.getData() == (byte) 15){
    					if(e.getItemInHand().getType() == Material.DIAMOND_BLOCK){
    						block.getRelative(0, 0, -1).setType(Material.WALL_SIGN);
    						block.getRelative(-1, 0, 0).setType(Material.DIAMOND_ORE);
    						/*Sign sign = (Sign) block.getRelative(0, 0, -1);
    						sign.setType(Material.WALL_SIGN);
    						sign.setLine(0, "§lСобытие игрока");*/
    					}
    				}else if(downBlock.getData() == (byte) 7){
    					if(e.getItemInHand().getType() == Material.WOOD){
    						block.getRelative(0, 0, -1).setType(Material.WALL_SIGN);
    						block.getRelative(-1, 0, 0).setType(Material.PISTON_BASE);
    						block.getRelative(-1, 0, 0).setData((byte) 4);
    						/*Sign sign = (Sign) block.getRelative(0, 0, -1);
    						sign.setType(Material.WALL_SIGN);
    						sign.setLine(0, "§lСобытие игрока");*/
    					}else if(e.getItemInHand().getType() == Material.COBBLESTONE){
    						block.getRelative(0, 0, -1).setType(Material.WALL_SIGN);
    						block.getRelative(-1, 0, 0).setType(Material.STONE);
    						/*Sign sign = (Sign) block.getRelative(0, 0, -1);
    						sign.setType(Material.WALL_SIGN);
    						sign.setLine(0, "§lСобытие игрока");*/
    					}
    				}else{
    					e.setCancelled(true);
    				}
    			}else{
    				e.setCancelled(true);
    			}
    			
    		}
    	}
    }
    @EventHandler
    public void breakBlock(BlockBreakEvent e){
    	Player p = e.getPlayer();
    	if(p.getLocation().getWorld().getName().equalsIgnoreCase("world")){
    		if(getPlayerGroupId(p) != 6){
    			e.setCancelled(true);
    			p.sendMessage("Низя!");
    			System.out.println("Player " + p.getName() + "is break block " + e.getBlock().getType() + " in lobby at " + e.getBlock().getX() + " " + e.getBlock().getY() + " " + e.getBlock().getZ() + ".");
    		}
    	}else{
    		if(worldsList.contains(p.getLocation().getWorld().getName().split("%")[0] + split + "coding")){
    			Block block = e.getBlock();
    			Block downBlock = block.getRelative(0, -1, 0);
    			if(downBlock.getType() == Material.STAINED_GLASS){
    				if(block.getType() == Material.WALL_SIGN){
    					block.setType(Material.AIR);
    					block.getRelative(0, 0, 1).setType(Material.AIR);
    					block.getRelative(-1, 0, 0).setType(Material.AIR);
    					block.getRelative(-1, 0, 1).setType(Material.AIR);
    					if(block.getRelative(0, -1, 1).getData() == (byte) 15){
    						for(int b = 0; b > -100; b--){
    							block.getRelative(b, 0, 0).setType(Material.AIR);
    							block.getRelative(b, 0, 1).setType(Material.AIR);
    							block.getRelative(b, 1, 0).setType(Material.AIR);
    							block.getRelative(b, 1, 1).setType(Material.AIR);
    						}
    					}
    				}
    			}
    			e.setCancelled(true);
    		}
    	}
    }
    @EventHandler
    public void onVehicleLeave(VehicleExitEvent e) {
        final Entity exiter = (Entity)e.getExited();
        final Entity vehicle = (Entity)e.getVehicle();
        if (exiter.getWorld().getName().equalsIgnoreCase("world") && vehicle instanceof Horse) {
            vehicle.remove();
        }
    }
    
    /*@EventHandler
    public void entityByEntity(EntityDamageByEntityEvent e) {
        final Entity damager = e.getDamager();
        final Entity target = e.getEntity();
        if (!(damager instanceof Projectile) && damager.getWorld().getName().equalsIgnoreCase("world")) {
            e.setCancelled(true);
            if (!(target instanceof Vehicle)) {
                if (damager.getPassengers().isEmpty()) {
                    damager.addPassenger(target);
                    damager.getWorld().playSound(damager.getLocation(), Sound.ENTITY_CHICKEN_EGG, 2.0f, 1.0f);
                }
                else {
                    damager.removePassenger(target);
                }
            }
        }
    }*/
    
    public boolean isContains(String[] array, String str){
    	try {
    		for(int i = 0;i<array.length;i++){
        		if(str.equalsIgnoreCase(array[i])){
        			return true;
        		}
        	}
    	}catch(NullPointerException e){
    		return false;
    	}
    	return false;
    }
    
    public boolean likeWorld(Player p, String worldName) throws Exception {
    	File like = new File(Bukkit.getWorldContainer()+File.separator+worldName+File.separator+"like.txt");
    	if(!isPreferred(p,worldName)){
    		FileUtils fu = new FileUtils();
    		if(like.exists()){
    			String likes = fu.getContent(like);
    			if(likes != ""){
    				String[] players = likes.split(",");
    				String result = "";
    				for(String str : players){
    					if(result == ""){
    						result = str;
    					}else{
    						result = result + "," + str;
    					}
    				}
    				result = result + "," + name(p);
    				fu.setContent(like, result);
    			}else{
    				fu.setContent(like, name(p));
    			}
    		}else{
    			fu.setContent(like, name(p));
    		}
    		return true;
    	}
		return false;
	}
    public boolean dislikeWorld(Player p, String worldName) throws Exception {
    	File dislike = new File(Bukkit.getWorldContainer()+File.separator+worldName+File.separator+"dislike.txt");
    	if(!isPreferred(p,worldName)){
    		FileUtils fu = new FileUtils();
    		if(dislike.exists()){
    			String dislikes = fu.getContent(dislike);
    			if(dislikes != ""){
    				String[] players = dislikes.split(",");
    				String result = "";
    				for(int i = 0;i<players.length;i++){
    					if(result == ""){
    						result = players[i];
    					}else{
    						result = result + "," + players[i];
    					}
    				}
    				result = result + "," + name(p);
    				fu.setContent(dislike, result);
    			}else{
    				fu.setContent(dislike, name(p));
    			}
    		}else{
    			fu.setContent(dislike, name(p));
    		}
    		return true;
    	}
		return false;
	}
    public boolean isPreferred(Player p, String worldName){
    	File like = new File(Bukkit.getWorldContainer()+File.separator+worldName+File.separator+"like.txt");
    	File dislike = new File(Bukkit.getWorldContainer()+File.separator+worldName+File.separator+"dislike.txt");
    	FileUtils fu = new FileUtils();
    	String[] likes = null;
    	String[] dislikes = null;
		try {
			if(like.exists()){
				likes = fu.getContent(like).split(",");
			}
			if(dislike.exists()){
				dislikes = fu.getContent(dislike).split(",");
			}
		} catch (Exception e) {
			return true;
		}
    	if(isContains(likes, name(p)) || isContains(dislikes, name(p))){
    		return true;
    	}
    	return false;
	}
    public int getPrefer(String worldName){
    	File like = new File(Bukkit.getWorldContainer() + File.separator + worldName + File.separator + "like.txt");
        File dislike = new File(Bukkit.getWorldContainer() + File.separator + worldName + File.separator + "dislike.txt");
        int l = 0;
        int d = 0;
        FileUtils fu = new FileUtils();
        if(like.exists()){ //Лайки
        	String[] players = null;
        	try {
				players = fu.getContent(like).split(",");
			} catch (Exception e) {
				l = 0;
				e.printStackTrace();
			}
        	for(String str : players){
        		if((str != null) || (str != "") || (str != "\n")){
        			l++;
        		}
        	}
        }else{
        	l = 0;
        }
        if(dislike.exists()){ //Дизлайки
        	String[] players = null;
        	try {
				players = fu.getContent(dislike).split(",");
			} catch (Exception e) {
				d = 0;
				e.printStackTrace();
			}
        	for(String str : players){
        		if((str != null) || (str != "") || (str != "\n")){
        			d--;
        		}
        	}
        }else{
        	d = 0;
        }
        int resultLike = l+d;
        return resultLike;
    }
    
    public void giveCash(String player, int count, CashType type){
    	if(type == CashType.MONEY){
    		int money = getConfig().getInt("players." + player + ".money");
            this.getConfig().set("players." + player + ".money", money + count);
    	}else if(type == CashType.RUBY){
    		int money = getConfig().getInt("players." + player + ".ruby");
            this.getConfig().set("players." + player + ".ruby", money + count);
    	}
    	saveConfig();
    }
    public List<String> getWorldsTop(){
    	File mainFolder = Bukkit.getWorldContainer();
    	List<String> worlds = new ArrayList<String>();
    	for(File file : mainFolder.listFiles()){
    		if(!file.getName().endsWith("%c")){
    		if(file.isDirectory()){
    			File worldDataFile = new File(file.getPath()+File.separator+"level.dat");
    			if(worldDataFile.exists()){
    				boolean canSeeLike = getConfig().getBoolean("players." + file.getName().split("%")[0] + "." + "worldOptions.canSeeLike");
    				if(canSeeLike){
    					if(!worlds.contains(file.getName())){
    						worlds.add(file.getName());
    					}
    				}
    			}
    		}
    		}
    	}
    	boolean needIteration = true;
    	while (needIteration) {
    		needIteration = false;
    		for (int i = 1; i < worlds.size(); i++) {
    			if (getLike(worlds.get(i)) > getLike(worlds.get(i-1))) {
    				swap(worlds, i-1, i);
    				needIteration = true;
    			}
    		}
    	}
    	//List<String> sortedList = new ArrayList<String>();
		return worlds;
    }
    public int getLike(String getWorldName){
    	File like = new File(Bukkit.getWorldContainer() + File.separator + getWorldName + File.separator + "like.txt");
        File dislike = new File(Bukkit.getWorldContainer() + File.separator + getWorldName + File.separator + "dislike.txt");
        int l = 0;
        int d = 0;
        FileUtils fu = new FileUtils();
        if(like.exists()){ //Лайки
        	String[] players = null;
        	try {
				players = fu.getContent(like).split(",");
			} catch (Exception e) {
				l = 0;
				e.printStackTrace();
			}
        	for(String str : players){
        		if((str != null) || (str != "") || (str != "\n")){
        			l++;
        		}
        	}
        }else{
        	l = 0;
        }
        if(dislike.exists()){ //Дизлайки
        	String[] players = null;
        	try {
				players = fu.getContent(dislike).split(",");
			} catch (Exception e) {
				d = 0;
				e.printStackTrace();
			}
        	for(String str : players){
        		if((str != null) || (str != "") || (str != "\n")){
        			d--;
        		}
        	}
        }else{
        	d = 0;
        }
        int resultLike = l+d;
        return resultLike;
    }
    private void swap(List<String> list, int ind1, int ind2) {
        String index1 = list.get(ind1);
        String index2 = list.get(ind2);
        list.set(ind1, index2);
        list.set(ind2, index1);
    }
    public int getWorldPosition(String worldName){
    	List<String> sortedList = getWorldsTop();
    	int result = 0;
    	for(int i = 0; i<sortedList.size(); i++){
    		if(sortedList.get(i).equalsIgnoreCase(worldName)){
    			result=i;
    			break;
    		}
    	}
    	return result;
    }
}
