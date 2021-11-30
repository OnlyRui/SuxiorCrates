package dev.suxior.crates.utils.command.binding;

import java.util.Map;

import dev.suxior.crates.utils.command.internal.InternalBinder;
import dev.suxior.crates.utils.command.Key;
import dev.suxior.crates.utils.command.Module;

public interface Binder {

	static Binder newBinder(Module... modules) {
        return new InternalBinder(modules);
    }

	void install(Module module);

	<T> AnnotatedBindingBuilder<T> bind(Key<T> key);

	default <T> AnnotatedBindingBuilder<T> bind(Class<T> aClass) {
        return bind(Key.get(aClass));
    }

	Map<Key<?>, Binding<?>> getBindings();

	<T> Binding<T> getBinding(Key<T> key);

	default <T> Binding<T> getBinding(Class<T> aClass) {
        return getBinding(Key.get(aClass));
    }
}
