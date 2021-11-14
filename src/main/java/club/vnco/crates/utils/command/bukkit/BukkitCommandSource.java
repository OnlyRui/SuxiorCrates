package club.vnco.crates.utils.command.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import club.vnco.crates.utils.command.CommandSource;

public class BukkitCommandSource extends CommandSource<CommandSender> {

	public BukkitCommandSource(CommandSender handle) {
        super(handle);
    }

    @Override
    public boolean hasPermission(String permission) {
        return getHandle().hasPermission(permission);
    }

    @Override
    public void message(String message) {
        getHandle().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
