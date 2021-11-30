package dev.suxior.crates.stores.defaults.impl;

import dev.suxior.crates.stores.Storeable;
import dev.suxior.crates.stores.defaults.DefaultStore;

/**
 * vHub
 * By: @CesarsDev
 */
public class NamedStore<T extends Storeable<String>> extends DefaultStore<T> {

    @Override public T get(Object id) {
        return this.get(String.valueOf(id));
    }

    public T get(String id) {
        if (this.getAll().isEmpty()) {
            return null;
        }

        return this.getAll().stream().filter(t -> t.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
}
