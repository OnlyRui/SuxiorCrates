package dev.suxior.crates.utils.command.bukkit.defaults.player;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import dev.suxior.crates.utils.command.AbstractModule;

@AllArgsConstructor
public class PlayerModule extends AbstractModule {

    private Server server;

    @Override protected void configure() {
        this.bind(Player.class).toProvider(new PlayerProvider(this.server));
    }
}