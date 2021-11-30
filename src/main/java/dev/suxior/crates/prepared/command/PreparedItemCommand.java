package dev.suxior.crates.prepared.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.prepared.PreparedItem;
import dev.suxior.crates.prepared.PreparedItemsController;
import dev.suxior.crates.prepared.menu.PreparedItemEditLootMenu;
import dev.suxior.crates.utils.StringUtils;
import dev.suxior.crates.utils.command.annotation.Command;
import dev.suxior.crates.utils.command.annotation.Sender;
import dev.suxior.crates.utils.command.parameter.Param;
import dev.suxior.crates.utils.text.ChatUtil;
import com.google.common.base.Strings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class PreparedItemCommand {

    private final PreparedItemsController controller = (PreparedItemsController) SuxiorCrates.getInstance().getController(PreparedItemsController.class);

    @Command(aliases = {"prepareditem"}, permission = "vcrates.prepareditem.command", help = true)
    public void onPreparedItemCommand(@Sender CommandSender sender) {
        ChatUtil.toSender(sender,

                "&7&m" + Strings.repeat("-", 55),
                "&b&lPrepared Item &7(Help Command) [/prepareditem <...>]",
                "",
                " create <name> &7(Create a P-Item)",
                " delete <p-item> &7(Delete a P-Item)",
                " give <p-item> <target/all> <time> &7(Give a P-Item to a target)",
                " stop <p-item> &7(Stop a anticipated P-Item)",
                " editloot <p-item> &7(Edit loot to a P-Item)",
                " list &7(Show a list of P-Items)",
                "",
                "&7&m" + Strings.repeat("-", 55)


        );
    }

    @Command(aliases = {"prepareditem create"}, permission = "vcrates.prepareditem.create.command")
    public void onPreparedItemCreateCommand(@Sender CommandSender sender, @Param(name = "name") String name) {
        if (this.controller.findById(name).isPresent()) {
            ChatUtil.toSender(sender, "&cPrepared Item &e" + name + " &calready exists!");
            return;
        }

        this.controller.getItems().add(new PreparedItem(name));
        ChatUtil.toSender(sender, "&aSuccessfully created &e" + name + " P-Item&a!");
    }

    @Command(aliases = {"prepareditem delete"}, permission = "vcrates.prepareditem.delete.command")
    public void onPreparedItemDeleteCommand(@Sender CommandSender sender, @Param(name = "p-item") PreparedItem preparedItem) {
        String name = preparedItem.getId();

        this.controller.getItems().remove(preparedItem);
        ChatUtil.toSender(sender, "&aSuccessfully deleted &e" + name + " P-Item &a!");
    }

    @Command(aliases = {"prepareditem give"}, permission = "vcrates.prepareditem.editloot.command")
    public void onPreparedItemGiveCommand(@Sender CommandSender sender, @Param(name = "p-item") PreparedItem preparedItem, @Param(name = "target/all") String target,
                                          @Param(name = "time", optional = true) String timeString) {
        if (timeString != null) {
            long time = StringUtils.parse(timeString);

            if (time < 1000L) {
                ChatUtil.toSender(sender, "&cInvalid time!");
                return;
            }

            preparedItem.start(sender, target, time);
            ChatUtil.toSender(sender, "&aSuccessfully started &e" + preparedItem.getId());
        } else {
            preparedItem.give(sender, target);
            ChatUtil.toSender(sender, "&aSuccessfully gived &e" + preparedItem.getId());
        }
    }

    @Command(aliases = "prepareditem stop", permission = "vcrates.prepareditem.stop.command")
    public void onPreparedItemStopCommand(@Sender CommandSender sender, @Param(name = "p-item") PreparedItem preparedItem) {
        String name = preparedItem.getId();
        BukkitTask task = preparedItem.getTask();

        if (task == null) {
            ChatUtil.toSender(sender, "&cPrepared Item &e" + name + " &cis not running the timer!");
        } else {
            task.cancel();
            ChatUtil.toSender(sender, "&aSuccessfully stopped timer &e" + name + "&a!");
        }

    }

    @Command(aliases = {"prepareditem editloot"}, permission = "vcrates.prepareditem.editloot.command", forPlayerOnly = true)
    public void onPreparedItemEditLootCommand(@Sender Player player, @Param(name = "p-item") PreparedItem preparedItem) {
        new PreparedItemEditLootMenu(preparedItem).openMenu(player, false);
    }

    @Command(aliases = {"prepareditem list"}, permission = "vcrates.prepareditem.list.command")
    public void onPreparedItemListCommand(@Sender CommandSender sender) {
        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));

        this.controller.getItems().forEach(preparedItem -> ChatUtil.toSender(sender, "&8* &b" + preparedItem.getId()));

        ChatUtil.toSender(sender, "&7&m" + Strings.repeat("-", 55));
    }

}
