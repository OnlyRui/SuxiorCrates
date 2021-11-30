package dev.suxior.crates.utils.command.bukkit.defaults.color;

import java.util.List;
import java.util.Map;

import dev.suxior.crates.utils.command.exception.ProviderException;
import dev.suxior.crates.utils.command.parameter.Parameter;
import org.bukkit.ChatColor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Provider;

public class ChatColorProvider implements Provider<ChatColor> {

	private Map<String, ChatColor> colorMap = Maps.newConcurrentMap();

	public ChatColorProvider() {
		this.colorMap.put("pink", ChatColor.LIGHT_PURPLE);
		this.colorMap.put("orange", ChatColor.GOLD);
		this.colorMap.put("purple", ChatColor.DARK_PURPLE);

		for (ChatColor chatColor : ChatColor.values()) {
			this.colorMap.put(chatColor.name().toLowerCase().replace("_", ""), chatColor);
		}
		
	}
	
	@Override
    public ChatColor provide(CommandSource<?> source, String string, Parameter<ChatColor> parameter) {
		if (!this.colorMap.containsKey(string.toLowerCase())) {
			throw new ProviderException("Color '%s' not found.", string.toLowerCase());
		}
		
		return (getColorFromName(string));
	}
	
	@Override
    public List<String> suggest(CommandSource<?> source, Parameter<ChatColor> parameter) {
		List<String> names = Lists.newArrayList();
		names.addAll(colorMap.keySet());
		
		return (names);
	}
	
	private ChatColor getColorFromName(String name) {
		if (this.colorMap.containsKey(name.trim().toLowerCase())) {
			return this.colorMap.get(name.trim().toLowerCase());
		}

		ChatColor color;

		try {
			color = ChatColor.valueOf(name.toUpperCase().replace(" ", "_"));
		} catch (Exception e) {
			return null;
		}

		return color;
	}
}
