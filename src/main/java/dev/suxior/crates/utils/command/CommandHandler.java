package dev.suxior.crates.utils.command;

import dev.suxior.crates.utils.command.binding.Binder;

public interface CommandHandler {

    Binder getBinder();

    <T> CommandHandler register(T instance, Class<? extends T> aClass);

    <T> CommandHandler register(T instance);

    void apply();

    void applyTo(String owner);
}
