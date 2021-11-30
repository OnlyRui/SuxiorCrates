package dev.suxior.crates.airdrop.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.airdrop.AirdropController;
import dev.suxior.crates.airdrop.inventory.AirdropProvisionalInventory;
import dev.suxior.crates.utils.PlayerUtil;
import dev.suxior.crates.utils.command.annotation.Command;
import dev.suxior.crates.utils.command.annotation.Sender;
import dev.suxior.crates.utils.command.parameter.Param;
import dev.suxior.crates.utils.text.ChatUtil;
import com.google.common.base.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class AirdropCommand {

    @Command(aliases = {"airdrop"}, permission = "suxiorcrates.airdrop.command", help = true)
    public void onAirdropCommand(@Sender CommandSender sender) {
        ChatUtil.toSender(sender,

                "&7&m" + Strings.repeat("-", 55),
                "&b&lAirdrops &7(Help Command / Arguments) [/airdrop <...>]",
                "",
                " give <target/all> <amount> &7(Give summoner to a player)",
                " viewloot &7(View airdrop loot)",
                "",
                "&7&m" + Strings.repeat("-", 55)

        );
    }

    @Command(aliases = {"airdrop give"}, permission = "suxiorcrates.airdrop.give.command")
    public void onAirdropGiveCommand(@Sender CommandSender sender, @Param(name = "target") String target, @Param(name = "amount") int amount) {
        PlayerUtil.actionForAllOrTarget(sender, target, player -> {

            ItemStack itemStack = ((AirdropController) SuxiorCrates.getInstance().getController(AirdropController.class)).getSummonerItem();

            if (amount < 1) {
                ChatUtil.toSender(sender, "&cInvalid amount!");
                return;
            }

            itemStack.setAmount(amount);

            PlayerInventory inventory = player.getInventory();

            if (inventory.firstEmpty() == -1) {
                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                ChatUtil.toPlayer(player, "&aYou summoner has been dropped in the floor!");
            } else {
                inventory.addItem(itemStack);
                ChatUtil.toPlayer(player, "&aSuccessfully added to your inventory airdrop summoners &7(x" + amount + ")");
            }

            if (target.equalsIgnoreCase("all")) {
                ChatUtil.toSender(sender, "&aSuccessfully gived &ex" + amount + " &aairdrops to &e" + Bukkit.getOnlinePlayers().size() + " players&a.");
            } else {
                ChatUtil.toSender(sender, "&aSuccessfully gived &ex" + amount + " &aairdrops to &e" + player.getName() + "&a.");
            }

        });
    }

    @Command(aliases = {"airdrop viewloot"}, forPlayerOnly = true)
    public void onAirdropViewLootCommand(@Sender Player player) {
        new AirdropProvisionalInventory(player).openMenu(player, false);
    }

}
