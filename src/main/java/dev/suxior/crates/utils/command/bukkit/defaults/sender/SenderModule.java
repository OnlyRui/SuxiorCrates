package dev.suxior.crates.utils.command.bukkit.defaults.sender;

import dev.suxior.crates.utils.command.annotation.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import dev.suxior.crates.utils.command.AbstractModule;

public class SenderModule extends AbstractModule {

    @Override protected void configure() {
        this.addBinding(Player.class, "a player");
        this.addBinding(ConsoleCommandSender.class, "the console");
        this.addBinding(CommandSender.class, "anyone");
    }

    private <C extends CommandSender> void addBinding(Class<C> aClass, String name) {
        this.bind(aClass).annotatedWith(Sender.class).toProvider(new SenderProvider<>(aClass, name));
    }

}
