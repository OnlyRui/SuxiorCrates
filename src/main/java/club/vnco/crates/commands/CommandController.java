package club.vnco.crates.commands;

import club.vnco.crates.Crates;
import club.vnco.crates.airdrop.command.AirdropCommand;
import club.vnco.crates.cache.SimpleCache;
import club.vnco.crates.controller.Controller;
import club.vnco.crates.crate.Crate;
import club.vnco.crates.crate.command.CrateCommand;
import club.vnco.crates.crate.command.CrateProvider;
import club.vnco.crates.prepared.PreparedItem;
import club.vnco.crates.prepared.command.PreparedItemCommand;
import club.vnco.crates.prepared.command.PreparedItemProvider;
import club.vnco.crates.reward.Reward;
import club.vnco.crates.reward.command.RewardCommand;
import club.vnco.crates.reward.command.RewardProvider;
import club.vnco.crates.utils.command.CommandHandler;
import club.vnco.crates.utils.command.Provider;
import club.vnco.crates.utils.command.bukkit.BukkitCommandHandler;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public class CommandController implements SimpleCache<Object>, Controller<Crates> {

    private CommandHandler commandHandler;
    private Set<Object> objects;

    @Override public void onEnable(Crates crates) {
        this.objects = new HashSet<>();
        this.commandHandler = new BukkitCommandHandler(crates);

        this.registerProviders();

        this.objects.addAll(Arrays.asList(

                new CrateCommand(),
                new RewardCommand(),
                new AirdropCommand(),
                new PreparedItemCommand()

        ));

        this.objects.forEach(object -> this.commandHandler.register(object));
        this.commandHandler.applyTo(crates.getName());
    }

    private void registerProviders(){
        this.provide(Crate.class, new CrateProvider());
        this.provide(Reward.class, new RewardProvider());
        this.provide(PreparedItem.class, new PreparedItemProvider());
    }

    @SuppressWarnings("all")
    private void provide(Class<?> clazz, Provider provider) {
        this.commandHandler.getBinder().bind(clazz).toProvider(provider);
    }

    @Override public Set<Object> get() {
        return this.objects;
    }

}
