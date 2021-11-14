package club.vnco.crates.config.handlers;

import club.vnco.crates.Crates;
import club.vnco.crates.config.ConfigController;

import java.util.List;

public interface ConfigHandler {

    default String getStringAt(String config, String path) {
        return (String) this.get(config, path);
    }

    @SuppressWarnings("unchecked")
    default List<String> getListAt(String config, String path) {
        return (List<String>) this.get(config, path);
    }

    default int getIntAt(String config, String path) {
        return (int) this.get(config, path);
    }

    default double getDoubleAt(String config, String path) {
        return (double) this.get(config, path);
    }

    default boolean getBooleanAt(String config, String path) {
        return (boolean) this.get(config, path);
    }

    default Object get(String config, String path) {
        return ((ConfigController) Crates.getInstance().getController(ConfigController.class)).get(config).get(path);
    }

}
