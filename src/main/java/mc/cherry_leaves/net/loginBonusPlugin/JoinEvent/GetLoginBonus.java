package mc.cherry_leaves.net.loginBonusPlugin.JoinEvent;

import mc.cherry_leaves.net.loginBonusPlugin.GUI.LoginBonus;
import mc.cherry_leaves.net.loginBonusPlugin.GUI.LoginBonusItem;
import mc.cherry_leaves.net.loginBonusPlugin.LoginBonusPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class GetLoginBonus {
    public GetLoginBonus(Player p) {
        NamespacedKey lastLoginKey = new NamespacedKey(new LoginBonusPlugin(), "last_login");
        Long lastLogin = p.getPersistentDataContainer()
                .get(lastLoginKey, PersistentDataType.LONG);
        p.getPersistentDataContainer()
                .set(lastLoginKey, PersistentDataType.LONG, System.currentTimeMillis());

        NamespacedKey LoginDaysKey = new NamespacedKey(new LoginBonusPlugin(), "login_days");
        if (!p.hasPlayedBefore()) {
            p.getPersistentDataContainer()
                    .set(LoginDaysKey, PersistentDataType.INTEGER, 1);
        }
        Integer LoginDays = p.getPersistentDataContainer()
                .get(LoginDaysKey, PersistentDataType.INTEGER);
        //ここから下,ログインメッセージ
        p.sendMessage(Line());
        if (lastLogin != null) {
            p.sendMessage(JoinMessageText(p, lastLogin, LoginDays));
        }
        else{
            p.sendMessage(FirstJoinMessageText(p));
        }
        LoginDays = p.getPersistentDataContainer()
                .get(LoginDaysKey, PersistentDataType.INTEGER);
        p.sendMessage(AllJoinMessage(p, LoginDays));
        p.sendMessage(Line());
    }

    private Component Line() {
        return Component.text("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－") // 30線
                .color(NamedTextColor.GREEN)
                .decoration(TextDecoration.BOLD, false)
                .decoration(TextDecoration.ITALIC, false);
    }

    private Component FirstJoinMessageText(Player p) {
        new LoginBonus(p, 1);
        new LoginBonusItem(p, 1);
        return Component.text("☆☆ ")
                .color(NamedTextColor.YELLOW)
                .decoration(TextDecoration.BOLD, false)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text("初ログイン報酬！")
                        .color(NamedTextColor.AQUA)
                        .decoration(TextDecoration.BOLD, true)
                        .decoration(TextDecoration.ITALIC, false))
                .append(Component.text(" ☆☆")
                        .color(NamedTextColor.YELLOW)
                        .decoration(TextDecoration.BOLD, false)
                        .decoration(TextDecoration.ITALIC, false))
                .append(Component.text("\n初ログインありがとうございます！\n管理者より、以下のログインボーナスが設定されています。\nぜひご確認ください！\n")
                        .color(NamedTextColor.GRAY)
                        .decoration(TextDecoration.BOLD, false)
                        .decoration(TextDecoration.ITALIC, true)
                );
    }

    private Component JoinMessageText(Player p, Long lastLogin, Integer loginDays) {
        // 前回ログイン日時をLocalDateに変換
        LocalDate lastLoginDate = LocalDate.ofInstant(
                java.time.Instant.ofEpochMilli(lastLogin),
                ZoneId.systemDefault()
        );

        // 現在の日付を取得
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Tokyo"));

        // 日付単位での差分を計算
        long daysSinceLastLogin = ChronoUnit.DAYS.between(lastLoginDate, today);

        if (daysSinceLastLogin == 0) {
            return Component.text("ログインありがとうございます！\n")
                            .color(NamedTextColor.YELLOW)
                            .decoration(TextDecoration.BOLD, false)
                            .decoration(TextDecoration.ITALIC, false);
        }
        else {
            loginDays += 1;
            p.getPersistentDataContainer()
                    .set(new NamespacedKey(new LoginBonusPlugin(), "login_days"), PersistentDataType.INTEGER, loginDays);
            new LoginBonus(p, loginDays);
            new LoginBonusItem(p, loginDays);
        }
        return Component.text("前回のログインから" + daysSinceLastLogin + "日経過しています！\n")
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, true)
                .append(Component.text("本日初ログイン！\n")
                        .color(NamedTextColor.YELLOW)
                        .decoration(TextDecoration.BOLD, false)
                        .decoration(TextDecoration.ITALIC, false)
                );
    }

    private Component AllJoinMessage(Player p, Integer loginDays) {
        return Component.text("報酬のチェックは")
                .color(NamedTextColor.AQUA)
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text("こちら！")
                        .color(NamedTextColor.YELLOW)
                        .decoration(TextDecoration.BOLD, false)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.UNDERLINED, true)
                        .clickEvent(ClickEvent.runCommand("/loginbonus"))
                                .append(Component.text("\n※累計" + loginDays + "日のログインを達成しています！")
                                        .color(NamedTextColor.GRAY)
                                        .decoration(TextDecoration.BOLD, false)
                                        .decoration(TextDecoration.ITALIC, true)
                                        .decoration(TextDecoration.UNDERLINED, false)
                        )
                );
    }
}
