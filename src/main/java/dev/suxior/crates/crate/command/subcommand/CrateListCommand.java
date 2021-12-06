package dev.suxior.crates.crate.command.subcommand;

import com.google.common.base.Strings;
import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.command.CommandSender;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 01-12-2021 - 22:08
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class CrateListCommand extends BaseCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(name = "crate.list", aliases = {"crates.list"}, permission = "suxiorcrates.crate.list", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();

        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));
        ChatUtil.toSender(sender, "&b&lCrates List");

        if (controller.getCrates().isEmpty()) {
            ChatUtil.toSender(sender, "&cThere are no crates available!");
        }
        else {
            controller.getCrates().forEach(crate -> ChatUtil.toSender(sender, "&3❃ &b" + crate.getId()));
        }

        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));
    }
}
