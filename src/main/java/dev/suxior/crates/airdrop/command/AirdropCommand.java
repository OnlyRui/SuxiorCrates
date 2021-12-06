package dev.suxior.crates.airdrop.command;

import com.google.common.base.Strings;
import dev.suxior.crates.airdrop.command.subcommand.AirdropGiveCommand;
import dev.suxior.crates.airdrop.command.subcommand.AirdropViewLootCommand;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import dev.suxior.crates.utilities.text.ChatUtil;

public class AirdropCommand extends BaseCommand {

    public AirdropCommand() {
        new AirdropGiveCommand();
        new AirdropViewLootCommand();
    }

    @Command(name = "airdrop", permission = "suxiorcrates.airdrop", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        String label = command.getLabel();
        ChatUtil.toSender(command.getSender(),
                "&7&m" + Strings.repeat("-", 55),
                "&b&lAirdrop Help",
                "",
                " &b/" + label + " give <player/all> <amount> &f(Give summoner to a player)",
                " &b/" + label + " viewloot &f(View airdrop loot)",
                "",
                "&7&m" + Strings.repeat("-", 55)
        );
    }
}
