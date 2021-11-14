package club.vnco.crates.utils.command.bukkit;

import java.util.Collection;

import club.vnco.crates.utils.command.CommandHandler;
import club.vnco.crates.utils.command.binding.Binder;
import club.vnco.crates.utils.command.bukkit.defaults.BukkitModule;
import club.vnco.crates.utils.command.context.ContextParser;
import club.vnco.crates.utils.command.context.RootContext;
import club.vnco.crates.utils.command.internal.InternalArgumentFormatter;
import club.vnco.crates.utils.command.internal.InternalCommandExecutor;
import club.vnco.crates.utils.command.internal.InternalContextParser;
import club.vnco.crates.utils.command.internal.InternalParameterParser;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Sets;

import club.vnco.crates.utils.command.CommandExecutor;
import club.vnco.crates.utils.command.Module;

public class BukkitCommandHandler implements CommandHandler {

    private final Binder binder;
    private final ContextParser contextParser;
    private final InternalArgumentFormatter argumentFormatter;
    private final BukkitCommandRegistrar registrar;

    public BukkitCommandHandler(JavaPlugin plugin, Module... modules) {
        this.binder = Binder.newBinder(modules);
        this.binder.install(new BukkitModule(plugin.getServer()));
        
        this.contextParser = new InternalContextParser(new InternalParameterParser(this.binder));
        this.argumentFormatter = new InternalArgumentFormatter();
        this.registrar = new BukkitCommandRegistrar(plugin.getServer());
    }

    @Override
    public Binder getBinder() {
        return (this.binder);
    }

    @Override
    public <T> CommandHandler register(T instance, Class<? extends T> aClass) {
        this.contextParser.append(aClass, instance);
        return (this);
    }

    @Override
    public <T> CommandHandler register(T instance) {
        return (register(instance, instance.getClass()));
    }

    @Override
    public void apply() {
        Iterable<RootContext> contexts = contextParser.collect();
        Collection<BukkitCommand> commands = Sets.newHashSet();

        for (RootContext rootContext : contexts) {
            CommandExecutor commandExecutor = new InternalCommandExecutor(rootContext, this.argumentFormatter);
            commands.add(new BukkitCommand(rootContext, commandExecutor, commandExecutor.getGenerator().generate(rootContext)));
        }

        registrar.register(commands);
    }

    @Override
    public void applyTo(String owner) {
        Iterable<RootContext> contexts = contextParser.collect();
        Collection<BukkitCommand> commands = Sets.newHashSet();

        for (RootContext rootContext : contexts) {
            CommandExecutor commandExecutor = new InternalCommandExecutor(rootContext, this.argumentFormatter);
            commands.add(new BukkitCommand(rootContext, commandExecutor, commandExecutor.getGenerator().generate(rootContext)));
        }

        registrar.register(owner, commands);
    }
}
