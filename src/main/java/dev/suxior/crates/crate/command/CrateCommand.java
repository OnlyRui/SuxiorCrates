package dev.suxior.crates.crate.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.crate.menus.CrateEditLootMenu;
import dev.suxior.crates.utils.PlayerUtil;
import dev.suxior.crates.utils.command.annotation.Command;
import dev.suxior.crates.utils.command.annotation.Sender;
import dev.suxior.crates.utils.command.parameter.Param;
import dev.suxior.crates.utils.text.ChatUtil;
import com.google.common.base.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateCommand {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Command(aliases = {"crate", "crates"}, description = "Crate command", help = true)
    public void onCrateCommand(@Sender CommandSender sender) {
        if (sender.hasPermission("vcrates.command")) {
            ChatUtil.toSender(sender,

                    "&7&m" + Strings.repeat("-", 55),
                    "&b&lvCrates &7(Help Command / Arguments) [/crates <...>]",
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
        } else {
            ChatUtil.toSender(sender,

                    "&7&m" + Strings.repeat("-", 55),
                    "&b&lvCrates &7(Plugin Information)",
                    "",
                    "&bOwner&7: &fRui",
                    "&bWorker&7: &fVnco#1315 &8/ &f@OldVnco",
                    "&bSupport&7: &fdiscord.vnco.club",
                    "&7&m" + Strings.repeat("-", 55)

                    );
        }
    }

    @Command(aliases = {"crate create", "crates create"}, permission = "vcrates.create.command")
    public void onCrateCreateCommand(@Sender CommandSender sender, @Param(name = "name") String name) {
        if (this.controller.findCrate(name).isPresent()) {
            ChatUtil.toSender(sender, "&cCrate &e" + name + " &calready exists!");
            return;
        }

        this.controller.getCrateStore().add(new Crate(name));
        ChatUtil.toSender(sender, "&aSuccessfully created &e" + name + " &7Crate&a!");
    }

    @Command(aliases = {"crate delete", "crates delete"}, permission = "vcrates.delete.command")
    public void onCrateDeleteCommand(@Sender CommandSender sender, @Param(name = "crate") Crate crate) {
        String name = crate.getId();

        this.controller.getCrateStore().remove(crate);
        ChatUtil.toSender(sender, "&aSuccessfully deleted &e" + name + " &7Crate&a!");
    }

    @Command(aliases = {"crate editloot", "crates editloot"}, permission = "vcrates.editloot.command", forPlayerOnly = true)
    public void onCrateEditLootCommand(@Sender Player player, @Param(name = "crate") Crate crate) {
        new CrateEditLootMenu(crate).openMenu(player, false);
    }

    @Command(aliases = {"crate givekey", "crates givekey"}, permission = "vcrates.givekey.command")
    public void onCrateGiveKeyCommand(@Sender CommandSender sender, @Param(name = "crate") Crate crate, @Param(name = "target/all") String target, @Param(name = "amount") int amount) {
        PlayerUtil.actionForAllOrTarget(sender, target, player -> {

            if (amount < 1) {
                ChatUtil.toSender(sender, "&cInvalid amount!");
                return;
            }

            crate.giveKey(player, amount);

            if (target.equalsIgnoreCase("all")) {
                ChatUtil.toSender(sender, "&aSuccessfully gived &ex" + amount + " " + crate.getDisplayName() + " &akeys to &e" + Bukkit.getOnlinePlayers().size() + " players&a.");
            } else {
                ChatUtil.toSender(sender, "&aSuccessfully gived &ex" + amount + " " + crate.getDisplayName() + " &akeys to &e" + player.getName() + "&a.");
            }


        });
    }

    @Command(aliases = {"crate give", "crates give"}, permission = "vcrates.give.command", forPlayerOnly = true)
    public void onCrateGiveCommand(@Sender CommandSender sender, @Param(name = "crate") Crate crate, @Param(name = "target") Player target) {
        crate.giveBlock(target);

        if (sender != target) {
            ChatUtil.toSender(sender, "&aSuccessfully gived to &e" + target.getName() + " " + crate.getDisplayName());
        }

        ChatUtil.toSender(sender, "&aSuccessfully gived to yourself " + crate.getDisplayName());
    }

    @Command(aliases = {"crate list", "crates list"}, permission = "vcrates.list.command")
    public void onCrateListCommand(@Sender CommandSender sender) {
        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));

        this.controller.getCrates().forEach(crate -> ChatUtil.toSender(sender, "&8* " + crate.getDisplayName()));

        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));
    }

}
