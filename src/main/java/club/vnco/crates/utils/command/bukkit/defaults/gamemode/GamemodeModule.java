package club.vnco.crates.utils.command.bukkit.defaults.gamemode;

import org.bukkit.GameMode;

import club.vnco.crates.utils.command.AbstractModule;

public class GamemodeModule extends AbstractModule {
	
	@Override protected void configure() {
		this.bind(GameMode.class).toProvider(new GamemodeProvider());
	}
}
