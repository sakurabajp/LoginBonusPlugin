package mc.cherry_leaves.net.loginBonusPlugin.JoinEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Nullable;

public class JoinMessage {
    public JoinMessage(PlayerJoinEvent e, Player p) {
        e.joinMessage(JoinMessageText(p));
    }

    private Component JoinMessageText(Player p) {
        return Component.text(
                p.getName()
                )
                .color(NamedTextColor.YELLOW)
                .decoration(TextDecoration.BOLD, true)
                .append(Component.text("さんがサーバーに参加しました！")
                        .color(NamedTextColor.YELLOW)
                        .decoration(TextDecoration.BOLD, false)
                );
    }
}
