package club.vnco.crates.utils.command.defaults.instance;

import club.vnco.crates.utils.command.parameter.Parameter;
import lombok.AllArgsConstructor;
import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;

@AllArgsConstructor
public class InstanceProvider<I> implements Provider<I> {

    private I instance;

    @Override
    public I provide(CommandSource<?> source, String string, Parameter<I> parameter) {
        return (instance);
    }
}
