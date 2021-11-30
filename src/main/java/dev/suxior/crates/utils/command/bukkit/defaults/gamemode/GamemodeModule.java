package dev.suxior.crates.utils.command.bukkit.defaults.gamemode;

import org.bukkit.GameMode;

import dev.suxior.crates.utils.command.AbstractModule;

public class GamemodeModule extends AbstractModule {
	
	@Override protected void configure() {
		this.bind(GameMode.class).toProvider(new GamemodeProvider());
	}
}
