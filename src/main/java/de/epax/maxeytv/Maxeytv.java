package de.epax.maxeytv;

import de.epax.maxeytv.commands.*;
import de.epax.maxeytv.listener.OnPlayerDethEvent;
import de.epax.maxeytv.util.ChalangeUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class Maxeytv extends JavaPlugin {

    @Override
    public void onEnable() {
        ChalangeUtil.plugin = this;
        // Plugin startup logic
        getCommand("startchalange").setExecutor(new startchalangeCommand());
        getCommand("stopchalange").setExecutor(new stopChalange());
        getCommand("generatevoidmap").setExecutor(new GenerateVoidMap());
        getCommand("clearvoidworld").setExecutor(new clearVoidWorld());
        getCommand("credits").setExecutor(new credits());
        getCommand("chalangehelp").setExecutor(new help());
        getCommand("restartchalange").setExecutor(new restartchalange());

        getServer().getPluginManager().registerEvents(new OnPlayerDethEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
