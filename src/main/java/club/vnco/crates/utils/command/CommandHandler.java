package club.vnco.crates.utils.command;

import club.vnco.crates.utils.command.binding.Binder;

public interface CommandHandler {

    Binder getBinder();

    <T> CommandHandler register(T instance, Class<? extends T> aClass);

    <T> CommandHandler register(T instance);

    void apply();

    void applyTo(String owner);
}
