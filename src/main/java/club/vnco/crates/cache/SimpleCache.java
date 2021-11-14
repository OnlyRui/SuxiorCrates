package club.vnco.crates.cache;

import java.util.Set;

public interface SimpleCache<T> {

    Set<T> get();

    default void add(T t) {
        this.get().add(t);
    }

    default void remove(T t) {
        this.get().remove(t);
    }

    default boolean contains(T t) {
        return this.get().contains(t);
    }

}
