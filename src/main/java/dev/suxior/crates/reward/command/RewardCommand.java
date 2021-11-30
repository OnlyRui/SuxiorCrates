package dev.suxior.crates.reward.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.reward.Reward;
import dev.suxior.crates.reward.RewardController;
import dev.suxior.crates.utils.PlayerUtil;
import dev.suxior.crates.utils.command.annotation.Command;
import dev.suxior.crates.utils.command.annotation.Sender;
import dev.suxior.crates.utils.command.parameter.Param;
import dev.suxior.crates.utils.text.ChatUtil;
import com.google.common.base.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RewardCommand {

    @Command(aliases = {"reward", "rewards"}, permission = "suxiorcrates.reward.command", help = true)
    public void onRewardCommand(@Sender CommandSender sender) {
        ChatUtil.toSender(sender,

                "&7&m" + Strings.repeat("-", 55),
                "&b&lRewards &7(Help Command / Arguments) [/rewards <...>]",
                "",
                " give <reward> <target> <amount> &7(Give a reward to a player)",
                " list &7(Show the rewards list)",
                "",
                "&7&m" + Strings.repeat("-", 55)

                );
    }

    @Command(aliases = {"reward give", "rewards give"}, permission = "suxiorcrates.reward.give.command")
    public void onRewardGiveCommand(@Sender CommandSender sender, @Param(name = "reward") Reward reward, @Param(name = "target") String target,
                                    @Param(name = "amount") int amount) {
        PlayerUtil.actionForAllOrTarget(sender, target, player -> {

            String name = reward.getName();
            ItemStack itemStack = reward.getItemStack();

            if (amount < 1) {
                ChatUtil.toSender(sender, "&cInvalid amount!");
                return;
            }

            itemStack.setAmount(amount);

            PlayerInventory inventory = player.getInventory();

            if (inventory.firstEmpty() == -1) {
                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                ChatUtil.toSender(sender, "&cInventory full! &7(Reward dropped)");
                return;
            } else {
                inventory.addItem(itemStack);
                ChatUtil.toPlayer(player, "&aSuccessfully gived &e" + name + " &aby &e" + sender.getName());
            }

            if (target.equalsIgnoreCase("all")) {
                ChatUtil.toSender(sender, "&aSuccessfully gived &ex" + amount + " " + reward.getName() + " &ato &e" + Bukkit.getOnlinePlayers().size() + " players&a.");
            } else {
                ChatUtil.toSender(sender, "&aSuccessfully gived &ex" + amount + " " + reward.getName() + " &ato &e" + player.getName() + "&a.");
            }

        });
    }

    @Command(aliases = {"reward list", "rewards list"}, permission = "suxiorcrates.reward.list.command")
    public void onRewardListCommand(@Sender CommandSender sender) {
        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));

        ((RewardController) SuxiorCrates.getInstance().getController(RewardController.class)).keySet().forEach(s ->
                ChatUtil.toSender(sender, "&8* &b&l" + s));

        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));
    }

}
