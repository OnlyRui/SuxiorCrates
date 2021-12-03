package dev.suxior.crates.crate.command.subcommand;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 02-12-2021 - 22:57
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class CrateGiveCommand extends BaseCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(name = "crate.give", aliases = {"crates.give"}, permission = "suxiorcrates.command.give", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length < 3) {
            ChatUtil.toSender(sender, "&cUsage: /crate give <player> <crate>");
            return;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            ChatUtil.toSender(sender, "&cPlayer not found!");
            return;
        }

        Optional<Crate> crate = this.controller.findCrate(args[1]);

        if (!crate.isPresent()) {
            ChatUtil.toSender(sender, "&cCrate not found!");
            return;
        }

        crate.get().giveBlock(player);
        ChatUtil.toSender(sender, "&aSuccessfully give to "
                + (sender == player ? "yourself" : player.getName()) + " &e" + crate.get().getId());
    }
}
