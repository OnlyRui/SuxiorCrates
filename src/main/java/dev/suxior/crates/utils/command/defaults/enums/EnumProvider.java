package dev.suxior.crates.utils.command.defaults.enums;

import java.util.List;

import dev.suxior.crates.utils.command.exception.ProviderException;
import dev.suxior.crates.utils.command.parameter.Parameter;
import com.google.common.collect.Lists;

import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Key;
import dev.suxior.crates.utils.command.Provider;

public class EnumProvider<E extends Enum<E>> implements Provider<E> {

    private final Class<E> type;

    public EnumProvider(Key<E> key) {
        this.type = key.getType();
    }

    @Override
    public E provide(CommandSource<?> source, String string, Parameter<E> parameter) {
        for (E enumConstant : type.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(string)) {
                return enumConstant;
            }
        }

        throw new ProviderException("No value named '$s' found in the '%s' list.", string, type.getSimpleName().toLowerCase());
    }

    @Override
    public List<String> suggest(CommandSource<?> source, Parameter<E> parameter) {
    	List<String> names = Lists.newArrayList();
    	for (E enumConstant : type.getEnumConstants()) {
    		names.add(enumConstant.name().toLowerCase());
    	}
    	
    	return names;
    }
}
