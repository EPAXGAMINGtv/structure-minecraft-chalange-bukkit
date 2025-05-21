package de.epax.maxeytv.commands;

import de.epax.maxeytv.util.ChalangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class restartchalange implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        new BukkitRunnable() {
            int countdown = 5;
            @Override
            public void run() {
                Player p = Bukkit.getPlayer(commandSender.getName());
                if (countdown > 0) {
                    Bukkit.broadcastMessage("Server Restartet in "+countdown+"Sekunden um die Chalange neu zu starten.");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                    countdown--;
                } else {
                    Bukkit.broadcastMessage("Restartchalange");
                    ChalangeUtil.restartChalange();
                    cancel();
                }
            }
        }.runTaskTimer(ChalangeUtil.plugin, 0L, 20L);

        return false;
    }
}
