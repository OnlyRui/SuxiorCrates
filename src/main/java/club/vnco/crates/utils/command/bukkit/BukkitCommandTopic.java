package club.vnco.crates.utils.command.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.help.GenericCommandHelpTopic;

public class BukkitCommandTopic extends GenericCommandHelpTopic {

    private final BukkitCommand bukkitCommand;

    public BukkitCommandTopic(BukkitCommand bukkitCommand) {
        super(bukkitCommand);
        
        this.bukkitCommand = bukkitCommand;
    }

    @Override
    public boolean canSee(CommandSender sender) {
        if (!bukkitCommand.getContext().hasPermission()) {
            return (true);
        }

        return (sender.hasPermission(bukkitCommand.getContext().getPermission()));
    }
}
