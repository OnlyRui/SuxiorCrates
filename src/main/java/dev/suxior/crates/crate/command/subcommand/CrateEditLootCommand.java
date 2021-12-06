package dev.suxior.crates.crate.command.subcommand;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.crate.menus.CrateEditLootMenu;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 02-12-2021 - 23:22
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class CrateEditLootCommand extends BaseCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(name = "crate.editloot", aliases = {"crates.editloot"}, permission = "suxiorcrates.crate.editloot")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            ChatUtil.toPlayer(player, "&cUsage: /crate editloot <crate>");
            return;
        }

        Optional<Crate> crate = this.controller.findCrate(args[0]);

        if (!crate.isPresent()) {
            ChatUtil.toPlayer(player, "&cCrate not found!");
            return;
        }

        CrateEditLootMenu menu = new CrateEditLootMenu(crate.get());
        menu.openMenu(player, false);
    }
}
