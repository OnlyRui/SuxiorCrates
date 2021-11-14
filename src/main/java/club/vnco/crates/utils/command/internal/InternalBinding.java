package club.vnco.crates.utils.command.internal;

import club.vnco.crates.utils.command.Key;
import club.vnco.crates.utils.command.Provider;
import club.vnco.crates.utils.command.binding.Binding;

public class InternalBinding<T> implements Binding<T> {

    private Key<T> key;
    private Provider<T> provider;

    public InternalBinding(Key<T> key) {
        this.key = key;
    }

    @Override
    public Key<T> getKey() {
        return (key);
    }

    public void setKey(Key<T> key) {
        this.key = key;
    }

    @Override
    public Provider<T> getProvider() {
        return (provider);
    }

    void setProvider(Provider<T> provider) {
        this.provider = provider;
    }
}
