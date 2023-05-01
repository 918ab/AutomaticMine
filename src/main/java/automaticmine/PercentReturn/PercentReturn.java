package automaticmine.PercentReturn;

import automaticmine.Main;

import java.util.Set;

public class PercentReturn {
    private final Main plugin;

    public PercentReturn(Main plugin) {
        this.plugin = plugin;
    }
    public String PercentReturn() {
        Set<String> oreSet = plugin.getConfig().getConfigurationSection("ord_spawn").getKeys(false);
        double maxPercent = 0;
        for (String ore : oreSet) {
            double percent = plugin.getConfig().getDouble("ord_spawn." + ore);
            maxPercent += percent;
        }
        double randomPercent = Math.random() * maxPercent;
        double cumulativePercent = 0;
        for (String ore : oreSet) {
            double percent = plugin.getConfig().getDouble("ord_spawn." + ore);
            cumulativePercent += percent;
            if (randomPercent < cumulativePercent) {
                return ore;
            }
        }
        return null;
    }
}
