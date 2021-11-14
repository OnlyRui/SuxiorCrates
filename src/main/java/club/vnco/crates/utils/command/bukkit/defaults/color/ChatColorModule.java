package club.vnco.crates.utils.command.bukkit.defaults.color;

import org.bukkit.ChatColor;

import club.vnco.crates.utils.command.AbstractModule;

public class ChatColorModule extends AbstractModule {

	@Override protected void configure() {
		this.bind(ChatColor.class).toProvider(new ChatColorProvider());
	}
}
