package mc.cherry_leaves.net.loginBonusPlugin;

import mc.cherry_leaves.net.loginBonusPlugin.GUI.InventoryList;
import mc.cherry_leaves.net.loginBonusPlugin.GUI.LoginBonus;
import mc.cherry_leaves.net.loginBonusPlugin.GUI.LoginBonusConfig;
import mc.cherry_leaves.net.loginBonusPlugin.GUI.addInventoryInYaml;
import mc.cherry_leaves.net.loginBonusPlugin.JoinEvent.main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class LoginBonusPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new main(), this);
        registerCommands();
        new LoginBonusPlugin().getServer().getPluginManager().registerEvents(this, this);
        new LoginBonusPlugin().getServer().getPluginManager().registerEvents(new addInventoryInYaml(), this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onEnable();
    }

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
    }

    private void registerCommands() {
        Command LoginBonusCheckCommand = getCommand();
        new LoginBonusPlugin().getServer().getCommandMap().register("LoginBonusPlugin", LoginBonusCheckCommand);

        Command LoginBonusSetCommand = new Command("setloginbonus") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
                if (!(sender instanceof Player p)) {return false;}
                if(!sender.isOp()){return false;}
                if(args.length == 0) {
                    sender.sendMessage(Component.text("ログインボーナス受け取り画面の設定に移ります").color(NamedTextColor.GREEN));
                    new LoginBonusConfig(p);
                    return false;
                }
                if(args.length >= 2) {sender.sendMessage(Component.text("エラーが発生しました。引数は1つに指定してください").color(NamedTextColor.RED)); return false;}
                else{
                    if(!args[0].matches("[0-9]+")){sender.sendMessage(Component.text("エラーが発生しました。引数は整数型で入力してください").color(NamedTextColor.RED));  return false;}
                    int x = Integer.parseInt(args[0]);
                    Inventory inv = Bukkit.createInventory(null, 9 * 5, Component.text( (9 * 4 * x) + "日目までのログイン画面設定"));
                    p.openInventory(inv);
                    new addInventoryInYaml().getInventoryInYaml1(x, inv);
                }
                return false;
            }
        };
        LoginBonusSetCommand.setDescription("ログインボーナスを設定");
        new LoginBonusPlugin().getServer().getCommandMap().register("LoginBonusPlugin", LoginBonusSetCommand);
    }

    private static @NotNull Command getCommand() {
        Command LoginBonusCheckCommand = new Command("loginbonus") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
                if (!(sender instanceof Player p)) {return false;}
                if(args.length == 0) {
                    int x;
                    if ((p.getPersistentDataContainer().get(new NamespacedKey(new LoginBonusPlugin(), "login_days"), PersistentDataType.INTEGER) == null)){
                        x = 1;
                    }
                    else {
                        x = Objects.requireNonNull(p.getPersistentDataContainer().get(new NamespacedKey(new LoginBonusPlugin(), "login_days"), PersistentDataType.INTEGER));
                    }
                    new LoginBonus(p, x);
                    return false;
                }
                if(args.length >= 2) {sender.sendMessage(Component.text("エラーが発生しました。引数は1つに指定してください").color(NamedTextColor.RED)); return false;}
                else {
                    if (!args[0].matches("[0-9]+")) {
                        sender.sendMessage(Component.text("エラーが発生しました。引数は整数型で入力してください").color(NamedTextColor.RED));
                        return false;
                    }

                    if(args[0].equals("0")){
                        sender.sendMessage(Component.text("エラーが発生しました。引数は1以上で入力してください").color(NamedTextColor.RED));
                        return false;
                    }
                    int x;
                    if ((p.getPersistentDataContainer().get(new NamespacedKey(new LoginBonusPlugin(), "login_days"), PersistentDataType.INTEGER) == null)){
                        x = 1;
                    }
                    else {
                        x = Objects.requireNonNull(p.getPersistentDataContainer().get(new NamespacedKey(new LoginBonusPlugin(), "login_days"), PersistentDataType.INTEGER));
                    }
                    if((x / 36) + 1 > Integer.parseInt(args[0])) {
                        new LoginBonus(p, (Integer.parseInt(args[0]) - 1) * 36);
                        new LoginBonus(true, p);
                    }
                    else if((x / 36) + 1 < Integer.parseInt(args[0])) {
                        new LoginBonus(p, (Integer.parseInt(args[0]) - 1) * 36);
                        new LoginBonus(false, p);
                    }
                    else if((x / 36) + 1 == Integer.parseInt(args[0])) {
                        new LoginBonus(p, x);
                    }
                }
                return false;
            }
        };
        LoginBonusCheckCommand.setDescription("ログインボーナスを確認");
        return LoginBonusCheckCommand;
    }
}
