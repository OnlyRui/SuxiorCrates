package dev.suxior.crates.airdrop.command.subcommand;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.airdrop.AirdropController;
import dev.suxior.crates.utilities.PlayerUtil;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 05-12-2021 - 22:31
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class AirdropGiveCommand extends BaseCommand {

    private final AirdropController controller = ((AirdropController) SuxiorCrates.getInstance().getController(AirdropController.class));

    @Command(name = "airdrop.give", permission = "suxiorcrates.airdrop.give", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length < 2) {
            ChatUtil.toSender(sender, "&cUsage: /airdrop give <player|all> <amount>");
            return;
        }

        String target = args[0];
        int amount = Integer.parseInt(args[2]);

        PlayerUtil.actionForAllOrTarget(sender, target, player -> {
            if (amount < 1) {
                ChatUtil.toSender(sender, "&cYou can't give less than 1 airdrop!");
                return;
            }

            ItemStack itemStack = controller.getSummonerItem();
            itemStack.setAmount(amount);

            PlayerInventory inventory = player.getInventory();

            if (inventory.firstEmpty() == -1) {
                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                ChatUtil.toPlayer(player, "&aYou summoner has been dropped in the floor!");
            }
            else {
                inventory.addItem(itemStack);
                ChatUtil.toPlayer(player, "&aSuccessfully added to your inventory airdrop summoners &7(x" + amount + ")");
            }

            if (target.equalsIgnoreCase("all")) {
                ChatUtil.toSender(sender, "&aSuccessfully give &ex" + amount + " &aairdrops to &e" + Bukkit.getOnlinePlayers().size() + " players&a.");
            }
            else {
                ChatUtil.toSender(sender, "&aSuccessfully give &ex" + amount + " &aairdrops to &e" + player.getName() + "&a.");
            }
        });
    }
}
