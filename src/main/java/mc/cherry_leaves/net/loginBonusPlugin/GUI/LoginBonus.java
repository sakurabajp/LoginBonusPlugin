package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoginBonus {

    public LoginBonus(Player p, int x) {
        Inventory LoginBonusInventory = new InventoryList().Inventory1();
        p.getInventory().close();
        p.openInventory(LoginBonusInventory);
        new addInventoryInYaml().getInventoryInYaml1(((x / 36) + 1), LoginBonusInventory);
        for(int i = 0; i < (x % 36); i++){
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text((i+1) + "日目 ").color(NamedTextColor.YELLOW).append(Component.text("✓受け取り済み").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, true)));
            item.setItemMeta(meta);
            LoginBonusInventory.setItem(i, item);
        }
        for(int i = 36; i < 45; i++){
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(Component.text(" "));
            item.setItemMeta(meta);
            LoginBonusInventory.setItem(i, item);
        }
    }

    public LoginBonus(boolean b, Player p) {
        if(b) {
            p.getOpenInventory().setItem(35, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }
}
