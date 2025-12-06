package mc.cherry_leaves.net.loginBonusPlugin.GUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class addInventoryInYaml implements Listener {
    public void getInventoryInYaml1(int x, Inventory inv){
        File yamlFile = new File("plugins/LoginBonusPlugin/" + x + ".yml");
        if (!yamlFile.exists()) {
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(yamlFile);
        if (config.contains("slot")) {
            for (String key : Objects.requireNonNull(config.getConfigurationSection("slot")).getKeys(false)) {
                int slot = Integer.parseInt(key);
                ItemStack item = config.getItemStack("slot." + key);
                if (item != null) {
                    inv.setItem(slot, item);
                }
            }
        }
    }

    public void addInventoryInYaml1(int x, Inventory inv) throws IOException {
        File yamlFile = new File("plugins/LoginBonusPlugin/" + x + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(yamlFile);

        if (!yamlFile.exists()) {
            yamlFile.getParentFile().mkdir();
            yamlFile.createNewFile();
        }

        // 既存のslotセクションをクリア
        config.set("slot", null);

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null) {
                config.set("slot." + i, item);
            }
        }

        config.save(yamlFile);
    }

    public boolean getSettingInYaml1(String op) {
        boolean b = false;
        File yamlFile = new File("plugins/LoginBonusPlugin/config.yml");
        if (!yamlFile.exists()) {
            try {
                yamlFile.getParentFile().mkdirs();
                yamlFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(yamlFile);

        switch (op){
            case "displayName":
                b = config.getBoolean(op);
                break;
            default:
                break;
        }
        return b;
    }

    public void setSettingInYaml1(String op, boolean b){
        File yamlFile = new File("plugins/LoginBonusPlugin/config.yml");
        if (!yamlFile.exists()) {
            try {
                yamlFile.getParentFile().mkdirs();
                yamlFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(yamlFile);

        switch (op){
            case "displayName":
                config.set(op, b);

                break;
            default:
                break;
        }
        try {
            config.save(yamlFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) throws IOException {
        Component title = e.getView().title();
        String titleText = PlainTextComponentSerializer.plainText().serialize(title);
        Inventory inv = e.getInventory();
        if(titleText.contains("日目までのログイン画面設定")){
            int x = Integer.parseInt(titleText.replaceAll("[^0-9]", ""));
            new addInventoryInYaml().addInventoryInYaml1((int) x / 36, inv);
        }

        else if(title.equals(new InventoryList().text2)){
            // config.yml取得
            File yamlFile = new File("plugins/LoginBonusPlugin/config.yml");
            if (!yamlFile.exists()) {return;}
            YamlConfiguration config = YamlConfiguration.loadConfiguration(yamlFile);

            // 以下個別設定
            // displayName: bool
            boolean i = !Objects.requireNonNull(inv.getItem(10)).getEnchantments().isEmpty();
            config.set("displayName", i);
            config.save(yamlFile);
        }
    }
}