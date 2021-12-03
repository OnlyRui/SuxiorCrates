package dev.suxior.crates.reward;

import dev.suxior.crates.utilities.PlayerUtil;
import dev.suxior.crates.utilities.text.ChatUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class RewardListener implements Listener {

    private final RewardController controller;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (!event.hasItem()) {
            return;
        }

        ItemStack itemInHand = event.getItem();

        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

            for (Reward reward : this.controller.values()) {

                if (!itemInHand.equals(reward.getItemStack())) return;

                event.setCancelled(true);

                PlayerUtil.removeOneItemFromItem(player.getInventory(), itemInHand);

                reward.getCommands().forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), (s)
                        .replace("<player>", player.getName())));

                player.updateInventory();
                ChatUtil.toPlayer(player, "&aSuccessfully redeem &e" + reward.getName() + "&a!");
            }
        }
    }
}
