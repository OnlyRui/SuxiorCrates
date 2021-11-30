package dev.suxior.crates.utils.command.binding;

import dev.suxior.crates.utils.command.Key;
import dev.suxior.crates.utils.command.Provider;

public interface Binding<T> {

    Key<T> getKey();

    Provider<T> getProvider();
}
