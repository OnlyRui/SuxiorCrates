package dev.suxior.crates.utils.command.context;

public interface ContextParser {

	<T> void append(Class<? extends T> aClass, T instance);

	Iterable<RootContext> collect();
}
