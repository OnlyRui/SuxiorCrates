package dev.suxior.crates.utils.command.defaults.instance;

import dev.suxior.crates.utils.command.parameter.Parameter;
import lombok.AllArgsConstructor;
import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Provider;

@AllArgsConstructor
public class InstanceProvider<I> implements Provider<I> {

    private I instance;

    @Override
    public I provide(CommandSource<?> source, String string, Parameter<I> parameter) {
        return (instance);
    }
}
