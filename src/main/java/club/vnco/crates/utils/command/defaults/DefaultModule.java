package club.vnco.crates.utils.command.defaults;

import java.time.Duration;
import java.util.function.Function;

import club.vnco.crates.utils.command.exception.ProviderException;
import club.vnco.crates.utils.command.parameter.Parameter;
import club.vnco.crates.utils.command.AbstractModule;
import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;

public class DefaultModule extends AbstractModule {

    @Override protected void configure() {
        bind(String.class).toProvider((sender, string, parameter) -> string);

        bindNumber(Integer::parseInt, "%s is not a valid integer.", Integer.class, int.class);
        bindNumber(Float::parseFloat, "%s is not a valid float.", Float.class, float.class);
        bindNumber(Double::parseDouble, "%s is not a valid double.", Double.class, double.class);
        bindNumber(Short::parseShort, "%s is not a valid short.", Short.class, short.class);

        bind(Boolean.class).toProvider(this::parseBoolean);
        bind(boolean.class).toProvider(this::parseBoolean);
        
        bind(Duration.class).toProvider(new DurationProvider());
    }

    private <N extends Number> void bindNumber(Function<String, N> function, String message, Class<N>... classes) {
        Provider<N> provider = (sender, string, parameter) -> {
            try {
                return function.apply(string);
            } catch (Exception e) {
                throw new ProviderException(message, string);
            }
        };

        for (Class<N> aClass : classes) {
            bind(aClass).toProvider(provider);
        }
    }

    private Boolean parseBoolean(CommandSource<?> source, String string, Parameter<Boolean> parameter) {
        if (string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("true")) {
            return true;
        }
        
        if (string.equalsIgnoreCase("no") || string.equalsIgnoreCase("false")) {
            return false;
        }
        
        throw new ProviderException("%s is not a valid boolean. (true/false)", string);
    }
}
