package club.vnco.crates.cache;

import java.util.function.Predicate;

public interface Storager<T> extends Cache<String, T> {

    default T getByName(String name){
        return this.get().get(name);
    }

    default boolean isEmpty(){
        return this.get().isEmpty();
    }

    default int size() {
        return this.get().size();
    }

    default boolean match(Predicate<T> predicate) {
        return this.values().stream().anyMatch(predicate);
    }

    @Override default void clear() {
        if (!this.isEmpty()) {
            Cache.super.clear();
        }
    }
}
