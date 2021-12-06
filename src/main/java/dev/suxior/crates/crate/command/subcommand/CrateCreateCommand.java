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
 * Date: 02-12-2021 - 22:05
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class CrateCreateCommand extends BaseCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(name = "crate.create", aliases = {"crates.create"}, permission = "suxiorcrates.crate.create", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length == 0) {
            ChatUtil.toSender(sender, "&cUsage: /crate create <name>");
            return;
        }

        String name = args[0];
        Optional<Crate> crate = this.controller.findCrate(name);

        if (crate.isPresent()) {
            ChatUtil.toSender(sender, "&cCrate &e'" + name + "' &calready exists!");
            return;
        }

        controller.getCrateStore().add(new Crate(name));
        ChatUtil.toSender(sender, "&aSuccessfully created &e" + name + " &aCrate!");
    }
}
