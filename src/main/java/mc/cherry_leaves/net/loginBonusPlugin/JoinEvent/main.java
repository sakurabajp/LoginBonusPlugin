package mc.cherry_leaves.net.loginBonusPlugin.JoinEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class main implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        new JoinMessage(e, p);
        new GetLoginBonus(p);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();

        new QuitMessage(e, p);
    }
}
