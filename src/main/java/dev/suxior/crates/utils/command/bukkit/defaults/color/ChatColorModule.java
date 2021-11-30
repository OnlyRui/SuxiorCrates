package dev.suxior.crates.utils.command.bukkit.defaults.color;

import org.bukkit.ChatColor;

import dev.suxior.crates.utils.command.AbstractModule;

public class ChatColorModule extends AbstractModule {

	@Override protected void configure() {
		this.bind(ChatColor.class).toProvider(new ChatColorProvider());
	}
}
