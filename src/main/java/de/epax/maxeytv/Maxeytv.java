package de.epax.maxeytv;

import de.epax.maxeytv.commands.GenerateVoidMap;
import de.epax.maxeytv.commands.startchalangeCommand;
import de.epax.maxeytv.commands.stopChalange;
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

        getServer().getPluginManager().registerEvents(new OnPlayerDethEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
