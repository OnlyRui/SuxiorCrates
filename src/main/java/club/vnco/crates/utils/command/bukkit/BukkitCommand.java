package club.vnco.crates.utils.command.bukkit;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import club.vnco.crates.utils.command.context.Context;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import club.vnco.crates.utils.command.CommandExecutor;
import club.vnco.crates.utils.command.CommandSource;

public class BukkitCommand extends Command {

    private final CommandExecutor commandExecutor;
    private final Context context;

    public BukkitCommand(Context context, CommandExecutor commandExecutor, String usage) {
        super(context.getName());
        
        this.commandExecutor = commandExecutor;
        this.context = context;

        setAliases(context.getAliases());

        if (context.hasDescription()) {
            setDescription(context.getDescription());
        }

        if (context.hasPermission()) {
            setPermission(context.getPermission());
        }

        setUsage("/<command> " + usage);
    }

    public Context getContext() {
        return (context);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        CommandSource<?> source = new BukkitCommandSource(sender);
        
        if (context.isAsync()) {
            ForkJoinPool.commonPool().submit(() -> commandExecutor.execute(source, alias, args));
        } else {
            commandExecutor.execute(source, alias, args);
        }

        return (true);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return (commandExecutor.suggest(new BukkitCommandSource(sender), args));
    }
}
