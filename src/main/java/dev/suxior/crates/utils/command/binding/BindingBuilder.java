package dev.suxior.crates.utils.command.binding;

import dev.suxior.crates.utils.command.Provider;

public interface BindingBuilder<T> {

    void toProvider(Provider<T> provider);

    void toInstance(T instance);
}
