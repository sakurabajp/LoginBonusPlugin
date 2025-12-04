package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import org.bukkit.entity.Player;

public class LoginBonusConfig {
    public LoginBonusConfig(Player p) {
        p.openInventory(new InventoryList().SettingInventory());
    }

    /*
    TODO : コンフィグ画面を作る！！！
    TODO : ex.アイテムを隠す,名前の変更,受け取り済みアイテムの板ガラス色, etc...
     */
}
