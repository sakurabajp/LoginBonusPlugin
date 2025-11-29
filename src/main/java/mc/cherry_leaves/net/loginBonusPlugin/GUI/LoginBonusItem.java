package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class LoginBonusItem {
    public LoginBonusItem(Player p, int x) {
        Inventory b = new InventoryList().Inventory1();
        new addInventoryInYaml().getInventoryInYaml1(((x / 36) + 1), b);
        ItemStack i = b.getItem(x % 36 - 1);
        p.getInventory().addItem(Objects.requireNonNull(i));
    }
}
