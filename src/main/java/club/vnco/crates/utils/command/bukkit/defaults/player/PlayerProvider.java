package club.vnco.crates.utils.command.bukkit.defaults.player;

import java.util.List;

import club.vnco.crates.utils.command.exception.ProviderException;
import club.vnco.crates.utils.command.parameter.Parameter;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;

@AllArgsConstructor
public class PlayerProvider implements Provider<Player> {

    private Server server;

    @Override
    public Player provide(CommandSource<?> source, String string, Parameter<Player> parameter) {
        Object handle = source.getHandle();

    	if (handle instanceof Player &&  string.equalsIgnoreCase("$%-*")) {
    		return (Player) handle;
    	}
    	
        Player player = this.server.getPlayer(string);

        if (player == null) {
            throw new ProviderException("&cPlayer '%s' &cnot found.", string);
        }

        // Make sure the player is visible to the sender.
        if (!canSee(player, (CommandSender) source.getHandle())) {
            throw new ProviderException("&cPlayer &e'%s' &cnot found.", string);
        }

        if (player.hasPlayedBefore() && !player.isOnline()) {
            throw new ProviderException("&cPlayer '%s' &cis offline.", string);
        }

        return (player);
    }

    @Override
    public List<String> suggest(CommandSource<?> source, Parameter<Player> parameter) {
    	List<String> names = Lists.newArrayList();
    	
    	for (Player player : Bukkit.getOnlinePlayers()) {
    		if (canSee(player, (CommandSender) source.getHandle())) {
    			names.add(player.getName());
    		}
    	}
    	
        return (names);
    }

    private boolean canSee(Player player, CommandSender sender) {
        return !(sender instanceof Player) || ((Player) sender).canSee(player);
    }
}
