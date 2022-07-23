package net.kazuha.cc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import java.util.List;
import java.util.Objects;

import static net.kazuha.cc.main.*;
public class listener implements Listener {
    @EventHandler
    public void OnPlayerChatEvent(PlayerChatEvent e){
                for (String cfg : config.getConfigurationSection("commands").getKeys(true)) {
                    List<String> arg = config.getStringList(cfg + ".alias");
                    if(arg.isEmpty())break;
                    if(config.getBoolean(cfg + ".slient")){
                        e.setCancelled(true);
                    }
                    if (config.getInt(cfg + ".mode") == 1) {
                        if (contain(e.getMessage(), cfg) || e.getMessage().contains(Objects.requireNonNull(config.getString(cfg + ".keyword")))) {
                            List<String> dispachedcmds = config.getStringList(cfg + ".command");
                            for (int c = 0; c < dispachedcmds.size(); c++) {
                                String cmd = dispachedcmds.get(c);
                                cmd = cmd.replace("%target%", e.getPlayer().getDisplayName());
                                if (cmd.contains("console:")) {
                                    cmd = cmd.replace("console:", "");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                                } else {
                                    Bukkit.dispatchCommand(e.getPlayer(), cmd);
                                }
                            }
                        }
                    } else if (config.getInt(cfg + ".mode") == 2) {
                        if (arg.contains(e.getMessage()) || e.getMessage().equals(config.getString(cfg + ".keyword"))) {
                            List<String> dispachedcmds = config.getStringList(cfg + ".command");
                            for (int c = 0; c < dispachedcmds.size(); c++) {
                                String cmd = dispachedcmds.get(c);
                                cmd = cmd.replace("%target%", e.getPlayer().getDisplayName());
                                if (cmd.contains("console:")) {
                                    cmd = cmd.replace("console:", "");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                                } else {
                                    Bukkit.dispatchCommand(e.getPlayer(), cmd);
                                }
                            }
                        }
                    }
                }
    }
    public Boolean contain(String nw, String sec){
        List<String> arg = config.getStringList(sec+".alias");
        for(int c = 0; c<= arg.size(); c++){
            if(nw.contains(arg.get(c))){
                return true;
            }
        }
        return false;
    }
}
