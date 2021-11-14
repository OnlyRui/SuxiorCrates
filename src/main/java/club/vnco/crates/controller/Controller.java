package club.vnco.crates.controller;

import club.vnco.crates.Crates;
import club.vnco.crates.stores.Storeable;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Controller easy
 * By: @CesarsDev
 */
public interface Controller<T extends JavaPlugin> extends Storeable<Class<?>> {

    /**
     * When the controller is registered through {@link JavaPlugin}
     * this method will be called
     */
    default void onEnable(T t) {}

    /**
     * When the controller is unregistered through {@link JavaPlugin}
     * this method will be called
     */
    default void onDisable(T t) {}

    /**
     * Reload method
     */
    default void onReload(T t) {}

    default Object getController(Class<? extends Controller<Crates>> clazz) {
        return this.getInstance().getController(clazz);
    }

    default Crates getInstance() {
        return Crates.getInstance();
    }

    @Override default Class<?> getId() {
        return this.getClass();
    }

    @Override default void setId(Class<?> t) {
        throw new RuntimeException("Cannot change immutable id");
    }

}