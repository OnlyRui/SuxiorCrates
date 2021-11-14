package club.vnco.crates.utils.command.binding;

import club.vnco.crates.utils.command.Provider;

public interface BindingBuilder<T> {

    void toProvider(Provider<T> provider);

    void toInstance(T instance);
}
