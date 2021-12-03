package dev.suxior.crates.crate.command;

import com.google.common.base.Strings;
import dev.suxior.crates.crate.command.subcommand.*;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;

public class CrateCommand extends BaseCommand {

    public CrateCommand() {
        new CrateCreateCommand();
        new CrateDeleteCommand();
        new CrateGiveCommand();
        new CrateGiveKeyCommand();
        new CrateEditLootCommand();
        new CrateListCommand();
    }

    @Command(name = "crate", permission = "suxiorcrates.command.crate", aliases = {"crates"})
    @Override
    public void onCommand(CommandArgs command) {
        ChatUtil.toSender(command.getSender(),
                "&7&m" + Strings.repeat("-", 55),
                "&b&lSuxiorCrates &7(Help Command / Arguments) [/crates <...>]",
                "",
                " create <name> &7(Create crate)",
                " delete <crate> &7(Delete crate)",
                " givekey <crate> <target/all> <amount> &7(Give crate key to a player)",
                " give <crate> <target> &7(Give crate to a player)",
                " editloot <crate> &7(Edit a crate loot )",
                " list &7(Show the crate list)",
                " reload &7(Reload plugin)",
                "",
                "&7&m" + Strings.repeat("-", 55)
        );
    }
}
