package dev.suxior.crates.utils;

import dev.suxior.crates.utils.text.ChatUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Collection;
import java.util.function.Consumer;

@UtilityClass
public class PlayerUtil {

    public void actionForAllOrTarget(CommandSender sender, String s, Consumer<Player> consumer) {
        if (s.equalsIgnoreCase("all")) {
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            if (players.size() == 0) {
                ChatUtil.toSender(sender, "&cNo online players.");
            } else {
                players.forEach(consumer);
            }

        } else {
            Player player = Bukkit.getPlayer(s);

            if (player == null) {
                ChatUtil.toSender(sender, "&cPlayer &e" + s + " &cnot found!");
            } else {
                consumer.accept(player);
            }
        }
    }

    public void playSound(Player player, String value, double volume) {
        player.playSound(player.getLocation(), Sound.valueOf(value), (float) volume, (float) volume);
    }

    public void removeOneItem(Player player) {
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand.getAmount() == 1) {
            player.getInventory().setItemInHand(new ItemStack(Material.AIR));
        } else {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        }
    }

    public void removeOneItemFromItem(PlayerInventory inventory, ItemStack item) {
        for (ItemStack itemStack : inventory.getContents()) {

            if (itemStack == null || !itemStack.equals(item)) {
                continue;
            }

            if (itemStack.getAmount() == 1) {
                itemStack.setType(Material.AIR);
            } else {
                itemStack.setAmount(itemStack.getAmount() - 1);
            }

        }
    }

}
