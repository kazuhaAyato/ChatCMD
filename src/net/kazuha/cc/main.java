package net.kazuha.cc;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
public class main extends JavaPlugin {
    public static JavaPlugin instance;
    public static Configuration config;
    @Override
    public void onEnable(){
        saveDefaultConfig();
        instance = this;
        getLogger().info("============================================");
        getLogger().info(ChatColor.DARK_AQUA + "Chat" + ChatColor.YELLOW + "Cmd" + ChatColor.GRAY + " By " + ChatColor.AQUA + "Kazuha" + ChatColor.GOLD + "Ayato");
        getLogger().info("插件版本" + ChatColor.DARK_GREEN + getDescription().getVersion());
        getLogger().info("============================================");
        getLogger().info("加载配置文件..");
        config = instance.getConfig();
        Bukkit.getPluginManager().registerEvents(new listener(), this);
        getLogger().info("加载完成");
    }
    @Override
    public void onDisable(){
        getLogger().info("ChatCMD已经卸载！");
    }
}
