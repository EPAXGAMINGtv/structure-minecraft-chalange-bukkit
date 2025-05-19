package de.epax.maxeytv.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class help implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        commandSender.sendMessage(ChatColor.GOLD + "Commands:");
        commandSender.sendMessage(ChatColor.DARK_GREEN + "/chalangehelp");
        commandSender.sendMessage(ChatColor.DARK_GREEN + "/startchalange");
        commandSender.sendMessage(ChatColor.DARK_GREEN + "/stopchalange");
        commandSender.sendMessage(ChatColor.DARK_GREEN + "/generatevoidworld");
        commandSender.sendMessage(ChatColor.DARK_GREEN + "/clearvoidworld");
        commandSender.sendMessage(ChatColor.DARK_GREEN + "/credits");
        return false;
    }
}
