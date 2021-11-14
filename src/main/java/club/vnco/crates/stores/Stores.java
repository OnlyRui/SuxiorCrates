package club.vnco.crates.stores;

import java.util.UUID;

import club.vnco.crates.stores.defaults.impl.ClassStore;
import club.vnco.crates.stores.defaults.impl.NamedStore;
import club.vnco.crates.stores.defaults.impl.UniqueStore;
import lombok.experimental.UtilityClass;

/**
 * vHub
 * By: @CesarsDev
 */
@UtilityClass
public class Stores {

	 /**
     * Create a new hash based {@link Class} store
     * See {@link ClassStore}
     * @param <E> the object stored
     * @return new classStore
     */
    public <E extends Storeable<Class<?>>> ClassStore<E> newClassStore() {
        return new ClassStore<>();
    }
	
    /**
     * Create a new hash based string store
     * See {@link NamedStore}
     * @param <E> the object stored
     * @return new namedStore
     */
    public <E extends Storeable<String>> NamedStore<E> newNamedStore() {
        return new NamedStore<>();
    }

    /**
     * Create a new hash based {@link UUID} store
     * See {@link UniqueStore}
     * @param <E> the object stored
     * @return new uniqueStore
     */
    public <E extends Storeable<UUID>> UniqueStore<E> newUniqueStore() {
        return new UniqueStore<>();
    }
}
