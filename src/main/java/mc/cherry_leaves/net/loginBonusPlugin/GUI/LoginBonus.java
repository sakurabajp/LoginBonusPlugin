package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LoginBonus {

    public LoginBonus(Player p, int x) {
        Inventory LoginBonusInventory = new InventoryList().Inventory1();
        p.getInventory().close();
        p.openInventory(LoginBonusInventory);
        new addInventoryInYaml().getInventoryInYaml1(((x / 36) + 1), LoginBonusInventory);
        for(int i = 0; i < (x % 36); i++){
            LoginBonusInventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    public LoginBonus(boolean b, Player p) {
        if(b) {
            p.getOpenInventory().setItem(35, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }
}
