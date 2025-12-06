package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class LoginBonusConfig {
    public LoginBonusConfig(Player p) {
        Inventory s = new InventoryList().SettingInventory();
        p.openInventory(s);
        for(int i = 0; i < 9 * 5; i++){
            s.setItem(i, SetItemMeta(Material.GRAY_STAINED_GLASS_PANE, Component.text(" ")));
        }
        boolean boolDisplayName = new addInventoryInYaml().getSettingInYaml1("displayName");
        s.setItem(10, SetItemMeta(
                Material.NAME_TAG,
                Component.text("アイテム名の日数化").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD, true),
                List.of(
                        Component.text("アイテム名を日数表示で隠すかどうか設定します。").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                        Component.text("現在の設定 : ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(boolDisplayName).color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)),
                        Component.space(),
                        Component.text("例) ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("10日目 ").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
                                        .append(Component.text("✗未受け取り").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, true)))
                ),
                boolDisplayName
        ));

        s.setItem(29, new ItemStack(Material.APPLE));
        s.setItem(30, SetItemMeta(Material.IRON_SWORD, Component.text("お試し用に作られた剣")));
        changeItemMetaFromConfig(s);
    }

    private ItemStack SetItemMeta(Material m, Component c){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(c);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack SetItemMeta(Material m, Component c, List<Component> lore, boolean enchant){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(c);
        meta.lore(lore);
        if(enchant){
            meta.addEnchant(Enchantment.BREACH, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    private void changeItemMetaFromConfig(Inventory s){
        if(new addInventoryInYaml().getSettingInYaml1("displayName")) {
            for (int i = 1; i < 8; i++) {
                ItemMeta im = Objects.requireNonNull(s.getItem(i - 1 + 29)).getItemMeta();
                im.displayName(Component.text(i + "日目 ").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
                                .append(Component.text("✗未受け取り").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, true)));
                Objects.requireNonNull(s.getItem(i - 1 + 29)).setItemMeta(im);
            }
        }
    }

    /*
    TODO : コンフィグ画面を作る！！！
    TODO : ex.アイテムを隠す,名前の変更,受け取り済みアイテムの板ガラス色, etc...
     - アイテム名(表示)の変更
     */
}
