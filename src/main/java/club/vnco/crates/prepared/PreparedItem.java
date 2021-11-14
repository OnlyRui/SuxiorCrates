package club.vnco.crates.prepared;

import club.vnco.crates.stores.Storeable;
import club.vnco.crates.utils.BukkitUtil;
import club.vnco.crates.utils.PlayerUtil;
import club.vnco.crates.utils.TaskUtil;
import club.vnco.crates.utils.text.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@Getter @Setter
public class PreparedItem implements Storeable<String> {

    private String id, loot;
    private transient BukkitTask task;

    public PreparedItem(String id) {
        this.id = id;
        this.loot = null;
    }

    public void start(CommandSender sender, String target, long time) {
        this.task = TaskUtil.runTaskTimerAsync(() -> new BukkitRunnable() {
            @Override public void run() {

                if (time < System.currentTimeMillis()) {
                    give(sender, target);
                    this.cancel();
                }
            }

        }, 0L, 20L);
    }

    public void give(CommandSender sender, String target) {
        PlayerUtil.actionForAllOrTarget(sender, target, player -> {

            for (ItemStack itemStack : this.getLoot()) {

                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    continue;
                }

                PlayerInventory inventory = player.getInventory();

                if (inventory.firstEmpty() == -1) {
                    player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                    ChatUtil.toSender(sender, "&aYou prepared items has been dropped in the floor");
                } else {
                    inventory.addItem(itemStack);
                    ChatUtil.toSender(sender, "&aSuccessfully gived &e" + id + " &ato your inventory!");
                }

            }

        });
    }

    public ItemStack[] getLoot() {
        return BukkitUtil.deserializeInventory(this.loot);
    }

}
