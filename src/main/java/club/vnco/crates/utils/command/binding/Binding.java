package club.vnco.crates.utils.command.binding;

import club.vnco.crates.utils.command.Key;
import club.vnco.crates.utils.command.Provider;

public interface Binding<T> {

    Key<T> getKey();

    Provider<T> getProvider();
}
