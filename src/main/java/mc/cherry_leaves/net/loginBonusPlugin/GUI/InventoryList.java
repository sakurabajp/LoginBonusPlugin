package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryList {
    public Component text1 = Component.text("LoginBonus").color(NamedTextColor.DARK_AQUA);
    public Component text2 = Component.text("設定").color(NamedTextColor.DARK_AQUA);

    public Inventory Inventory1(){
        return Bukkit.createInventory(null, 9 * 5, text1);
    }

    public Inventory SettingInventory(){
        Inventory a = Bukkit.createInventory(null, 9 * 5, text2);
        for(int i = 0; i < 9 * 5; i++){
            a.setItem(i, SetItemMeta(Material.GRAY_STAINED_GLASS_PANE, Component.text(" ")));
        }
        return a;
    }

    private ItemStack SetItemMeta(Material m, Component c){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(c);
        item.setItemMeta(meta);
        return item;
    }
}
