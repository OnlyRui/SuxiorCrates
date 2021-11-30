package dev.suxior.crates.airdrop;

import dev.suxior.crates.utils.PlayerUtil;
import dev.suxior.crates.utils.StringUtils;
import dev.suxior.crates.utils.TaskUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@AllArgsConstructor
public class AirdropListener implements Listener {

    private final AirdropController controller;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerPlaceSummoner(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (!event.hasItem()) {
            return;
        }

        ItemStack itemStack = event.getItem();

        if (!itemStack.equals(this.controller.getSummonerItem()) && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        event.setCancelled(true);

        UUID uuid = player.getUniqueId();
        Airdrop airdrop = new Airdrop(uuid, player.getLocation());

        airdrop.fall();

        TaskUtil.runTaskLater(() -> {

            airdrop.selfDestruct();
            this.controller.remove(uuid);

        }, StringUtils.parse("60s"));

        PlayerUtil.removeOneItemFromItem(player.getInventory(), itemStack);
        this.controller.add(uuid, airdrop);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerOpenAirdrop(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block block = event.getClickedBlock();
        BlockState blockState = block.getState();

        if (!(blockState instanceof Dispenser)) {
            return;
        }

        Airdrop airdrop = this.controller.findAt(block.getLocation());

        if (airdrop == null) {
            return;
        }

        /*Inventory inventory = ((Dispenser) blockState).getInventory();

        if (inventory.getContents() == null) {
            inventory.addItem(this.controller.getRandomLoot(player));
        }*/

        player.openInventory(((Dispenser) blockState).getInventory());
    }

    @EventHandler public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        Block block = event.getBlock();

        if (!(entity instanceof FallingBlock)) {
            return;
        }

        Airdrop airdrop = this.controller.findAt(block.getLocation());

        if (airdrop == null) {
            return;
        }

        Player player = Bukkit.getPlayer(airdrop.getUuid());

        if (player == null) {
            return;
        }

        entity.remove();
        event.setCancelled(true);

        block.setType(Material.DISPENSER);

        ((Dispenser) block.getState()).getInventory().addItem(this.controller.getRandomLoot(player));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof Dispenser)) {
            return;
        }

        Dispenser dispenser = (Dispenser) holder;

        Airdrop airdrop = this.controller.findAt(dispenser.getLocation());

        if (airdrop == null) {
            return;
        }

        boolean empty = true;

        for (ItemStack itemStack : dispenser.getInventory().getContents()){

            if (itemStack == null){
                continue;
            }

            empty = false;
            break;
        }

        if (empty) {
            airdrop.selfDestruct();
        }
    }
}
