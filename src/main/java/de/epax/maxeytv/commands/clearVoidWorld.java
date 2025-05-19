package de.epax.maxeytv.commands;

import de.epax.maxeytv.util.ChalangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class clearVoidWorld implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        World w = Bukkit.getWorld("voidworld");
        ChalangeUtil.clearWorld(w);
        commandSender.sendMessage(ChatColor.GREEN + "Successfully cleared world");
        return false;
    }
}
