package automaticmine.event;

import automaticmine.Main;
import automaticmine.PercentReturn.PercentReturn;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class event implements Listener {
    final Main plugin;
    public event(Main plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event){
        if(event.getBlock().getWorld().getName().equals(plugin.getWorldName())){
            if(event.getFace()== BlockFace.DOWN) {
                Block toBlock = event.getToBlock();
                Block fenceBlock = toBlock.getRelative(BlockFace.DOWN);
                String createblock = plugin.getCreateblock();
                Material material = Material.getMaterial(createblock.toUpperCase());
                if (fenceBlock.getType() == material) {
                    event.setCancelled(true);
                    double waitSeconds = this.plugin.getConfig().getDouble("wait_seconds");
                    PercentReturn percent = new PercentReturn(plugin);
                    String ore = percent.PercentReturn();
                    Material oreMaterial = Material.valueOf(ore.toUpperCase());
                    if (waitSeconds != 0) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                toBlock.setType(oreMaterial);
                            }
                        }.runTaskLater(this.plugin, (long) (waitSeconds * 20));
                    } else {
                        toBlock.setType(oreMaterial);
                    }
                }
            }
        }
    }
}
