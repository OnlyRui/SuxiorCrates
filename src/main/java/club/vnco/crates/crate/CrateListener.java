package club.vnco.crates.crate;

import club.vnco.crates.crate.menus.CrateEditLootMenu;
import club.vnco.crates.utils.BukkitUtil;
import club.vnco.crates.utils.PlayerUtil;
import club.vnco.crates.utils.text.ChatUtil;
import lombok.AllArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

@AllArgsConstructor
public class CrateListener implements Listener {

    private final CrateController controller;

    @EventHandler public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getItemInHand();

        if (itemInHand == null || itemInHand.getType() == Material.AIR || !itemInHand.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = itemInHand.getItemMeta();

        if (!itemMeta.hasDisplayName() || !itemMeta.getDisplayName().contains(CrateController.META)) {
            return;
        }

        for (Crate crate : this.controller.getCrates()) {

            if (crate == null) {
                continue;
            }

            ItemStack blockItem = crate.getBlockItem();

            if (itemInHand.equals(blockItem)) {
                if (!player.hasPermission("vcrates.place") && player.getGameMode() != GameMode.CREATIVE) {
                    event.setCancelled(true);
                    continue;
                }

                crate.getLocations().add(BukkitUtil.serializeLocation(event.getBlockPlaced().getLocation()));
                ChatUtil.toPlayer(player, "&aSuccessfully placed " + crate.getDisplayName() + "&a!");
            }
        }

    }

    @EventHandler public void onBlockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();

        this.controller.findCrate(location).ifPresent(crate -> {

            Player player = event.getPlayer();

            if (!player.hasPermission("vcrates.break") && !player.isSneaking() && !player.getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
                return;
            }

            crate.getLocations().remove(BukkitUtil.serializeLocation(location));
            ChatUtil.toPlayer(player, "&aSuccessfully removed &e" + crate.getId() + "&a!");

        });
    }

    @EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.LEFT_CLICK_AIR) {
            return;
        }

        this.controller.findCrate(event.getClickedBlock().getLocation()).ifPresent(crate -> {
            event.setCancelled(true);

            Player player = event.getPlayer();

            switch (action) {

                case LEFT_CLICK_BLOCK: {
                    event.setCancelled(true);

                    new CrateEditLootMenu(crate).openMenu(player, false);
                }

                case RIGHT_CLICK_BLOCK: {
                    event.setCancelled(true);

                    if (!event.hasItem()) {
                        return;
                    }

                    ItemStack itemInHand = event.getItem();

                    if (!crate.isValidKey(itemInHand)) {
                        ChatUtil.toPlayer(player, "&cInvalid Key!");
                        return;
                    }

                    ItemStack[] loot = crate.getLoot();

                    if (loot == null) {
                        ChatUtil.toPlayer(player, "&cThis crate need almost 1 item for can claim!");
                        return;
                    }

                    PlayerInventory inventory = player.getInventory();

                    if (inventory.firstEmpty() == -1) {
                        ChatUtil.toPlayer(player, "&cInventory full!");
                        return;
                    }

                    Random random = new Random();
                    int length = loot.length;

                    ItemStack winItem = loot[random.nextInt(length)];

                    while (winItem == null) {
                        winItem = loot[random.nextInt(length)];
                    }

                    PlayerUtil.removeOneItemFromItem(inventory, itemInHand);
                    inventory.addItem(winItem);

                    player.updateInventory();
                }

            }
        });
    }

}
