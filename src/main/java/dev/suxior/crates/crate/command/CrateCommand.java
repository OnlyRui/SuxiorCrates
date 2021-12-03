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
                "&b&lSuxiorCrates &7(Help Command / Arguments)",
                "",
                " &b/crate create <name> &f(Create crate)",
                " &b/crate delete <crate> &f(Delete crate)",
                " &b/crate givekey <crate> <target/all> <amount> &f(Give crate key to a player)",
                " &b/crate give <crate> <target> &f(Give crate to a player)",
                " &b/crate editloot <crate> &f(Edit a crate loot )",
                " &b/crate list &f(Show the crate list)",
                " &b/crate reload &f(Reload plugin)",
                "",
                "&7&m" + Strings.repeat("-", 55)
        );
    }
}
