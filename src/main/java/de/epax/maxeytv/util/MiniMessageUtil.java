package de.epax.maxeytv.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MiniMessageUtil {

    private static final MiniMessage mm = MiniMessage.miniMessage();

    public static Component format(String message) {
        return mm.deserialize(message);
    }

    public static void send(Player player, String message) {
        player.sendMessage(format(message));
    }

    public static void broadcast(String message) {
        Component formatted = format(message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(formatted);
        }
    }
}
