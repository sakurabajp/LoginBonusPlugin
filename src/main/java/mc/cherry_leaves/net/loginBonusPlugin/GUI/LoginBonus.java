package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import mc.cherry_leaves.net.loginBonusPlugin.LoginBonusPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class LoginBonus {

    public LoginBonus(Player p, int x) {
        Inventory LoginBonusInventory = new InventoryList().Inventory1();
        p.getInventory().close();
        p.openInventory(LoginBonusInventory);
        int xx = (x / 36) + 1;
        new addInventoryInYaml().getInventoryInYaml1(xx, LoginBonusInventory);
        changeItemMetaFromConfig(LoginBonusInventory);
        for(int i = 0; i < (x % 36); i++){
            LoginBonusInventory.setItem(i, SetItemMeta(Material.ORANGE_STAINED_GLASS_PANE, Component.text((i+1) + "日目 ").color(NamedTextColor.YELLOW).append(Component.text("✓受け取り済み").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, true))));
        }
        for(int i = 37; i < 44; i++){
            LoginBonusInventory.setItem(i, SetItemMeta(Material.GRAY_STAINED_GLASS_PANE, Component.text(" ")));
        }
        LoginBonusInventory.setItem(40, GUIConfig(p, x));
        if(xx > 63){xx = 63;}
        LoginBonusInventory.setItem(36, SetItemMeta(Material.RED_STAINED_GLASS_PANE, Component.text("前のページへ"), (xx > 1) ? (xx - 1) : 1));
        LoginBonusInventory.setItem(44, SetItemMeta(Material.LIME_STAINED_GLASS_PANE, Component.text("次のページへ"),(xx + 1)));
    }

    public LoginBonus(boolean b, Player p) {
        if(b) {
            p.getOpenInventory().setItem(35, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    private ItemStack SetItemMeta(Material m, Component c){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(c);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack SetItemMeta(Material item_m, Component c, int i){
        ItemStack item = new ItemStack(item_m);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(c);
        item.setAmount(i);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack GUIConfig(Player p,int x){
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        item.setAmount(1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("ページ情報").color(NamedTextColor.YELLOW));
        NamespacedKey LoginDaysKey = new NamespacedKey(new LoginBonusPlugin(), "login_days");
        Integer LoginDays = p.getPersistentDataContainer().get(LoginDaysKey, PersistentDataType.INTEGER);
        if(LoginDays == null) {LoginDays = 1;}
        meta.lore(List.of(
                Component.text("総ログイン日数 : ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(Component.text(LoginDays).color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false)),
                Component.text("このログインページ : ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(Component.text(((x / 36) + 1) + "ページ").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false))
        ));
        item.setItemMeta(meta);
        return item;
    }

    private void changeItemMetaFromConfig(Inventory s) {
        if (new addInventoryInYaml().getSettingInYaml1("displayName")) {
            for(int i = 1;i < (9*5); i++){
                if(s.getItem(i - 1) != null) {
                    ItemMeta im = Objects.requireNonNull(s.getItem(i - 1)).getItemMeta();
                    im.displayName(Component.text(i + "日目 ").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
                            .append(Component.text("✗未受け取り").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, true)));
                    Objects.requireNonNull(s.getItem(i - 1)).setItemMeta(im);
                }
            }
        }
    }
}
