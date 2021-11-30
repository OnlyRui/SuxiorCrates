package dev.suxior.crates.utils.command.defaults.enums;

import dev.suxior.crates.utils.command.Key;
import dev.suxior.crates.utils.command.Provider;
import dev.suxior.crates.utils.command.binding.Binding;

public class EnumBinding<E extends Enum<E>> implements Binding<E> {

    private final Key<E> key;
    private final Provider<E> provider;

    public EnumBinding(Key<E> key) {
        this.key = key;
        this.provider = new EnumProvider<>(key);
    }

    @Override
    public Key<E> getKey() {
        return key;
    }

    @Override
    public Provider<E> getProvider() {
        return provider;
    }
}
