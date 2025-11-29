package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryList {
    public Component text = Component.text("LoginBonus").color(NamedTextColor.DARK_AQUA);

    public Inventory Inventory1(){
        return Bukkit.createInventory(null, 9 * 5, text);
    }
}
