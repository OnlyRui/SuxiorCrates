package dev.suxior.crates.utils.command.bukkit.defaults;

import dev.suxior.crates.utils.command.bukkit.defaults.color.ChatColorModule;
import dev.suxior.crates.utils.command.bukkit.defaults.gamemode.GamemodeModule;
import dev.suxior.crates.utils.command.bukkit.defaults.player.PlayerModule;
import dev.suxior.crates.utils.command.bukkit.defaults.player.offline.OfflinePlayerModule;
import dev.suxior.crates.utils.command.bukkit.defaults.sender.SenderModule;
import dev.suxior.crates.utils.command.defaults.DefaultModule;
import org.bukkit.Server;

import lombok.AllArgsConstructor;
import dev.suxior.crates.utils.command.AbstractModule;

@AllArgsConstructor
public class BukkitModule extends AbstractModule {

    private Server server;

    @Override protected void configure() {
        this.install(new DefaultModule());
        this.install(new ChatColorModule());
        this.install(new GamemodeModule());
        this.install(new OfflinePlayerModule(this.server));
        this.install(new PlayerModule(this.server));
        this.install(new SenderModule());
    }
}
