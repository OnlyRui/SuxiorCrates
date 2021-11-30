package dev.suxior.crates.stores.defaults.impl;

import dev.suxior.crates.stores.Storeable;
import dev.suxior.crates.stores.defaults.DefaultStore;

import java.util.UUID;

public class UniqueStore<T extends Storeable<UUID>> extends DefaultStore<T> {

    @Override public T get(Object id) {
        return this.get((UUID) (id));
    }

    public T get(UUID id) {
        if (this.getAll().isEmpty()) {
            return null;
        }

        return this.getAll().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}
