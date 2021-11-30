package dev.suxior.crates.utils.command.bukkit.defaults.player.offline;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;

import lombok.AllArgsConstructor;
import dev.suxior.crates.utils.command.AbstractModule;

@AllArgsConstructor
public class OfflinePlayerModule extends AbstractModule {

    private Server server;

    @Override protected void configure() {
        bind(OfflinePlayer.class).toProvider(new OfflinePlayerProvider(server));
    }
}
