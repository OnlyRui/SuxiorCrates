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

    @Command(name = "crate", permission = "suxiorcrates.crate", aliases = {"crates"})
    @Override
    public void onCommand(CommandArgs command) {
        String label = command.getLabel();

        ChatUtil.toSender(command.getSender(),
                "&7&m" + Strings.repeat("-", 55),
                "&b&lCrate Help",
                "",
                " &b/" + label + " create <name> &f(Create crate)",
                " &b/" + label + " delete <crate> &f(Delete crate)",
                " &b/" + label + " givekey <player|all> <crate> <amount> &f(Give crate key to a player)",
                " &b/" + label + " give <player> <crate> &f(Give crate to a player)",
                " &b/" + label + " editloot <crate> &f(Edit a crate loot )",
                " &b/" + label + " list &f(Show the crate list)",
                " &b/" + label + " reload &f(Reload plugin)",
                "",
                "&7&m" + Strings.repeat("-", 55)
        );
    }
}
