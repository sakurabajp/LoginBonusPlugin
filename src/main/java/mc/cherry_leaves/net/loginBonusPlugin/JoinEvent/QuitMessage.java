package mc.cherry_leaves.net.loginBonusPlugin.JoinEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitMessage {
    public QuitMessage(PlayerQuitEvent e, Player p) {
        e.quitMessage(QuitMessageText(p));
    }

    private Component QuitMessageText(Player p) {
        return Component.text(
                        p.getName()
                )
                .color(NamedTextColor.YELLOW)
                .decoration(TextDecoration.BOLD, true)
                .append(Component.text("さんがサーバーから退出しました！")
                        .color(NamedTextColor.YELLOW)
                        .decoration(TextDecoration.BOLD, false)
                );
    }
}
