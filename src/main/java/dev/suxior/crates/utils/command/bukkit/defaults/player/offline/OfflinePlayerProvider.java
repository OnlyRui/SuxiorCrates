package dev.suxior.crates.utils.command.bukkit.defaults.player.offline;

import java.util.List;

import dev.suxior.crates.utils.command.exception.ProviderException;
import dev.suxior.crates.utils.command.parameter.Parameter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Provider;

@AllArgsConstructor
public class OfflinePlayerProvider implements Provider<OfflinePlayer> {

    private Server server;

    @Override
    public OfflinePlayer provide(CommandSource<?> source, String string, Parameter<OfflinePlayer> parameter) {
        Object handle = source.getHandle();

        if (handle instanceof OfflinePlayer &&  string.equalsIgnoreCase("$%-*")) {
            return (OfflinePlayer) handle;
        }


        OfflinePlayer offlinePlayer = this.server.getOfflinePlayer(string);

        if (offlinePlayer == null) {
            throw new ProviderException("&cPlayer &e'%s' &cis offline.", string);
        }

        // Make sure the player is visible to the sender.
        if (!offlinePlayer.hasPlayedBefore()) {
            throw new ProviderException("&cPlayer &e'%s' &cnot found.", string);
        }

        return (offlinePlayer);
    }

    @Override
    public List<String> suggest(CommandSource<?> source, Parameter<OfflinePlayer> parameter) {
    	List<String> names = Lists.newArrayList();
    	
    	for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
    		names.add(offlinePlayer.getName());
    	}
    	
        return (names);
    }
}
