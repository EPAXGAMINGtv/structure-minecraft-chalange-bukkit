package de.epax.maxeytv.commands;

import de.epax.maxeytv.util.ChalangeUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class stopChalange implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        ChalangeUtil.endChallange();
        commandSender.sendMessage(ChatColor.RED + "Challange stoped!");
        return false;
    }
}
