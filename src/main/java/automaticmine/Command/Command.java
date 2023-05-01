package automaticmine.Command;

import automaticmine.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Command implements CommandExecutor {
    private final Main plugin;

    public Command(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp()){
            Main.getPlugin().reloadConfig();
            plugin.setWorldName();
            sender.sendMessage("§f[§aAutoMaticMine§f] Config.yml 리로드 완료");
            Integer waitseconds = plugin.getConfig().getInt("wait_seconds");
            if(waitseconds!=0) {
                sender.sendMessage("§f[§aAutoMaticMine§f] wait_seconds : §a" + waitseconds+"초");
            }else{
                sender.sendMessage("§f[§aAutoMaticMine§f] wait_seconds : §a0(사용안함)");
            }
            String worldName = plugin.getConfig().getString("world");
            sender.sendMessage("§f[§aAutoMaticMine§f] world : §a"+worldName);
            String createblock = plugin.getConfig().getString("create_block");
            sender.sendMessage("§f[§aAutoMaticMine§f] createblock : §a"+createblock);
            Set<String> oreSet = plugin.getConfig().getConfigurationSection("ord_spawn").getKeys(false);
            double maxPercent = 0;
            for (String ore : oreSet) {
                double percent = plugin.getConfig().getDouble("ord_spawn." + ore);
                sender.sendMessage("§f[§aAutoMaticMine§f] "+ore+" : §a"+percent+"%");
                maxPercent += percent;
            }
            sender.sendMessage("§f[§aAutoMaticMine§f] 총 : §a"+maxPercent+"%");
        }else{
            sender.sendMessage("§f[§aAutoMaticMine§f] 권한이 없습니다");
        }
        return true;
    }
}
