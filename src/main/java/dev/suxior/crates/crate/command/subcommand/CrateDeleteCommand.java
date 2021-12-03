package dev.suxior.crates.crate.command.subcommand;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 02-12-2021 - 22:11
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class CrateDeleteCommand extends BaseCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(name = "create.delete", aliases = {"crates.delete"}, permission = "suxiorcrates.command.delete", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length == 0) {
            ChatUtil.toSender(sender, "&cUsage: /crate delete <name>");
            return;
        }

        Optional<Crate> crate = this.controller.findCrate(args[0]);

        if (!crate.isPresent()) {
            ChatUtil.toSender(sender, "&cCrate not found!");
            return;
        }

        controller.getCrateStore().remove(crate.get());
        ChatUtil.toSender(sender, "&aSuccessfully deleted &e" + crate.get().getId() + " &aCrate!");
    }
}
