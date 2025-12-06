package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class onInventoryClickEvent implements Listener {
    @EventHandler
    public void onInventoryInsertEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().title().equals(new InventoryList().text1)){
            e.setCancelled(true);
            if(e.getSlot() == 36 || e.getSlot() == 44){
                p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.85F, 1F);
                if(e.getInventory().getItem(e.getSlot()) == null){return;}
                int x = Objects.requireNonNull(e.getInventory().getItem(e.getSlot())).getAmount();
                p.performCommand("loginbonus " + x);
            }
        }
        if(e.getView().title().equals(new InventoryList().text2)){
            e.setCancelled(true);
            if(e.getSlot() == 10){
                p.playSound(p, Sound.BLOCK_TRIPWIRE_CLICK_ON, 0.85F, 1F);
                ItemMeta meta = Objects.requireNonNull(e.getInventory().getItem(10)).getItemMeta();
                if(Objects.requireNonNull(e.getInventory().getItem(10)).getEnchantments().isEmpty()){
                    meta.addEnchant(Enchantment.BREACH, 1, false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    Objects.requireNonNull(e.getInventory().getItem(10)).setItemMeta(meta);
                    new addInventoryInYaml().setSettingInYaml1("displayName", true);
                    new LoginBonusConfig(p);
                }
                else{
                    meta.removeEnchant(Enchantment.BREACH);
                    Objects.requireNonNull(e.getInventory().getItem(10)).setItemMeta(meta);
                    new addInventoryInYaml().setSettingInYaml1("displayName", false);
                    new LoginBonusConfig(p);
                }
            }
        }
    }
}
