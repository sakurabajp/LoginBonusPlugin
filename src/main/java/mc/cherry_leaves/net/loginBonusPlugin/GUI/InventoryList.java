package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryList {
    public Component text1 = Component.text("LoginBonus").color(NamedTextColor.DARK_AQUA);
    public Component text2 = Component.text("設定").color(NamedTextColor.DARK_AQUA);

    public Inventory Inventory1(){
        return Bukkit.createInventory(null, 9 * 5, text1);
    }

    public Inventory SettingInventory(){
        return Bukkit.createInventory(null, 9 * 5, text2);
    }
}
