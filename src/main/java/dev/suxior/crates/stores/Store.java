package dev.suxior.crates.stores;

import java.util.List;

/**
 * Store {@link Storeable} objects
 * @param <T> the object to store
 *
 * By: @OldVnco
 */
public interface Store<T extends Storeable<?>> {

    /**
     * Get an immutable copy of all objects stored
     * @return list
     */
    List<T> getAll();

    /**
     * Get an object by its id
     * @param id the id
     * @return object
     */
    T get(Object id);

    /**
     * Store an object
     * @param t the object
     */
    void add(T t);

    /**
     * Remove a stored object
     * @param t the object
     */
    void remove(T t);

}