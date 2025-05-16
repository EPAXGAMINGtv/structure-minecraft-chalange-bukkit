package de.epax.maxeytv.commands;

import de.epax.maxeytv.util.VoidMapGeneratorUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GenerateVoidMap implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        WorldCreator wc = new WorldCreator("voidworld");
        wc.generator(new VoidMapGeneratorUtil());
        Bukkit.createWorld(wc);
        commandSender.sendMessage(ChatColor.GREEN + "Generating VoidMap");
        return false;
    }
}