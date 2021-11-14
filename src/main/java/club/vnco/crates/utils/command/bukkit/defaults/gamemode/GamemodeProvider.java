package club.vnco.crates.utils.command.bukkit.defaults.gamemode;

import java.util.List;
import java.util.Map;

import club.vnco.crates.utils.command.exception.ProviderException;
import club.vnco.crates.utils.command.parameter.Parameter;
import org.bukkit.GameMode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;

public class GamemodeProvider implements Provider<GameMode> {

	private final Map<String, GameMode> gamemodes = Maps.newConcurrentMap();

	public GamemodeProvider() {
		this.gamemodes.put("0", GameMode.SURVIVAL);
		this.gamemodes.put("s", GameMode.SURVIVAL);
		this.gamemodes.put("surv", GameMode.SURVIVAL);
		this.gamemodes.put("survival", GameMode.SURVIVAL);

		this.gamemodes.put("1", GameMode.CREATIVE);
		this.gamemodes.put("c", GameMode.CREATIVE);
		this.gamemodes.put("creat", GameMode.CREATIVE);
		this.gamemodes.put("creative", GameMode.CREATIVE);
	}
	
	@Override
    public GameMode provide(CommandSource<?> source, String string, Parameter<GameMode> parameter) {
		if (!this.gamemodes.containsKey(string.toLowerCase())) {
			throw new ProviderException("No gamemode named '%s' found.", string.toLowerCase());
		}
		
		return this.gamemodes.get(string.toLowerCase());
	}

	@Override
    public List<String> suggest(CommandSource<?> source, Parameter<GameMode> parameter) {
		List<String> names = Lists.newArrayList();
		
		for (GameMode gamemode : GameMode.values()) {
			names.add(gamemode.name().toLowerCase());
		}
		
		return (names);
	}
}
