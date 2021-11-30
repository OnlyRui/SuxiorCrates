package dev.suxior.crates.commands;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.airdrop.command.AirdropCommand;
import dev.suxior.crates.cache.SimpleCache;
import dev.suxior.crates.controller.Controller;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.command.CrateCommand;
import dev.suxior.crates.crate.command.CrateProvider;
import dev.suxior.crates.prepared.PreparedItem;
import dev.suxior.crates.prepared.command.PreparedItemCommand;
import dev.suxior.crates.prepared.command.PreparedItemProvider;
import dev.suxior.crates.reward.Reward;
import dev.suxior.crates.reward.command.RewardCommand;
import dev.suxior.crates.reward.command.RewardProvider;
import dev.suxior.crates.utils.command.CommandHandler;
import dev.suxior.crates.utils.command.Provider;
import dev.suxior.crates.utils.command.bukkit.BukkitCommandHandler;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public class CommandController implements SimpleCache<Object>, Controller<SuxiorCrates> {

    private CommandHandler commandHandler;
    private Set<Object> objects;

    @Override public void onEnable(SuxiorCrates crates) {
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
