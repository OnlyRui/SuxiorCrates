package dev.suxior.crates.utils.command.bukkit.defaults.sender;

import dev.suxior.crates.utils.command.exception.ProviderException;
import dev.suxior.crates.utils.command.parameter.Parameter;
import org.bukkit.command.CommandSender;

import lombok.AllArgsConstructor;
import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Provider;

@AllArgsConstructor
public class SenderProvider<C extends CommandSender> implements Provider<C> {

    private Class<C> type;
    private String name;

    @Override
    public C provide(CommandSource<?> source, String string, Parameter<C> parameter) {
        if (!this.type.isInstance(source.getHandle())) {
            throw new ProviderException("This can only be executed by %s.", this.name);
        }

        return this.type.cast(source.getHandle());
    }
}