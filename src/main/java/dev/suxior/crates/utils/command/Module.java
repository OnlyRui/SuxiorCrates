package dev.suxior.crates.utils.command;

import dev.suxior.crates.utils.command.binding.Binder;

public interface Module {

    void configure(Binder binder);

}
