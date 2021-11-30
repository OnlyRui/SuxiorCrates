package dev.suxior.crates.stores;

public interface Storeable<T> {

    /**
     * Get the id for this object
     * @return the id
     */
    T getId();

    /**
     * Set the id for this object
     * @param t the id
     */
    void setId(T t);

}
