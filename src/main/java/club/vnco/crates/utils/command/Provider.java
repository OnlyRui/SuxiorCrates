package club.vnco.crates.utils.command;

import java.util.Collections;
import java.util.List;

import club.vnco.crates.utils.command.parameter.Parameter;

public interface Provider<T> {

    T provide(CommandSource<?> source, String string, Parameter<T> parameter);

    default List<String> suggest(CommandSource<?> source, Parameter<T> parameter) {
        return Collections.emptyList();
    }
}
