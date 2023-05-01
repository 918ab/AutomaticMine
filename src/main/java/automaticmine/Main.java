package automaticmine;

import automaticmine.Command.Command;
import automaticmine.event.event;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin implements Listener, CommandExecutor {
    private static Main plugin;
    private String worldName;
    private String Createblock;
    public static Main getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getLogger().info("[AutoMaticMine] AutoMaticMine Enable");
        getCommand("automaticmine").setExecutor(new Command(this));
        setWorldName();
        getServer().getPluginManager().registerEvents(new event(this), this);
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
    }

    public void setWorldName(){
        worldName = getConfig().getString("world");
        Createblock = getConfig().getString("create_block");
    }
    public String getWorldName() {
        return worldName;
    }
    public String getCreateblock(){
        return Createblock;
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[AutoMaticMine] AutoMaticMine Disable");

    }
}
