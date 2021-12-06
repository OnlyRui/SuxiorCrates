package dev.suxior.crates.crate.command.subcommand;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.utilities.PlayerUtil;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 02-12-2021 - 23:09
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class CrateGiveKeyCommand extends BaseCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(name = "crate.givekey", aliases = {"crates.givekey"}, permission = "suxiorcrates.crate.givekey", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length < 3) {
            ChatUtil.toSender(sender, "&cUsage: /crate givekey <player|all> <crate> <amount>");
            return;
        }

        Optional<Crate> optionalCrate = this.controller.findCrate(args[1]);

        if (!optionalCrate.isPresent()) {
            ChatUtil.toSender(sender, "&cCrate not found!");
            return;
        }

        String target = args[0];
        Crate crate = optionalCrate.get();
        int amount = Integer.parseInt(args[2]);

        PlayerUtil.actionForAllOrTarget(sender, target, player -> {
            if (amount < 1) {
                ChatUtil.toSender(sender, "&cYou can't give less than 1 key!");
                return;
            }

            crate.giveKey(player, amount);

            if (target.equalsIgnoreCase("all")) {
                ChatUtil.toSender(sender, "&aSuccessfully give &ex" + amount + " " + crate.getDisplayName() + " &akeys to &e" + Bukkit.getOnlinePlayers().size() + " players&a.");
            }
            else {
                ChatUtil.toSender(sender, "&aSuccessfully give &ex" + amount + " " + crate.getDisplayName() + " &akeys to &e" + player.getName() + "&a.");
            }
        });
    }
}
