package club.vnco.crates.stores.defaults.impl;

import club.vnco.crates.stores.Storeable;
import club.vnco.crates.stores.defaults.DefaultStore;

/**
 * vHub
 * By: @CesarsDev
 */
public class ClassStore<T extends Storeable<Class<?>>> extends DefaultStore<T> {

	@Override public T get(Object id) {
		return this.get((Class<?>) id);
	}
	
	public T get(Class<?> clazz) {
		if (this.getAll().isEmpty()) {
			return null;
		}
		
		return this.getAll().stream().filter(t -> t.getId().getSimpleName().equalsIgnoreCase(clazz.getSimpleName())).findFirst().orElse(null);
	}

}
