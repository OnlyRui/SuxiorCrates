package club.vnco.crates.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Cache<K, V> {

    Map<K, V> get();

    default Optional<V> find(K k) {
        return Optional.ofNullable(this.get().get(k));
    }

    default void add(K k, V v) {
        this.get().put(k, v);
    }

    default void remove(K k) {
        this.get().remove(k);
    }

    default V get(K k) {
        return this.get().get(k);
    }
    
    default Set<K> keySet() {
        return this.get().keySet();
    }

    default Collection<V> values() {
        return this.get().values();
    }

    default void clear() {
        this.get().clear();
    }
    
    default boolean contains(K k) {
        return this.get().containsKey(k);
    }

    default void keySetEach(Consumer<K> consumer){
        this.keySet().forEach(consumer);
    }

    default void valuesEach(Consumer<V> consumer){
        this.values().forEach(consumer);
    }

    default Set<Map.Entry<K, V>> entry(){
        return this.get().entrySet();
    }

    default void forEach(BiConsumer<K, V> biConsumer){
        for (Map.Entry<K, V> entry : this.entry()){
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }

}
