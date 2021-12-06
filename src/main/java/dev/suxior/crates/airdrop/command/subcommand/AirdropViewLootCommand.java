package dev.suxior.crates.airdrop.command.subcommand;

import dev.suxior.crates.airdrop.inventory.AirdropProvisionalInventory;
import dev.suxior.crates.utilities.command.BaseCommand;
import dev.suxior.crates.utilities.command.Command;
import dev.suxior.crates.utilities.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 05-12-2021 - 22:44
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class AirdropViewLootCommand extends BaseCommand {

    @Command(name = "airdrop.viewloot", permission = "suxiorcrates.airdrop.viewloot")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        new AirdropProvisionalInventory(player).openMenu(player, false);
    }
}
