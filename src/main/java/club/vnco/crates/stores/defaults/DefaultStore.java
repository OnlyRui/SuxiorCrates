package club.vnco.crates.stores.defaults;

import java.util.ArrayList;
import java.util.List;

import club.vnco.crates.stores.Store;
import club.vnco.crates.stores.Storeable;
import com.google.common.collect.ImmutableList;

/**
 * vHub
 * By: @CesarsDev
 */
public class DefaultStore<T extends Storeable<?>> implements Store<T> {

    private final List<T> stored = new ArrayList<>();

    @Override public List<T> getAll() {
        return ImmutableList.copyOf(stored);
    }

    @Override public T get(Object id) {
        if (stored.isEmpty()) {
            return null;
        }

        return this.getAll().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override public void add(T t) {
        this.stored.add(t);
    }

    @Override public void remove(T t) {
        this.stored.remove(t);
    }

}
